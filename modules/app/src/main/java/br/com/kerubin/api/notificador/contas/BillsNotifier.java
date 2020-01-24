package br.com.kerubin.api.notificador.contas;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_NOTIFICADOR;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_NOTIFICADOR_APP_PWD;
import static br.com.kerubin.api.servicecore.mail.MailUtils.EMAIL_KERUBIN_NOTIFICADOR_PERSONAL;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatDate;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatNumber;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getFirstName;
import static br.com.kerubin.api.servicecore.util.CoreUtils.getStringAlternate;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
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

import br.com.kerubin.api.financeiro.contaspagar.ServiceConfig;
import br.com.kerubin.api.notificador.model.CaixaMovimentoItem;
import br.com.kerubin.api.notificador.model.ContasPagarHojeResumo;
import br.com.kerubin.api.notificador.model.ContasPagarHojeResumoCompleto;
import br.com.kerubin.api.notificador.model.ContasPagarSituacaoDoAnoSum;
import br.com.kerubin.api.notificador.model.ContasReceberHojeResumo;
import br.com.kerubin.api.notificador.model.ContasReceberHojeResumoCompleto;
import br.com.kerubin.api.notificador.model.ContasReceberSituacaoDoAnoSum;
import br.com.kerubin.api.notificador.model.FinanceiroResumoData;
import br.com.kerubin.api.notificador.model.SysUser;
import br.com.kerubin.api.servicecore.mail.MailInfo;
import br.com.kerubin.api.servicecore.mail.MailSender;
import br.com.kerubin.api.servicecore.util.BooleanWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BillsNotifier {
	
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final int CONTA_MAX_ITEMS = 5;
	private static final int MAX_RETRIES = 5;
	
	public static final String FINANCEIRO_CONTASPAGAR_SERVICE = "financeiro-contaspagar/financeiro/contas_pagar/";
	public static final String FINANCEIRO_CONTASRECEBER_SERVICE = "financeiro-contasreceber/financeiro/contas_receber/";
	public static final String FINANCEIRO_FLUXOCAIXA_SERVICE = "financeiro-fluxocaixa/financeiro/fluxo_caixa/";
	public static final String SECURITY_AUTHORIZATION_SERVICE = "security-authorization/security/authorization/";
	public static final String HTTP = "http://";
	public static final String DASHBOARD = "dashboard";
	private static final String KERUBIN_LINK = "<span style=\"color: #1e94d2; font-weight: bold;\"><a href=\"{0}\">Kerubin</a></span>";
	
	@Inject
	private RestTemplate restTemplate;
	
	@Inject
	private MailSender mailSender;
	
	@Inject
	private ServiceConfig serviceConfig;
	
	private String getKerubinLink() {
		return MessageFormat.format(KERUBIN_LINK, serviceConfig.getAllowOrigin());
	}
	

	// @Scheduled(fixedDelay = 1000 * 20)
	@Scheduled(cron = "0 0 0 * * *", zone = TIME_ZONE)
	public UUID executeNotifyUsersAboutTheBills() {
		UUID ticket = UUID.randomUUID();
		tryToNotifyUsersAboutTheBillsAssync(ticket);
		return ticket;
	}
	
	private void tryToNotifyUsersAboutTheBillsAssync(UUID ticket) {
		CompletableFuture
			.supplyAsync(() -> loadUsersWithTenants())
			.thenAccept(usersLoaded -> notifyUsersAboutTheBills(usersLoaded));
		
	}

	private List<SysUser> loadUsersWithTenants() {		
		String url = HTTP + SECURITY_AUTHORIZATION_SERVICE + "/" + "entities/sysUser" + "/listActiveUsers";
		
		log.info("Loading users from: {}...", url);
		
		ResponseEntity<List<SysUser>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<SysUser>>() {
		});
		
		List<SysUser> users = response.getBody();
		int userCount = isNotEmpty(users) ? users.size() : 0;
		
		log.info("Loaded {} users from: {}.", userCount, url);
		
		return users;
	}

	private void notifyUsersAboutTheBills(List<SysUser> users) {
		log.info("Starting ALL users notification about bills...");
		
		if (isEmpty(users)) {
			log.warn("Users list is null or empty at notifyUsersAboutTheBills.");
			return;
		}
		
		List<SysUser> distinctUsers = users.stream()
				.filter(distinctByKey(user -> user.getTenant().getId()))
				.collect(Collectors.toList());
		
		log.info("Will notifyUsersAboutTheBills for {} users.", users.size());
		distinctUsers.forEach(userAndTenant -> {
			log.info("Notifying bills for tenant: {}...", userAndTenant.getTenant().getName());
			notifyBillsForTenant(userAndTenant, users);
		});

		log.info("DONE ALL users notification about bills.");
	}

	private void notifyBillsForTenant(SysUser userAndTenant, List<SysUser> users) {
		Instant startNotifyBillsForTenant = Instant.now();
		
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
		
		System.out.println("Starting completable futures ...");
		Instant startCompletableFuture = Instant.now();
		
		CompletableFuture<ContasPagarHojeResumoCompleto> contasPagarHojeResumoCompletoFuture = CompletableFuture.supplyAsync(() -> getContasPagarHojeResumoCompleto(userAndTenant));
		
		CompletableFuture<ContasReceberHojeResumoCompleto> contasReceberHojeResumoCompletoFuture = CompletableFuture.supplyAsync(() -> getContasReceberHojeResumoCompleto(userAndTenant));
		
		CompletableFuture<List<CaixaMovimentoItem>> fluxoCaixaResumoMovimentacoesFuture = CompletableFuture.supplyAsync(() -> getFluxoCaixaResumoMovimentacoes(userAndTenant));
		
	    
		FinanceiroResumoData financeiroResumoData = CompletableFuture.allOf(contasPagarHojeResumoCompletoFuture, 
				contasReceberHojeResumoCompletoFuture, 
				fluxoCaixaResumoMovimentacoesFuture)
				.thenApply(ignored -> {
					
					return combineFinanceiroResumoData(
						contasPagarHojeResumoCompletoFuture.join(),
						contasReceberHojeResumoCompletoFuture.join(),
						fluxoCaixaResumoMovimentacoesFuture.join());
				}
					).join();
		
		Instant finishCompletableFuture = Instant.now();
		long timeElapsedCompletableFuture = Duration.between(startCompletableFuture, finishCompletableFuture).toMillis();
		log.info("Time elapsed in CompletableFuture:{} sec.", timeElapsedCompletableFuture / 1000);
		
		// Filtra apenas pelos usuários do tenant para envio das informações.
		List<SysUser> usersOfTenant = users.stream()
				.filter(it -> it.getTenant().getId().equals(userAndTenant.getTenant().getId()))
				.collect(Collectors.toList());
		
		Instant startNotifyBillsForUser = Instant.now();
		
		notifyBillsForUser(usersOfTenant, 
				financeiroResumoData.getContasPagarHojeResumoCompleto(), 
				financeiroResumoData.getContasReceberHojeResumoCompleto(), 
				financeiroResumoData.getCaixaMovimentoItens());
		
		/*usersOfTenant.forEach(user -> {
			CompletableFuture.runAsync( () -> notifyBillsForUser(user, 
				financeiroResumoData.getContasPagarHojeResumoCompleto(), 
				financeiroResumoData.getContasReceberHojeResumoCompleto(), 
				financeiroResumoData.getCaixaMovimentoItens()));
			});*/
		
		Instant finishNotifyBillsForUser = Instant.now();
		long timeElapsedNotifyBillsForUser = Duration.between(startNotifyBillsForUser, finishNotifyBillsForUser).toMillis();
		log.info("Time elapsed in NotifyBillsForUser:{} sec.", timeElapsedNotifyBillsForUser / 1000);
		
		Instant finishNotifyBillsForTenant = Instant.now();
		long timeElapsedNotifyBillsForTenant = Duration.between(startNotifyBillsForTenant, finishNotifyBillsForTenant).toMillis();
		log.info("Time elapsed in notifyBillsForTenant:{} sec.", timeElapsedNotifyBillsForTenant / 1000);
		
		log.info("DONE notifyBillsForTenant for tenant: {}.", tenant);
	}
	
	private void notifyBillsForUser(List<SysUser> users, 
			ContasPagarHojeResumoCompleto contasPagarHojeResumoCompleto,
			ContasReceberHojeResumoCompleto contasReceberHojeResumoCompleto,
			List<CaixaMovimentoItem> fluxoCaixaResumoMovimentacoes) {
		
		
		MailInfo from = new MailInfo(EMAIL_KERUBIN_NOTIFICADOR, EMAIL_KERUBIN_NOTIFICADOR_PERSONAL, EMAIL_KERUBIN_NOTIFICADOR_APP_PWD);
		List<MailInfo> recipients = users.stream().map(user -> new MailInfo(user.getEmail(), user.getName(), null)).collect(Collectors.toList());
		
		log.info("Notifying bills for users {} ...", recipients);
		
		//List<String> recipients = Arrays.asList(user.getEmail());
		String subsject = "Kerubin - Resumo das contas";
		String message = null;
		try {
			message = buildNotificationBillsForUserMessage(users, 
					contasPagarHojeResumoCompleto, 
					contasReceberHojeResumoCompleto,
					fluxoCaixaResumoMovimentacoes);
		} catch(Exception e) {
			log.error(MessageFormat.format("Erro ao gerar mensagem para enviado notificação de contas para: {0}. Erro: {1}", recipients, e.getMessage()), e);
		}
		
		if (isNotEmpty(message)) {
			try {
				mailSender.sendMail(from, recipients, subsject, message);
			} catch (Exception e) {
				log.error(MessageFormat.format("Erro enviado e-mail de notificação de contas para: {0}. Erro: {1}", recipients, e.getMessage()), e);
			}
		}
		
		
		log.info("DONE notify bills for users {} ...", recipients);
	}
	
	private FinanceiroResumoData combineFinanceiroResumoData(ContasPagarHojeResumoCompleto contasPagarHojeResumoCompleto, 
			ContasReceberHojeResumoCompleto contasReceberHojeResumoCompleto,
			List<CaixaMovimentoItem> caixaMovimentoItens
			) {
		
		FinanceiroResumoData result = FinanceiroResumoData
				.builder()
				.contasPagarHojeResumoCompleto(contasPagarHojeResumoCompleto)
				.contasReceberHojeResumoCompleto(contasReceberHojeResumoCompleto)
				.caixaMovimentoItens(caixaMovimentoItens)
				.build();
		
		return result;
	}
	
	/*private void notifyBillsForUser_OLD(SysUser user, 
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
	}*/
	
	private String buildListaContasPagarHoje(ContasPagarHojeResumoCompleto cp) {
		List<ContasPagarHojeResumo> cpHojeList = cp.getContasPagarHojeResumo();
		int count = cpHojeList.size();
		
		if (count > CONTA_MAX_ITEMS) {
			cpHojeList = cpHojeList.subList(0, CONTA_MAX_ITEMS);
		}
		
		StringBuilder sb = new StringBuilder();
		
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
				sb.append("<td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">").append(format(conta.getValor())).append("</td>");
				sb.append("</tr>");
				
			} // for
			
			if (count > CONTA_MAX_ITEMS) {
				sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
				sb.append("<th colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">")
					.append("Tem mais ")
					.append(count - CONTA_MAX_ITEMS)
					.append(" contas.")
					.append(" Para detalhes acesso o ")
					.append(getKerubinLink())					
					.append("</th>");
				sb.append("</tr>");
			}
			
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<th colspan=\"3\" style=\"text-align: right; padding-right: 5px; border: 1px solid #bdbdbd;\">").append("Total").append("</th>");
			sb.append("<th style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">").append(format(cp.getContasPagarHojeResumoSum())).append("</th>");
			sb.append("</tr>");
		}
		else {
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<td colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("<strong>Não há contas a pagar para hoje.</strong>").append("</td>");
			sb.append("</tr>");
		}
		sb.append(" </table>");
		
		return sb.toString();
	}
	
	private String buildListaFluxoCaixaResumoMovimentacoes(List<CaixaMovimentoItem> fc) {
		StringBuilder sb = new StringBuilder();
		
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
			values[0][i] = format(item.getCredito());
			values[1][i] = "-" + format(item.getDedito());
			values[2][i] = format(item.getSaldo());
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
				sb.append("<td style=\"text-align: right; border: 1px solid #bdbdbd; padding-right: 5px;\">").append(format(conta.getValor())).append("</td>");
				sb.append("</tr>");
				
			} // for
			
			if (count > CONTA_MAX_ITEMS) {
				sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
				
				sb.append("<th colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">")
				.append("Tem mais ")
				.append(count - CONTA_MAX_ITEMS)
				.append(" contas.")
				.append(" Para detalhes acesso o ")
				.append(getKerubinLink())					
				.append("</th>");
				
				sb.append("</tr>");
			}
			
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<th colspan=\"3\" style=\"text-align: right; padding-right: 5px; border: 1px solid #bdbdbd;\">").append("Total").append("</th>");
			sb.append("<th style=\"text-align: right; padding-right: 5px; border: 1px solid #bdbdbd;\">").append(format(cr.getContasReceberHojeResumoSum())).append("</th>");
			sb.append("</tr>");
		}
		else {
			sb.append("<tr").append(getStringAlternate(trTrue, trFalse, bw)).append(">");
			sb.append("<td colspan=\"4\" style=\"text-align: center; border: 1px solid #bdbdbd;\">").append("<strong>Não há contas a receber para hoje.</strong>").append("</td>");
			sb.append("</tr>");
		}
		sb.append(" </table>");
		
		return sb.toString();
	}
	
	private String buildNotificationBillsForUserMessage(List<SysUser> users,
			ContasPagarHojeResumoCompleto cp,
			ContasReceberHojeResumoCompleto cr,
			List<CaixaMovimentoItem> fc) {
		
		ContasPagarSituacaoDoAnoSum cpResumoSum = cp.getContasPagarSituacaoDoAnoSum();
		
		ContasReceberSituacaoDoAnoSum crResumoSum = cr.getContasReceberSituacaoDoAnoSum();
		
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
		
		StringBuilder saudacao = new StringBuilder("Olá, ");
		if (users.size() == 1) {
			SysUser user = users.get(0);
			saudacao.append("<span style=\"color:#1e94d2;\">").append(getFirstName(user.getName())).append("</span>!<br>Segue o resumo financeiro das suas contas.");
		}
		else {
			saudacao.append("segue o resumo financeiro das suas contas.");
		}
		
		String html = "<div style=\"float: left; border: 1px solid #d9d9d9; background: #F7F7F7; margin: 0 auto; width:100%; font-family: Helvetica,Arial,sans-serif; padding-left: 5px; padding-right: 5px;\">\r\n" + 
		"	<div style=\"height: 100%; display:table; max-width:800px; width:100%; margin: 0 auto; margin-top: 20px; margin-bottom: 20px;\">\r\n" + 
		"		<div style=\"display:table-row;background: #1e94d2;\">\r\n" + 
		"			<div style=\"border:1px solid #0586d3; border-bottom: 0px; text-align:center;vertical-align:middle; display:table-cell; background:  #1e94d2; width: 100%; color:#fff; height: 60px; font-size: 1.5em; font-weight: bold;\"><img alt=\"Kerubin\" src=\"https://i.ibb.co/DRRnWT1/logo.jpg\"></div>\r\n" + 
		"		</div>\r\n" + 
		"		<div style=\"display:table-row;\">\r\n" + 
		"			<div style=\"border:1px solid #d9d9d9;text-align:center;vertical-align:middle; display:table-cell; background: #fff; width: 100%;  height: 60px; font-size: 1.5em; font-weight: bold; padding-top: 20px; padding-bottom: 20px;\">\r\n" + 
		"              " + saudacao.toString() + "\r\n" + 
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
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(cpResumoSum.getValorVencido()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fdbf8f; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar hoje:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(cpResumoSum.getValorVenceHoje()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#feffaa; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar amanhã:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(cpResumoSum.getValorVenceAmanha()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#dfdfdf; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A pagar nos próximos 7 dias:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(cpResumoSum.getValorVenceProximos7Dias()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#a8eca5; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">Pago este mês:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(cpResumoSum.getValorPagoMesAtual()) + "</td>\r\n" + 
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
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(crResumoSum.getValorVencido()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#fdbf8f; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber hoje:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(crResumoSum.getValorVenceHoje()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#feffaa; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber amanhã:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(crResumoSum.getValorVenceAmanha()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#dfdfdf; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">A receber nos próximos 7 dias:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(crResumoSum.getValorVenceProximos7Dias()) + "</td>\r\n" + 
		"                	</tr>\r\n" + 
		"                \r\n" + 
		"                	<tr style=\"background:#a8eca5; height:25px; height: 30px;\">\r\n" + 
		"                      	<td style=\"text-align: right;  padding-right: 5px;\">Recebido este mês:</td>\r\n" + 
		"                        <td style=\"text-align: right; padding-right: 5px;\">" + format(crResumoSum.getValorPagoMesAtual()) + "</td>\r\n" + 
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
		"              <p>Para mais detalhes, acesse o " + getKerubinLink() + ".</p>\r\n" + 
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
		
		System.out.println("getContasPagarHojeResumoCompleto Thread:" + Thread.currentThread().getName());
		
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		String url = HTTP + FINANCEIRO_CONTASPAGAR_SERVICE + "/" + DASHBOARD + "/getContasPagarHojeResumoCompleto";
		
		String logMsg = "Tenant: " + tenant + ", username: " + username + ", URL: " + url;
		
		
		log.info("Starting getContasPagarHojeResumoCompleto for: {}...", logMsg);
		
        HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
        ResponseEntity<ContasPagarHojeResumoCompleto> response = null;
        
        int attempts = 0;
		boolean success = false;
		while (!success && attempts < MAX_RETRIES) {
			attempts++;
			try {
				log.info("Trying {} attempts getting data from: {}...", attempts, logMsg);
				
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						new ParameterizedTypeReference<ContasPagarHojeResumoCompleto>() {
				});
				
				success = true;
				
			} catch (Exception e) {
				log.error(attempts + " errors getting data from: " + logMsg, e);
			}
		} // while
		
		if (!success) {
			log.warn("FAIL with " + attempts + " attempts getting data from: " + logMsg);
			return null;
		}
		
		log.info("SUCCESS with " + attempts + " attempts getting data from: " + logMsg);
		
		ContasPagarHojeResumoCompleto result = response.getBody();
		
		log.info("Result at getContasPagarHojeResumoCompleto for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE with SUCCESS getContasPagarHojeResumoCompleto for tenant: {} and user: {}...", tenant,  username);
		return result;
	}
	
	private ContasReceberHojeResumoCompleto getContasReceberHojeResumoCompleto(SysUser userAndTenant) {
		
		System.out.println("getContasReceberHojeResumoCompleto Thread:" + Thread.currentThread().getName());
		
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		String url = HTTP + FINANCEIRO_CONTASRECEBER_SERVICE + "/" + DASHBOARD + "/getContasReceberHojeResumoCompleto";
		
		String logMsg = "Tenant: " + tenant + ", username: " + username + ", URL: " + url;
		
		log.info("Starting getContasReceberHojeResumoCompleto from: {}...", logMsg);
		
		
		HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
        
		
		//////////////
		ResponseEntity<ContasReceberHojeResumoCompleto> response = null;
		int attempts = 0;
		boolean success = false;
		while (!success && attempts < MAX_RETRIES) {
			attempts++;
			try {
				log.info("Trying {} attempts getting data from: {}...", attempts, logMsg);
				
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						new ParameterizedTypeReference<ContasReceberHojeResumoCompleto>() {
				});
				
				success = true;
				
			} catch (Exception e) {
				log.error(attempts + " errors getting data from: " + logMsg, e);
			}
		} // while
		
		if (!success) {
			log.warn("FAIL with " + attempts + " attempts getting data from: " + logMsg);
			return null;
		}
		
		log.info("SUCCESS with " + attempts + " attempts getting data from: " + logMsg);
		//////////////
		
		
		ContasReceberHojeResumoCompleto result = response.getBody();
		
		log.info("Result at getContasReceberHojeResumoCompleto for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		log.info("DONE getContasReceberHojeResumoCompleto for tenant: {} and user: {}...", tenant,  username);
		return result;
	}
	
	
	private List<CaixaMovimentoItem> getFluxoCaixaResumoMovimentacoes(SysUser userAndTenant) {
		
		System.out.println("getFluxoCaixaResumoMovimentacoes Thread:" + Thread.currentThread().getName());
		
		String tenant = userAndTenant.getTenant().getName();
		String username = userAndTenant.getEmail();
		String url = HTTP + FINANCEIRO_FLUXOCAIXA_SERVICE + "/" + DASHBOARD + "/getFluxoCaixaResumoMovimentacoes";
		
		String logMsg = "Tenant: " + tenant + ", username: " + username + ", URL: " + url;
		
		log.info("Starting getFluxoCaixaResumoMovimentacoes from: {}...", logMsg);
		
		HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
		
		//////////////
		ResponseEntity<List<CaixaMovimentoItem>> response = null;
		int attempts = 0;
		boolean success = false;
		while (!success && attempts < MAX_RETRIES) {
			attempts++;
			try {
				log.info("Trying {} attempts getting data from: {}...", attempts, logMsg);
				
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						new ParameterizedTypeReference<List<CaixaMovimentoItem>>() {
				});
				
				success = true;
				
			} catch (Exception e) {
				log.error(attempts + " errors getting data from: " + logMsg, e);
			}
		} // while
		
		if (!success) {
			log.warn("FAIL with " + attempts + " attempts getting data from: " + logMsg);
			return null;
		}
		
		log.info("SUCCESS with " + attempts + " attempts getting data from: " + logMsg);
		//////////////
		
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
	
	public String format(BigDecimal value) {
		return formatNumber(value);
	}
	
}
