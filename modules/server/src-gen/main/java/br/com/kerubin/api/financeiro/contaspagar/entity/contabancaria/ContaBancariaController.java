/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

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

		
import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaAutoComplete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/contas_pagar/entities/contaBancaria")
@Api(value = "ContaBancaria", tags = {"ContaBancaria"}, description = "Operations for Conta bancária")
public class ContaBancariaController {
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@Autowired
	ContaBancariaDTOConverter contaBancariaDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Conta bancária")
	public ResponseEntity<ContaBancaria> create(@Valid @RequestBody ContaBancaria contaBancaria) {
		ContaBancariaEntity contaBancariaEntity = contaBancariaService.create(contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Conta bancária")
	public ResponseEntity<ContaBancaria> read(@PathVariable java.util.UUID id) {
		try {
			ContaBancariaEntity contaBancariaEntity = contaBancariaService.read(id);
			return ResponseEntity.ok(contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates Conta bancária")
	public ResponseEntity<ContaBancaria> update(@PathVariable java.util.UUID id, @Valid @RequestBody ContaBancaria contaBancaria) {
		try {
			ContaBancariaEntity contaBancariaEntity = contaBancariaService.update(id, contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
			return ResponseEntity.ok(contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes Conta bancária")
	public void delete(@PathVariable java.util.UUID id) {
		contaBancariaService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Conta bancária")
	public PageResult<ContaBancaria> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable) {
		Page<ContaBancariaEntity> page = contaBancariaService.list(contaBancariaListFilter, pageable);
		List<ContaBancaria> content = page.getContent().stream().map(pe -> contaBancariaDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<ContaBancaria> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Conta bancária with a query param")
	public Collection<ContaBancariaAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/contaBancariaNumeroContaAutoComplete")
	@ApiOperation(value = "Retrieves a list of Conta bancária with a query param")
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaNumeroContaAutoComplete> result = contaBancariaService.contaBancariaNumeroContaAutoComplete(query);
		return result;
	}
	
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/agenciaBancariaAgenciaAutoComplete")
	@ApiOperation(value = "Retrieves a list of AgenciaBancariaAutoComplete by query agenciaBancariaAgenciaAutoComplete over ContaBancaria with a query param")
	public Collection<AgenciaBancariaAutoComplete> agenciaBancariaAgenciaAutoComplete(@RequestParam("query") String query) {
		Collection<AgenciaBancariaAutoComplete> result = contaBancariaService.agenciaBancariaAgenciaAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
