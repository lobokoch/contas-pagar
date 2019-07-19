package br.com.kerubin.api.notificador.contas;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;

import java.text.DecimalFormat;
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
			ContasPagarSituacaoDoAnoSum cp,
			ContasReceberSituacaoDoAnoSum cr) {
		
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		
		String html = "<div style=\"background: #F7F7F7; padding: 0px; margin: 0px; height: 100vh; width:100%; font-family: Helvetica,Arial,sans-serif;\">\r\n" + 
		"	<div style=\"background:red; display:table; max-width:600px; width:100%; margin: 0 auto;\">\r\n" + 
		"		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; height: 60px; font-size: 1.5em; font-weight: bold;\">Kerubin</div>\r\n" + 
		"		</div>\r\n" + 
		"		<div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9;text-align:center;vertical-align:middle; display:table-cell; background: #fff; width: 100%;  height: 60px; font-size: 1.5em; font-weight: bold; padding-top: 20px; padding-bottom: 20px;\">\r\n" + 
		"              Olá, <span style=\"color:#1e94d2;\">" + getFirstName(user.getName()) + "</span>!<br>Segue o resumo das suas contas.\r\n" + 
		"          </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      \r\n" + 
		"      		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #ff5722; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Resumo do Contas a Pagar</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; font-weight:50px; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              \r\n" + 
		"              <table align=\"center\" style=\"width: 60%\">\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fc7f8b; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right; padding-right: 5px;\">Em atraso:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cp.getValorVencido()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fdbf8f; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar hoje:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cp.getValorVenceHoje()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#feffaa; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar amanhã:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cp.getValorVenceAmanha()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#dfdfdf; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar nos próximos 7 dias:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cp.getValorVenceProximos7Dias()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#a8eca5; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">Pago este mês:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cp.getValorPagoMesAtual()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"              </table>              \r\n" + 
		"             \r\n" + 
		"        \r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"            		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #ff5722; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Contas a Pagar de hoje (18/07/2019)</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; font-weight:50px; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              \r\n" + 
		"             <table align=\"center\" style=\"width: 90%; border: 1px solid #bdbdbd; border-collapse: collapse;\">             \r\n" + 
		"                	\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background: #f4f4f4;\">\r\n" + 
		"                      	<th style=\"text-align: center; border: 1px solid #bdbdbd;\">Conta</th>\r\n" + 
		"                        <th style=\"text-align: center; border: 1px solid #bdbdbd; \">Valor</th>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"height:25px;\">\r\n" + 
		"                      	<td style=\"text-align: center; border: 1px solid #bdbdbd;\">Telefone</td>\r\n" + 
		"                        <td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">200,00</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background: #f4f4f4; height:25px;\">\r\n" + 
		"                      	<td style=\"text-align: center; border: 1px solid #bdbdbd;\">Água</td>\r\n" + 
		"                        <td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">350,00</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"height:25px;\">\r\n" + 
		"                      	<td style=\"text-align: center; border: 1px solid #bdbdbd;\">Escola</td>\r\n" + 
		"                        <td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">1.350,00</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background: #f4f4f4; height:25px;\">\r\n" + 
		"                      	<th style=\"text-align: center; border: 1px solid #bdbdbd;\">Total</th>\r\n" + 
		"                        <th style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">5.840,38</th>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"              </table>\r\n" + 
		"        \r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"\r\n" + 
		"<!--  CONTAS A RECEBER -->\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Resumo do Contas a Receber</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; font-weight:50px; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              \r\n" + 
		"              <table align=\"center\" style=\"width: 60%\">\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fc7f8b; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right; padding-right: 5px;\">Em atraso:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cr.getValorVencido()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fdbf8f; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber hoje:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cr.getValorVenceHoje()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#feffaa; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber amanhã:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cr.getValorVenceAmanha()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#dfdfdf; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber nos próximos 7 dias:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cr.getValorVenceProximos7Dias()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#a8eca5; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">Recebido este mês:</td>\r\n" + 
		"                        <td style=\"text-align: left; padding-left: 5px;\">" + df.format(cr.getValorPagoMesAtual()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"              </table>\r\n" + 
		"        \r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"            		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Contas a Receber de hoje (18/07/2019)</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; font-weight:50px; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              \r\n" + 
		"              <table align=\"center\" style=\"width: 90%; border: 1px solid #bdbdbd; border-collapse: collapse;\">             \r\n" + 
		"                	\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background: #f4f4f4;\">\r\n" + 
		"                      	<th style=\"text-align: center; border: 1px solid #bdbdbd;\">Conta</th>\r\n" + 
		"                        <th style=\"text-align: center; border: 1px solid #bdbdbd; \">Valor</th>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"height:25px;\">\r\n" + 
		"                      	<td style=\"text-align: center; border: 1px solid #bdbdbd;\">Cliente A</td>\r\n" + 
		"                        <td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">200,00</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background: #f4f4f4; height:25px;\">\r\n" + 
		"                      	<td style=\"text-align: center; border: 1px solid #bdbdbd;\">Cliente B</td>\r\n" + 
		"                        <td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">350,00</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"height:25px;\">\r\n" + 
		"                      	<td style=\"text-align: center; border: 1px solid #bdbdbd;\">Cliente C</td>\r\n" + 
		"                        <td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">1.350,00</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background: #f4f4f4; height:25px;\">\r\n" + 
		"                      	<th style=\"text-align: center; border: 1px solid #bdbdbd;\">Total</th>\r\n" + 
		"                        <th style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">5.840,38</th>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"              </table>\r\n" + 
		"        \r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px;\">\r\n" + 
		"              <p>Para mais detalhes, acesse o <span style=\"color: #1e94d2; font-weight: bold;\">Kerubin</span> <a href=\"#\">clicando aqui</a>.</p>\r\n" + 
		"             Abraços,<br>Equipe Kerubin\r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"	</div>\r\n" + 
		"</div>";
		
		return html;
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
