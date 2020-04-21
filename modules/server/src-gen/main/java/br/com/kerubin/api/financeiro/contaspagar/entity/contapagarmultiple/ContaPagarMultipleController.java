/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagarmultiple;

import org.springframework.web.bind.annotation.RequestParam;
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

		
import br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor.FornecedorAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagarAutoComplete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/contas_pagar/entities/contaPagarMultiple")
@Api(value = "ContaPagarMultiple", tags = {"ContaPagarMultiple"}, description = "Operations for Registros de pagamento")
public class ContaPagarMultipleController {
	
	@Autowired
	private ContaPagarMultipleService contaPagarMultipleService;
	
	@Autowired
	ContaPagarMultipleDTOConverter contaPagarMultipleDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Registros de pagamento")
	public ResponseEntity<ContaPagarMultiple> create(@Valid @RequestBody ContaPagarMultiple contaPagarMultiple) {
		ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.create(contaPagarMultipleDTOConverter.convertDtoToEntity(contaPagarMultiple));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Registros de pagamento")
	public ResponseEntity<ContaPagarMultiple> read(@PathVariable java.util.UUID id) {
		try {
			ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.read(id);
			return ResponseEntity.ok(contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates Registros de pagamento")
	public ResponseEntity<ContaPagarMultiple> update(@PathVariable java.util.UUID id, @Valid @RequestBody ContaPagarMultiple contaPagarMultiple) {
		try {
			ContaPagarMultipleEntity contaPagarMultipleEntity = contaPagarMultipleService.update(id, contaPagarMultipleDTOConverter.convertDtoToEntity(contaPagarMultiple));
			return ResponseEntity.ok(contaPagarMultipleDTOConverter.convertEntityToDto(contaPagarMultipleEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes Registros de pagamento")
	public void delete(@PathVariable java.util.UUID id) {
		contaPagarMultipleService.delete(id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PostMapping("/deleteInBulk")
	@ApiOperation(value = "Delete a list of Registros de pagamento by ids.")
	public void deleteInBulk(@RequestBody java.util.List<java.util.UUID> idList) {
		contaPagarMultipleService.deleteInBulk(idList);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Registros de pagamento")
	public PageResult<ContaPagarMultiple> list(ContaPagarMultipleListFilter contaPagarMultipleListFilter, Pageable pageable) {
		Page<ContaPagarMultipleEntity> page = contaPagarMultipleService.list(contaPagarMultipleListFilter, pageable);
		List<ContaPagarMultiple> content = page.getContent().stream().map(pe -> contaPagarMultipleDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<ContaPagarMultiple> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Registros de pagamento with a query param")
	public Collection<ContaPagarMultipleAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarMultipleAutoComplete> result = contaPagarMultipleService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/contaPagarMultipleHistConcBancariaAutoComplete")
	@ApiOperation(value = "Retrieves a list of Registros de pagamento with a query param")
	public Collection<ContaPagarMultipleHistConcBancariaAutoComplete> contaPagarMultipleHistConcBancariaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarMultipleHistConcBancariaAutoComplete> result = contaPagarMultipleService.contaPagarMultipleHistConcBancariaAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaPagarMultipleSumFields")
	@ApiOperation(value = "Retrieves a sum of contaPagarMultipleSumFields filtering by contaPagarMultipleListFilter")
	public ContaPagarMultipleSumFields getContaPagarMultipleSumFields(ContaPagarMultipleListFilter contaPagarMultipleListFilter) {
		ContaPagarMultipleSumFields result = contaPagarMultipleService.getContaPagarMultipleSumFields(contaPagarMultipleListFilter);
		return result;
	}
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/fornecedorFornecedorAutoComplete")
	@ApiOperation(value = "Retrieves a list of FornecedorAutoComplete by query fornecedorFornecedorAutoComplete over ContaPagarMultiple with a query param")
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(@RequestParam("query") String query) {
		Collection<FornecedorAutoComplete> result = contaPagarMultipleService.fornecedorFornecedorAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/planoContaPlanoContasAutoComplete")
	@ApiOperation(value = "Retrieves a list of PlanoContaAutoComplete by query planoContaPlanoContasAutoComplete over ContaPagarMultiple with a query param")
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaAutoComplete> result = contaPagarMultipleService.planoContaPlanoContasAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/contaBancariaContaBancariaAutoComplete")
	@ApiOperation(value = "Retrieves a list of ContaBancariaAutoComplete by query contaBancariaContaBancariaAutoComplete over ContaPagarMultiple with a query param")
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaAutoComplete> result = contaPagarMultipleService.contaBancariaContaBancariaAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/cartaoCreditoCartaoCreditoAutoComplete")
	@ApiOperation(value = "Retrieves a list of CartaoCreditoAutoComplete by query cartaoCreditoCartaoCreditoAutoComplete over ContaPagarMultiple with a query param")
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(@RequestParam("query") String query) {
		Collection<CartaoCreditoAutoComplete> result = contaPagarMultipleService.cartaoCreditoCartaoCreditoAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly = true)
	@GetMapping("/contaPagarContaPagarAutoComplete")
	@ApiOperation(value = "Retrieves a list of ContaPagarAutoComplete by query contaPagarContaPagarAutoComplete over ContaPagarMultiple with a query param")
	public Collection<ContaPagarAutoComplete> contaPagarContaPagarAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarAutoComplete> result = contaPagarMultipleService.contaPagarContaPagarAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}