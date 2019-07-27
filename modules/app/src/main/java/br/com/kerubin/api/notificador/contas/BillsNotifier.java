package br.com.kerubin.api.notificador.contas;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;

import java.text.DecimalFormat;
import java.time.LocalDate;
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

import br.com.kerubin.api.notificador.model.CaixaMovimentoItem;
import br.com.kerubin.api.notificador.model.ContasPagarHojeResumo;
import br.com.kerubin.api.notificador.model.ContasPagarHojeResumoCompleto;
import br.com.kerubin.api.notificador.model.ContasPagarSituacaoDoAnoSum;
import br.com.kerubin.api.notificador.model.ContasReceberHojeResumo;
import br.com.kerubin.api.notificador.model.ContasReceberHojeResumoCompleto;
import br.com.kerubin.api.notificador.model.ContasReceberSituacaoDoAnoSum;
import br.com.kerubin.api.notificador.model.SysUser;
import br.com.kerubin.api.servicecore.mail.MailSender;
import br.com.kerubin.api.servicecore.util.BooleanWrapper;
import lombok.extern.slf4j.Slf4j;


import static br.com.kerubin.api.servicecore.util.CoreUtils.*;

@Slf4j
@Component
public class BillsNotifier {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final int CONTA_MAX_ITEMS = 5;
	
	public static final String FINANCEIRO_CONTASPAGAR_SERVICE = "financeiro-contaspagar";
	public static final String FINANCEIRO_CONTASRECEBER_SERVICE = "financeiro-contasreceber";
	public static final String FINANCEIRO_FLUXOCAIXA_SERVICE = "financeiro-fluxocaixa";
	public static final String SECURITY_AUTHORIZATION_SERVICE = "security-authorization";
	public static final String HTTP = "http://";
	public static final String DASHBOARD = "dashboard";
	public static final String KERUBIN_LINK = "<span style=\"color: #1e94d2; font-weight: bold;\"><a href=\"#\">Kerubin</a></span>";
	
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
		
		ContasPagarHojeResumoCompleto contasPagarHojeResumoCompleto = getContasPagarHojeResumoCompleto(userAndTenant);
		
		ContasReceberHojeResumoCompleto contasReceberHojeResumoCompleto = getContasReceberHojeResumoCompleto(userAndTenant);
		
		List<CaixaMovimentoItem> fluxoCaixaResumoMovimentacoes = getFluxoCaixaResumoMovimentacoes(userAndTenant);
		
		// Filtra apenas pelos usuários do tenant para envio das informações.
		List<SysUser> usersOfTenant = users.stream()
				.filter(it -> it.getTenant().getId().equals(userAndTenant.getTenant().getId()))
				.collect(Collectors.toList());
		
		usersOfTenant.forEach(user -> notifyBillsForUser(user, contasPagarHojeResumoCompleto, contasReceberHojeResumoCompleto, fluxoCaixaResumoMovimentacoes));
		
		
		log.info("DONE notifyBillsForTenant for tenant: {}.", tenant);
	}
	
	private void notifyBillsForUser(SysUser user, 
			ContasPagarHojeResumoCompleto contasPagarHojeResumoCompleto,
			ContasReceberHojeResumoCompleto contasReceberHojeResumoCompleto,
			List<CaixaMovimentoItem> fluxoCaixaResumoMovimentacoes) {
		log.info("Notifying bills for user name: {}, e-mail: {}, tenant: {} ...", user.getName(), user.getEmail(), user.getTenant());
		
		String from = MailSender.EMAIL_FROM_DEFAULT;
		List<String> recipients = Arrays.asList(user.getEmail());
		String subsject = "Kerubin - Resumo das contas";
		String message = buildNotificationBillsForUserMessage(user, 
				contasPagarHojeResumoCompleto, 
				contasReceberHojeResumoCompleto,
				fluxoCaixaResumoMovimentacoes);
		
		mailSender.sendMail(from, recipients, subsject, message);
		
		log.info("DONE notification bills for user name: {}, e-mail: {}, tenant: {} ...", user.getName(), user.getEmail(), user.getTenant());
	}
	
	private String buildListaContasPagarHoje(ContasPagarHojeResumoCompleto cp) {
		List<ContasPagarHojeResumo> cpHojeList = cp.getContasPagarHojeResumo();
		int count = cpHojeList.size();
		
		if (count > CONTA_MAX_ITEMS) {
			cpHojeList = cpHojeList.subList(0, CONTA_MAX_ITEMS);
		}
		
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		
		BooleanWrapper bw = new BooleanWrapper(true);
		String trTrue = " style=\"height:25px; background: #f4f4f4;\"";
		String trFalse = " style=\"height:25px; background: #ffffff;\"";
		
		sb.append("<table style=\"margin: auto; width: 90%; border: 1px solid #bdbdbd; border-collapse: collapse;\">");
		sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Conta").append("</th>");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Vencimento").append("</th>");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Dias atrazo").append("</th>");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Valor (R$)").append("</th>");
		sb.append("</tr>");
		if (count > 0) {
			for (int i = 0; i < cpHojeList.size(); i++) {
				ContasPagarHojeResumo conta = cpHojeList.get(i);
				sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
				sb.append("<td style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(conta.getDescricao()).append("</td>");
				sb.append("<td style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(formatDate(conta.getDataVencimento())).append("</td>");
				sb.append("<td style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(conta.getDiasEmAtraso()).append("</td>");
				sb.append("<td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">").append(df.format(conta.getValor())).append("</td>");
				sb.append("</tr>");
				
			} // for
			
			if (count > CONTA_MAX_ITEMS) {
				sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
				sb.append("<th colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">")
					.append("Tem mais ")
					.append(count - CONTA_MAX_ITEMS)
					.append(" contas.")
					.append(" Para detalhes acesso o ")
					.append(KERUBIN_LINK)					
					.append("</th>");
				sb.append("</tr>");
			}
			
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<th colspan=\"3\" style=\"text-align: right; padding-right: 5px; border: 1px solid #bdbdbd;\">").append("Total").append("</th>");
			sb.append("<th style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">").append(df.format(cp.getContasPagarHojeResumoSum())).append("</th>");
			sb.append("</tr>");
		}
		else {
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<td colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("<strong>Aproveite seu dia, não há contas pra pagar hoje ;-)</strong>").append("</td>");
			sb.append("</tr>");
		}
		sb.append(" </table>");
		
		return sb.toString();
	}
	
	private String buildListaFluxoCaixaResumoMovimentacoes(List<CaixaMovimentoItem> fc) {
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		
		BooleanWrapper bw = new BooleanWrapper(true);
		String trTrue = " style=\"height:25px; background: #f4f4f4;\"";
		String trFalse = " style=\"height:25px; background: #ffffff;\"";
		
		sb.append("<table style=\"margin: auto; width: 90%; border: 1px solid #bdbdbd; border-collapse: collapse;\">");
		
		// Mount the table headers.
		sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
		
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("").append("</th>");
		fc.forEach(item -> {
			sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(item.getDescricao()).append("</th>");
		});
		
		sb.append("</tr>");
		
		// Mount each table row
		final byte MAX_ROWS = 3;
		List<String> tipos = Arrays.asList("Créditos", "Débitos", "Saldo");
		List<String> bgColors = Arrays.asList("rgba(0, 191, 255, 0.4)", "rgba(255, 0, 0, 0.4)", "rgba(0, 206, 0, 0.4)");
		final int MAX_COLS = fc.size();
		String[][] values = new String[MAX_ROWS][MAX_COLS];
		for (int i = 0; i < fc.size(); i++) {
			CaixaMovimentoItem item = fc.get(i);
			values[0][i] = df.format(item.getCredito());
			values[1][i] = "-" + df.format(item.getDedito());
			values[2][i] = df.format(item.getSaldo());
		}
		
		for (int i = 0; i < MAX_ROWS; i++) {
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<td style=\"font-weight: bold; text-align: center; border: 1px solid #bdbdbd; background: ").append("#f4f4f4;"/*bgColors.get(i)*/).append("\">").append(tipos.get(i)).append("</td>");
			
			for (int j = 0; j < MAX_COLS; j++) {
				String value = values[i][j];
				sb.append("<td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px; ").append(i == 2 ? "font-weight: bold; " : "").append("background: ").append(bgColors.get(i)).append(";").append("\">").append(value).append("</td>");
			} //for j

			sb.append("</tr>");
		} // for
		
		
		
		sb.append(" </table>");
		
		return sb.toString();
	}
	
	private String buildListaContasReceberHoje(ContasReceberHojeResumoCompleto cr) {
		List<ContasReceberHojeResumo> crHojeList = cr.getContasReceberHojeResumo();
		int count = crHojeList.size();
		
		if (count > CONTA_MAX_ITEMS) {
			crHojeList = crHojeList.subList(0, CONTA_MAX_ITEMS);
		}
		
		StringBuilder sb = new StringBuilder();
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		
		BooleanWrapper bw = new BooleanWrapper(true);
		String trTrue = " style=\"height:25px; background: #f4f4f4;\"";
		String trFalse = " style=\"height:25px; background: #ffffff;\"";
		
		sb.append("<table style=\"margin: auto; width: 90%; border: 1px solid #bdbdbd; border-collapse: collapse;\">");
		sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Conta").append("</th>");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Vencimento").append("</th>");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Dias atrazo").append("</th>");
		sb.append("<th style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("Valor").append("</th>");
		sb.append("</tr>");
		if (count > 0) {
			for (int i = 0; i < crHojeList.size(); i++) {
				ContasReceberHojeResumo conta = crHojeList.get(i);
				sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
				sb.append("<td style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(conta.getDescricao()).append("</td>");
				sb.append("<td style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(formatDate(conta.getDataVencimento())).append("</td>");
				sb.append("<td style=\"text-align: center; border: 1px solid #bdbdbd;\">").append(conta.getDiasEmAtraso()).append("</td>");
				sb.append("<td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">").append(df.format(conta.getValor())).append("</td>");
				sb.append("</tr>");
				
			} // for
			
			if (count > CONTA_MAX_ITEMS) {
				sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
				
				sb.append("<th colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">")
				.append("Tem mais ")
				.append(count - CONTA_MAX_ITEMS)
				.append(" contas.")
				.append(" Para detalhes acesso o ")
				.append(KERUBIN_LINK)					
				.append("</th>");
				
				sb.append("</tr>");
			}
			
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<th colspan=\"3\" style=\"text-align: right; padding-right: 5px; border: 1px solid #bdbdbd;\">").append("Total").append("</th>");
			sb.append("<th style=\"text-align: right; padding-right: 5px; border: 1px solid #bdbdbd;\">").append(df.format(cr.getContasReceberHojeResumoSum())).append("</th>");
			sb.append("</tr>");
		}
		else {
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<td colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("<strong>Ainda não há contas a receber para hoje :(</strong>").append("</td>");
			sb.append("</tr>");
		}
		sb.append(" </table>");
		
		return sb.toString();
	}
	
	private String buildNotificationBillsForUserMessage(SysUser user,
			ContasPagarHojeResumoCompleto cp,
			ContasReceberHojeResumoCompleto cr,
			List<CaixaMovimentoItem> fc) {
		
		ContasPagarSituacaoDoAnoSum cpResumoSum = cp.getContasPagarSituacaoDoAnoSum();
		
		ContasReceberSituacaoDoAnoSum crResumoSum = cr.getContasReceberSituacaoDoAnoSum();
		
		DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
		String todayStr = formatDate(LocalDate.now());
		
		StringBuilder sb = new StringBuilder();
		sb.append("");
		
		sb.append("<!DOCTYPE html>\r\n");
		sb.append("<html lang=\"pt-br\">\r\n");
		sb.append("<head>\r\n");
		sb.append("<meta charset=\"UTF-8\">\r\n");
		sb.append("<title>Rerubin - Resumo das contas</title>\r\n");
		sb.append("</head>\r\n");
		sb.append("<body>\r\n");
		
		String html = "<div style=\"float: left; border: 1px solid #d9d9d9; background: #F7F7F7; margin: 0 auto; width:100%; font-family: Helvetica,Arial,sans-serif; padding-left: 5px; padding-right: 5px;\">\r\n" + 
		"	<div style=\"height: 100%; display:table; max-width:800px; width:100%; margin: 0 auto; margin-top: 20px; margin-bottom: 20px;\">\r\n" + 
		"		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; height: 60px; font-size: 1.5em; font-weight: bold;\"><img alt=\"Kerubin\" src=\"https://i.ibb.co/DRRnWT1/logo.jpg\"></div>\r\n" + 
		"		</div>\r\n" + 
		"		<div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9;text-align:center;vertical-align:middle; display:table-cell; background: #fff; width: 100%;  height: 60px; font-size: 1.5em; font-weight: bold; padding-top: 20px; padding-bottom: 20px;\">\r\n" + 
		"              Olá, <span style=\"color:#1e94d2;\">" + getFirstName(user.getName()) + "</span>!<br>Segue o resumo das suas contas.\r\n" + 
		"          </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      \r\n" + 
		"      		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #ff5722; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Resumo dos valores das contas a pagar (em R$)</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              \r\n" + 
		"              <table style=\"margin: auto; width: 60%\">\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fc7f8b; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right; padding-right: 5px;\">Em atraso:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(cpResumoSum.getValorVencido()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fdbf8f; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar hoje:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(cpResumoSum.getValorVenceHoje()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#feffaa; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar amanhã:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(cpResumoSum.getValorVenceAmanha()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#dfdfdf; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar nos próximos 7 dias:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(cpResumoSum.getValorVenceProximos7Dias()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#a8eca5; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">Pago este mês:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(cpResumoSum.getValorPagoMesAtual()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"              </table>              \r\n" + 
		"             \r\n" + 
		"        \r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"            		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #ff5722; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Contas a pagar para hoje (" + todayStr + ")</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px\">\r\n" 
		
		+ buildListaContasPagarHoje(cp) +
		

		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"\r\n" + 
		"<!--  CONTAS A RECEBER -->\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Resumo dos valores das contas a receber (em R$)</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              \r\n" + 
		"              <table style=\"margin: auto; width: 60%\">\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fc7f8b; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right; padding-right: 5px;\">Em atraso:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(crResumoSum.getValorVencido()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fdbf8f; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber hoje:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(crResumoSum.getValorVenceHoje()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#feffaa; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber amanhã:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(crResumoSum.getValorVenceAmanha()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#dfdfdf; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber nos próximos 7 dias:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(crResumoSum.getValorVenceProximos7Dias()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#a8eca5; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">Recebido este mês:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + df.format(crResumoSum.getValorPagoMesAtual()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"              </table>\r\n" + 
		"        \r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + // Begin Contas a Receber de hoje
		"       <div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Contas a Receber de hoje (" + todayStr + ")</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              "
		
		+ buildListaContasReceberHoje(cr) +
		
		"        \r\n" + 
		"        </div>\r\n" + 
		"	</div>\r\n" + 
		"\r\n" + 
		
		"      \r\n" + // Begin Resumo do Fluxo de Caixa
		"       <div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background: #008000; width: 100%; color:#fff; padding-top: 5px; padding-bottom: 5px;\">Resumo das movimentações do caixa (em R$)</div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top:0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px\">\r\n" + 
		"              "
		
		+ buildListaFluxoCaixaResumoMovimentacoes(fc) +
		
		"        \r\n" + 
		"        </div>\r\n" + 
		"	</div>\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"      \r\n" + 
		"      <div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9; border-top: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #fff; width: 100%; padding-top: 20px; padding-bottom: 20px;\">\r\n" + 
		"              <p>Para mais detalhes, acesse o " + KERUBIN_LINK + ".</p>\r\n" + 
		"             Abraços,<br>Equipe Kerubin\r\n" + 
		"        </div>\r\n" + 
		"		</div>\r\n" + 
		"      \r\n" + 
		"	</div>\r\n" + 
		"</div>\r\n";
		
		sb.append(html);
		
		sb.append("</body>\r\n");
		sb.append("</html>\r\n");
		
		html = sb.toString();
		// saveText(html, "D:\\bkp\\email.HTML");
		
		return html;
		
	}

	private ContasPagarHojeResumoCompleto getContasPagarHojeResumoCompleto(SysUser userAndTenant) {
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		
		log.info("Starting getContasPagarHojeResumoCompleto for tenant: {} and user: {}...", tenant,  username);
		String url = HTTP + FINANCEIRO_CONTASPAGAR_SERVICE + "/" + DASHBOARD + "/getContasPagarHojeResumoCompleto";
		
        HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);

		ResponseEntity<ContasPagarHojeResumoCompleto> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<ContasPagarHojeResumoCompleto>() {
				});
		
		ContasPagarHojeResumoCompleto result = response.getBody();
		
		log.info("Result at getContasPagarHojeResumoCompleto for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE getContasPagarHojeResumoCompleto for tenant: {} and user: {}...", tenant,  username);
		return result;
	}
	
	private ContasReceberHojeResumoCompleto getContasReceberHojeResumoCompleto(SysUser userAndTenant) {
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		
		log.info("Starting getContasReceberHojeResumoCompleto for tenant: {} and user: {}...", tenant,  username);
		
		String url = HTTP + FINANCEIRO_CONTASRECEBER_SERVICE + "/" + DASHBOARD + "/getContasReceberHojeResumoCompleto";
		
		HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
        
		ResponseEntity<ContasReceberHojeResumoCompleto> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<ContasReceberHojeResumoCompleto>() {
		});
		
		ContasReceberHojeResumoCompleto result = response.getBody();
		
		log.info("Result at getContasReceberHojeResumoCompleto for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE getContasReceberHojeResumoCompleto for tenant: {} and user: {}...", tenant,  username);
		return result;
	}
	
	
	private List<CaixaMovimentoItem> getFluxoCaixaResumoMovimentacoes(SysUser userAndTenant) {
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		
		String url = HTTP + FINANCEIRO_FLUXOCAIXA_SERVICE + "/" + DASHBOARD + "/getFluxoCaixaResumoMovimentacoes";
		
		log.info("Starting getFluxoCaixaResumoMovimentacoes for tenant: {} and user: {}, URL: {}...", tenant,  username, url);
		
		HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
		
		ResponseEntity<List<CaixaMovimentoItem>> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<CaixaMovimentoItem>>() {
		});
		
		List<CaixaMovimentoItem> result = response.getBody();
		
		log.info("Result at getFluxoCaixaResumoMovimentacoes for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE getFluxoCaixaResumoMovimentacoes for tenant: {} and user: {}...", tenant,  username);
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
