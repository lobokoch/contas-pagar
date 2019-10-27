package br.com.kerubin.api.financeiro.contaspagar.conciliacaobancaria;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("financeiro/contas_pagar/conciliacaoBancaria")
@Api(value = "ContaPagar", tags = {"ContaPagar"}, description = "Operations de conciliação bancária para o Contas a pagar")
public class ConciliacaoBancariaController {
	
	@Inject
	private ConciliacaoBancariaService conciliacaoBancariaService;
	
	@PostMapping("/verificarTransacoes")
	public ResponseEntity<ConciliacaoBancariaDTO> verificarTransacoes(@RequestBody ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		ConciliacaoBancariaDTO result = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		return ResponseEntity.ok(result);
	}
	

}
