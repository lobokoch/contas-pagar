package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConciliacaoTransacaoDTO {
	
	private java.util.UUID id;
	
	private String trnId;
	
	private java.time.LocalDate trnData;
	
	private String trnHistorico;
	
	private String trnDocumento;
	
	private TipoTransacao trnTipo;
	
	private java.math.BigDecimal trnValor;
	
	private ConciliacaoBancariaLookupResult conciliacaoBancaria;
	
	////
	private java.util.UUID tituloConciliadoId;
    
    private String tituloConciliadoDesc;
    
    private PlanoContaDTO tituloPlanoContas;
    
    private SituacaoConciliacaoTrn situacaoConciliacaoTrn;
    
    private java.time.LocalDate dataConciliacao;
    
    /////
    private List<ConciliacaoTransacaoTituloDTO> conciliacaoTransacaoTitulosDTO;
	
	private Boolean conciliadoComErro;
	
	private String conciliadoMsg;
	
	public boolean isCredito() {
		return TipoTransacao.CREDITO.equals(trnTipo);
	}
	
	public boolean isDebito() {
		return TipoTransacao.DEBITO.equals(trnTipo);
	}

}
