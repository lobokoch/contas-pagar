/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

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

		
import br.com.kerubin.api.financeiro.contaspagar.entity.banco.BancoAutoComplete;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/contas_pagar/entities/agenciaBancaria")
@Api(value = "AgenciaBancaria", tags = {"AgenciaBancaria"}, description = "Operations for Agência bancária")
public class AgenciaBancariaController {
	
	@Autowired
	private AgenciaBancariaService agenciaBancariaService;
	
	@Autowired
	AgenciaBancariaDTOConverter agenciaBancariaDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Agência bancária")
	public ResponseEntity<AgenciaBancaria> create(@Valid @RequestBody AgenciaBancaria agenciaBancaria) {
		AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.create(agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
		return ResponseEntity.status(HttpStatus.CREATED).body(agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Agência bancária")
	public ResponseEntity<AgenciaBancaria> read(@PathVariable java.util.UUID id) {
		try {
			AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.read(id);
			return ResponseEntity.ok(agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates Agência bancária")
	public ResponseEntity<AgenciaBancaria> update(@PathVariable java.util.UUID id, @Valid @RequestBody AgenciaBancaria agenciaBancaria) {
		try {
			AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.update(id, agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
			return ResponseEntity.ok(agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes Agência bancária")
	public void delete(@PathVariable java.util.UUID id) {
		agenciaBancariaService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Agência bancária")
	public PageResult<AgenciaBancaria> list(AgenciaBancariaListFilter agenciaBancariaListFilter, Pageable pageable) {
		Page<AgenciaBancariaEntity> page = agenciaBancariaService.list(agenciaBancariaListFilter, pageable);
		List<AgenciaBancaria> content = page.getContent().stream().map(pe -> agenciaBancariaDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<AgenciaBancaria> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Agência bancária with a query param")
	public Collection<AgenciaBancariaAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<AgenciaBancariaAutoComplete> result = agenciaBancariaService.autoComplete(query);
		return result;
	}
	
	
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/bancoBancoAutoComplete")
	@ApiOperation(value = "Retrieves a list of BancoAutoComplete by query bancoBancoAutoComplete over AgenciaBancaria with a query param")
	public Collection<BancoAutoComplete> bancoBancoAutoComplete(@RequestParam("query") String query) {
		Collection<BancoAutoComplete> result = agenciaBancariaService.bancoBancoAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
