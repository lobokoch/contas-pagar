/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.com.kerubin.api.financeiro.contaspagar.common.PageResult;

		
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/contas_pagar/entities/contaPagar")
@Api(value = "ContaPagar", tags = {"ContaPagar"}, description = "Operations for Contas a pagar")
public class ContaPagarController {
	
	@Autowired
	private ContaPagarService contaPagarService;
	
	@Autowired
	ContaPagarDTOConverter contaPagarDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Contas a pagar")
	public ResponseEntity<ContaPagar> create(@Valid @RequestBody ContaPagar contaPagar) {
		ContaPagarEntity contaPagarEntity = contaPagarService.create(contaPagarDTOConverter.convertDtoToEntity(contaPagar));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaPagarDTOConverter.convertEntityToDto(contaPagarEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Contas a pagar")
	public ResponseEntity<ContaPagar> read(@PathVariable java.util.UUID id) {
		try {
			ContaPagarEntity contaPagarEntity = contaPagarService.read(id);
			return ResponseEntity.ok(contaPagarDTOConverter.convertEntityToDto(contaPagarEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates Contas a pagar")
	public ResponseEntity<ContaPagar> update(@PathVariable java.util.UUID id, @Valid @RequestBody ContaPagar contaPagar) {
		try {
			ContaPagarEntity contaPagarEntity = contaPagarService.update(id, contaPagarDTOConverter.convertDtoToEntity(contaPagar));
			return ResponseEntity.ok(contaPagarDTOConverter.convertEntityToDto(contaPagarEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes Contas a pagar")
	public void delete(@PathVariable java.util.UUID id) {
		contaPagarService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Contas a pagar")
	public PageResult<ContaPagar> list(ContaPagarListFilter contaPagarListFilter, Pageable pageable) {
		Page<ContaPagarEntity> page = contaPagarService.list(contaPagarListFilter, pageable);
		List<ContaPagar> content = page.getContent().stream().map(pe -> contaPagarDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<ContaPagar> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Contas a pagar with a query param")
	public Collection<ContaPagarAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarAutoComplete> result = contaPagarService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/contaPagarDescricaoAutoComplete")
	@ApiOperation(value = "Retrieves a list of Contas a pagar with a query param")
	public Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarDescricaoAutoComplete> result = contaPagarService.contaPagarDescricaoAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaPagarHistConcBancariaAutoComplete")
	@ApiOperation(value = "Retrieves a list of Contas a pagar with a query param")
	public Collection<ContaPagarHistConcBancariaAutoComplete> contaPagarHistConcBancariaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarHistConcBancariaAutoComplete> result = contaPagarService.contaPagarHistConcBancariaAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaPagarAgrupadorAutoComplete")
	@ApiOperation(value = "Retrieves a list of Contas a pagar with a query param")
	public Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarAgrupadorAutoComplete> result = contaPagarService.contaPagarAgrupadorAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaPagarSumFields")
	@ApiOperation(value = "Retrieves a sum of contaPagarSumFields filtering by contaPagarListFilter")
	public ContaPagarSumFields getContaPagarSumFields(ContaPagarListFilter contaPagarListFilter) {
		ContaPagarSumFields result = contaPagarService.getContaPagarSumFields(contaPagarListFilter);
		return result;
	}
	
	
	@PutMapping("/actionBaixarContaComDataPagamentoHoje/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Executes the action actionBaixarContaComDataPagamentoHoje")
	public void actionBaixarContaComDataPagamentoHoje(@PathVariable java.util.UUID id) {
		try {
			contaPagarService.actionBaixarContaComDataPagamentoHoje(id);
		}
		catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PutMapping("/actionBaixarContaComDataPagamentoIgualDataVenciento/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Executes the action actionBaixarContaComDataPagamentoIgualDataVenciento")
	public void actionBaixarContaComDataPagamentoIgualDataVenciento(@PathVariable java.util.UUID id) {
		try {
			contaPagarService.actionBaixarContaComDataPagamentoIgualDataVenciento(id);
		}
		catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PutMapping("/actionEstornarPagamentoContaComUmClique/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Executes the action actionEstornarPagamentoContaComUmClique")
	public void actionEstornarPagamentoContaComUmClique(@PathVariable java.util.UUID id) {
		try {
			contaPagarService.actionEstornarPagamentoContaComUmClique(id);
		}
		catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PostMapping("/actionFazerCopiasContaPagar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Executes the action actionFazerCopiasContaPagar")
	public void actionFazerCopiasContaPagar(@Valid @RequestBody ContaPagarMakeCopies contaPagarMakeCopies) {
		try {
			contaPagarService.actionFazerCopiasContaPagar(contaPagarMakeCopies);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/planoContaPlanoContasAutoComplete")
	@ApiOperation(value = "Retrieves a list of PlanoContaAutoComplete by query planoContaPlanoContasAutoComplete over ContaPagar with a query param")
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaAutoComplete> result = contaPagarService.planoContaPlanoContasAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/contaBancariaContaBancariaAutoComplete")
	@ApiOperation(value = "Retrieves a list of ContaBancariaAutoComplete by query contaBancariaContaBancariaAutoComplete over ContaPagar with a query param")
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaAutoComplete> result = contaPagarService.contaBancariaContaBancariaAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/cartaoCreditoCartaoCreditoAutoComplete")
	@ApiOperation(value = "Retrieves a list of CartaoCreditoAutoComplete by query cartaoCreditoCartaoCreditoAutoComplete over ContaPagar with a query param")
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(@RequestParam("query") String query) {
		Collection<CartaoCreditoAutoComplete> result = contaPagarService.cartaoCreditoCartaoCreditoAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/fornecedorFornecedorAutoComplete")
	@ApiOperation(value = "Retrieves a list of FornecedorAutoComplete by query fornecedorFornecedorAutoComplete over ContaPagar with a query param")
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(@RequestParam("query") String query) {
		Collection<FornecedorAutoComplete> result = contaPagarService.fornecedorFornecedorAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
