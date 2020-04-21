package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
	
	private UUID id;
	
	private String trnId;
	
	private LocalDate trnData;
	
	private String trnHistorico;
	
	private String trnDocumento;
	
	private TipoTransacao trnTipo;
	
	private BigDecimal trnValor;
	
	private ConciliacaoBancariaLookupResult conciliacaoBancaria;
	
	////
	private UUID tituloConciliadoId;
    private String tituloConciliadoDesc;
    private BigDecimal tituloConciliadoValor;    
    private LocalDate tituloConciliadoDataVen;
    private LocalDate tituloConciliadoDataPag;
    private PlanoContaDTO tituloPlanoContas;
    private SituacaoConciliacaoTrn situacaoConciliacaoTrn;
    private LocalDate dataConciliacao;
    
    @Builder.Default
    private Boolean tituloConciliadoMultiple = false;
    
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
