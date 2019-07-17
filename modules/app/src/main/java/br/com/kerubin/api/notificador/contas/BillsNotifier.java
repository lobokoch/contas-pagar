package br.com.kerubin.api.notificador.contas;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.kerubin.api.notificador.model.ContasPagarSituacaoDoAnoSum;
import br.com.kerubin.api.notificador.model.ContasReceberSituacaoDoAnoSum;
import br.com.kerubin.api.notificador.model.SysUser;
import br.com.kerubin.api.servicecore.mail.MailSender;
import lombok.extern.slf4j.Slf4j;

import static br.com.kerubin.api.servicecore.util.CoreUtils.*;

@Slf4j
@Component
public class BillsNotifier {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	public static final String FINANCEIRO_CONTASPAGAR_SERVICE = "financeiro-contaspagar";
	public static final String FINANCEIRO_CONTASRECEBER_SERVICE = "financeiro-contasreceber";
	public static final String SECURITY_AUTHORIZATION_SERVICE = "security-authorization";
	public static final String HTTP = "http://";
	public static final String DASHBOARD = "dashboard";
	
	private List<SysUser> users;

	@Inject
	private RestTemplate restTemplate;
	
	@Inject
	private MailSender mailSender;

	// @Scheduled(fixedDelay = 1000 * 20)
	@Scheduled(cron = "0 0 0 * * *", zone = TIME_ZONE)
	public void executeNotifyUsersAboutTheBills() {
		loadUsersWithTenants();
		notifyUsersAboutTheBills();
	}
	
	private void loadUsersWithTenants() {		
		String url = HTTP + SECURITY_AUTHORIZATION_SERVICE + "/" + "entities/sysUser" + "/listActiveUsers";
		
		ResponseEntity<List<SysUser>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<SysUser>>() {
		});
		users = response.getBody();
	}

	private void notifyUsersAboutTheBills() {
		if (isEmpty(users)) {
			log.warn("Users list is null or empty at notifyUsersAboutTheBills.");
			return;
		}
		
		List<SysUser> distinctUsers = users.stream()
				.filter(distinctByKey(user -> user.getTenant().getId()))
				.collect(Collectors.toList());
		
		distinctUsers.forEach(userAndTenant -> {
			log.info("Notifying bills for tenant: {}...", userAndTenant.getTenant().getName());
			notifyBillsForTenant(userAndTenant);
		});

	}

	private void notifyBillsForTenant(SysUser userAndTenant) {
		if (isEmpty(userAndTenant)) {
			log.warn("Null or empty userAndTenant at notifyBillsForTenant.");
			return;
		}
		
		if (isEmpty(userAndTenant.getTenant()) || isEmpty(userAndTenant.getTenant().getName())) {
			log.warn("Null or empty tenant at notifyBillsForTenant.");
			return;
		}
		
		String tenant = userAndTenant.getTenant().getName();
		
		log.info("Starting notification for tenant: {}...", tenant);
		
		ContasPagarSituacaoDoAnoSum contasPagarSituacaoDoAnoSum = getContasPagarSituacaoDoAno(userAndTenant);
		
		ContasReceberSituacaoDoAnoSum contasReceberSituacaoDoAnoSum = getContasReceberSituacaoDoAno(userAndTenant);
		
		// Filtra apenas pelos usuários do tenant para envio das informações.
		List<SysUser> usersOfTenant = users.stream()
				.filter(it -> it.getTenant().getId().equals(userAndTenant.getTenant().getId()))
				.collect(Collectors.toList());
		
		usersOfTenant.forEach(user -> notifyBillsForUser(user, contasPagarSituacaoDoAnoSum, contasReceberSituacaoDoAnoSum));
		
		
		log.info("DONE notifyBillsForTenant for tenant: {}.", tenant);
	}
	
	private void notifyBillsForUser(SysUser user, ContasPagarSituacaoDoAnoSum contasPagarSituacaoDoAnoSum,
			ContasReceberSituacaoDoAnoSum contasReceberSituacaoDoAnoSum) {
		log.info("Notifying bills for user name: {}, e-mail: {}, tenant: {} ...", user.getName(), user.getEmail(), user.getTenant());
		
		String from = MailSender.EMAIL_FROM_DEFAULT;
		List<String> recipients = Arrays.asList(user.getEmail());
		String subsject = "Kerubin - Veja o resumo das suas contas";
		String message = buildNotificationBillsForUserMessage(user, contasPagarSituacaoDoAnoSum, contasReceberSituacaoDoAnoSum);
		
		mailSender.sendMail(from, recipients, subsject, message);
		
		log.info("DONE notification bills for user name: {}, e-mail: {}, tenant: {} ...", user.getName(), user.getEmail(), user.getTenant());
	}

	private String buildNotificationBillsForUserMessage(SysUser user,
			ContasPagarSituacaoDoAnoSum contasPagarSituacaoDoAnoSum,
			ContasReceberSituacaoDoAnoSum contasReceberSituacaoDoAnoSum) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<div style=\"max-width:600px;border:0;height:auto;line-height:100%;outline:none;text-decoration:none\">Kerubin</div>")
		.append("Olá, ").append(user.getName()).append("!")
		.append("<div style=\"max-width:600px;border:0;height:auto;line-height:100%;outline:none;text-decoration:none\">Seu resumo das Contas a Pagar e Contas a Receber</div>")
		.append("<div>")
		
		// BEGIN Contas a pagar
		.append("<table>") //
			.append("<tr>") // Head
				.append("<th colspan=\"2\">").append("Resumo das Contas a Pagar").append("</th>") //
			.append("</tr>") //
			
			// Body
			.append("<tr>") //
				.append("<td>").append("Em atraso:").append("</td>") //
				.append("<td>").append(contasPagarSituacaoDoAnoSum.getValorVencido()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("A pagar hoje:").append("</td>") //
				.append("<td>").append(contasPagarSituacaoDoAnoSum.getValorVenceHoje()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("A pagar amanhã:").append("</td>") //
				.append("<td>").append(contasPagarSituacaoDoAnoSum.getValorVenceAmanha()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("A pagar nos próximos 7 dias:").append("</td>") //
				.append("<td>").append(contasPagarSituacaoDoAnoSum.getValorVenceProximos7Dias()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("Pago este mês:").append("</td>") //
				.append("<td>").append(contasPagarSituacaoDoAnoSum.getValorPagoMesAtual()).append("</td>") //
			.append("</tr>") //
			
		.append("</table>") 
		// END Contas a Pagar
		
		// BEGIN Contas a Receber
		.append("<table>") //
			.append("<tr>") // Head
				.append("<th colspan=\"2\">").append("Resumo das Contas a Receber").append("</th>") //
			.append("</tr>") //
			
			// Body
			.append("<tr>") //
				.append("<td>").append("Em atraso:").append("</td>") //
				.append("<td>").append(contasReceberSituacaoDoAnoSum.getValorVencido()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("A receber hoje:").append("</td>") //
				.append("<td>").append(contasReceberSituacaoDoAnoSum.getValorVenceHoje()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("A receber amanhã:").append("</td>") //
				.append("<td>").append(contasReceberSituacaoDoAnoSum.getValorVenceAmanha()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("A receber nos próximos 7 dias:").append("</td>") //
				.append("<td>").append(contasReceberSituacaoDoAnoSum.getValorVenceProximos7Dias()).append("</td>") //
			.append("</tr>") //
			
			.append("<tr>") //
				.append("<td>").append("Recebido este mês:").append("</td>") //
				.append("<td>").append(contasReceberSituacaoDoAnoSum.getValorPagoMesAtual()).append("</td>") //
			.append("</tr>") //
			
		.append("</table>") 
		// END Contas a Receber
		
		// Contas a receber
		.append("</div>")
		
		.append("Abraços, <br>Equipe Kerubin.");
		
		return sb.toString();
	}

	private ContasPagarSituacaoDoAnoSum getContasPagarSituacaoDoAno(SysUser userAndTenant) {
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		
		log.info("Starting getContasPagarSituacaoDoAno for tenant: {} and user: {}...", tenant,  username);
		String url = HTTP + FINANCEIRO_CONTASPAGAR_SERVICE + "/" + DASHBOARD + "/getContasPagarSituacaoDoAno";
		
        HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);

		ResponseEntity<ContasPagarSituacaoDoAnoSum> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<ContasPagarSituacaoDoAnoSum>() {
				});
		
		ContasPagarSituacaoDoAnoSum result = response.getBody();
		
		log.info("Result at getContasPagarSituacaoDoAno for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE getContasPagarSituacaoDoAno for tenant: {} and user: {}...", tenant,  username);
		return result;
	}
	
	private ContasReceberSituacaoDoAnoSum getContasReceberSituacaoDoAno(SysUser userAndTenant) {
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		
		log.info("Starting getContasReceberSituacaoDoAno for tenant: {} and user: {}...", tenant,  username);
		
		String url = HTTP + FINANCEIRO_CONTASRECEBER_SERVICE + "/" + DASHBOARD + "/getContasReceberSituacaoDoAno";
		
		HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
        
		ResponseEntity<ContasReceberSituacaoDoAnoSum> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<ContasReceberSituacaoDoAnoSum>() {
		});
		
		ContasReceberSituacaoDoAnoSum result = response.getBody();
		
		log.info("Result at getContasReceberSituacaoDoAno for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE getContasReceberSituacaoDoAno for tenant: {} and user: {}...", tenant,  username);
		return result;
	}
	
	private HttpEntity<String> buildHttpHeaders(String tenant, String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HEADER_TENANT, tenant);
        headers.set(HEADER_USER, username);
        
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        return httpEntity;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
