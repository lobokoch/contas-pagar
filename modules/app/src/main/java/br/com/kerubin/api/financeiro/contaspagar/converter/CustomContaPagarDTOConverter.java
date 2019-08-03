package br.com.kerubin.api.financeiro.contaspagar.converter;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagar;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarDTOConverter;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarEntity;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaEntity;

@Primary
@Component
public class CustomContaPagarDTOConverter extends ContaPagarDTOConverter {
	
	@Override
	public ContaPagar convertEntityToDto(ContaPagarEntity entity) {
		ContaPagar result = super.convertEntityToDto(entity);
		
		// TODO: usar cache no futuro.
		PlanoContaEntity planoContas = entity.getPlanoContas();
		if (isNotEmpty(planoContas)) {
			String descricao = getPlanoContasDescricao(planoContas);
			result.getPlanoContas().setDescricao(descricao);
		}
		return result;
	}
	
	private String getPlanoContasDescricao(PlanoContaEntity planoContas) {
		String descricao = "";
		if (isNotEmpty(planoContas)) { // Adjusts the field descricao of plano de contas and plano de contas pai.
			descricao = planoContas.getCodigo() + " - " + planoContas.getDescricao();
			PlanoContaEntity planoContasPai = planoContas.getPlanoContaPai();
			if (isNotEmpty(planoContasPai)) {
				descricao = planoContasPai.getCodigo() + " - " + planoContasPai.getDescricao() + " / " + descricao;
			}
		} 
		return descricao;
	}

}
