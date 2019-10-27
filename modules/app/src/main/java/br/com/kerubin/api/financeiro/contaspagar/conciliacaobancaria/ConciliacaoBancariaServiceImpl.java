package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.QContaPagarEntity;
import br.com.kerubin.api.servicecore.util.CoreUtils;

@Service
public class ConciliacaoBancariaServiceImpl implements ConciliacaoBancariaService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (conciliacaoBancariaDTO == null || conciliacaoBancariaDTO.getTransacoes() == null) {
			return conciliacaoBancariaDTO;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QContaPagarEntity qContaPagar = QContaPagarEntity.contaPagarEntity;
		
		conciliacaoBancariaDTO.getTransacoes().forEach(transacao -> {
			
			BooleanBuilder filtroDados = new BooleanBuilder();
			filtroDados
			.and(qContaPagar.numDocConcBancaria.eq(transacao.getTrnDocumento()))
			.or(qContaPagar.valor.eq(transacao.getTrnValor()))
			.or(qContaPagar.valorPago.eq(transacao.getTrnValor()));
			
			LocalDate from = transacao.getTrnData().minusDays(30);
			LocalDate to = transacao.getTrnData().plusDays(30);
			
			BooleanBuilder filtroPerido = new BooleanBuilder();
			filtroPerido.and(qContaPagar.dataVencimento.between(from, to)).or(qContaPagar.dataPagamento.between(from, to));
			
			BooleanBuilder where = new BooleanBuilder();
			where.and(filtroDados).and(filtroPerido);
			
			List<ContaPagarEntity> contas = query.selectFrom(qContaPagar).where(where).fetch();
			
			if (contas != null && !contas.isEmpty()) {
				ContaPagarEntity contaCandidata = contas.stream().filter(conta -> transacao.getTrnDocumento().equals(conta.getNumDocConcBancaria())).findFirst().orElse(null);
				
				if (contaCandidata == null) {
					contaCandidata = contas.stream().filter(conta -> CoreUtils.isEquals(transacao.getTrnValor(), conta.getValorPago())).findFirst().orElse(null);
				}
				
				if (contaCandidata == null) {
					contaCandidata = contas.stream().filter(conta -> CoreUtils.isEquals(transacao.getTrnValor(), conta.getValor())).findFirst().orElse(null);
				}
				
				if (contaCandidata != null) {
					transacao.setTituloConciliadoId(contaCandidata.getId());
					transacao.setTituloConciliadoDesc(contaCandidata.getDescricao());
					
					SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
					if (contaCandidata.getDataPagamento() != null) { // Já pago, baixado.
						if (contaCandidata.getNumDocConcBancaria() != null) { // Pagamento normal, sem conciliação
							situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIADO_CONTAS_PAGAR;
						}
						else {
							situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONTAS_PAGAR_BAIXADO_SEM_CONCILIACAO;
						}
					}
					else {
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIAR_CONTAS_PAGAR;
					}
					
					transacao.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn);
				}
			}
			
		});
		
		return conciliacaoBancariaDTO;
	}

}
