package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("financeiro/contas_pagar/conciliacaoBancaria")
@Api(value = "ContaPagar", tags = {"ContaPagar"}, description = "Operations de conciliação bancária para o Contas a pagar")
public class ConciliacaoBancariaController {
	
	@Inject
	private ConciliacaoBancariaService conciliacaoBancariaService;
	
	@PostMapping("/verificarTransacoes")
	@ApiOperation(value = "Verifica transações que combinam com contas a pagar")
	public ResponseEntity<ConciliacaoBancariaDTO> verificarTransacoes(@RequestBody ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		ConciliacaoBancariaDTO result = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/aplicarConciliacaoBancaria")
	@ApiOperation(value = "Baixa contas relacionadas com transações de conciliação bancária")
	public ResponseEntity<ConciliacaoBancariaDTO> aplicarConciliacaoBancaria(@RequestBody ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		ConciliacaoBancariaDTO result = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		return ResponseEntity.ok(result);
	}
	

}
