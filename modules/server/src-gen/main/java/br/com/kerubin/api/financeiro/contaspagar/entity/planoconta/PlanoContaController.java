/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T06:59:30.300
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/contas_pagar/entities/planoConta")
@Api(value = "PlanoConta", tags = {"PlanoConta"}, description = "Operations for Plano de contas")
public class PlanoContaController {
	
	@Autowired
	private PlanoContaService planoContaService;
	
	@Autowired
	PlanoContaDTOConverter planoContaDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Plano de contas")
	public ResponseEntity<PlanoConta> create(@Valid @RequestBody PlanoConta planoConta) {
		PlanoContaEntity planoContaEntity = planoContaService.create(planoContaDTOConverter.convertDtoToEntity(planoConta));
		return ResponseEntity.status(HttpStatus.CREATED).body(planoContaDTOConverter.convertEntityToDto(planoContaEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Plano de contas")
	public ResponseEntity<PlanoConta> read(@PathVariable java.util.UUID id) {
		try {
			PlanoContaEntity planoContaEntity = planoContaService.read(id);
			return ResponseEntity.ok(planoContaDTOConverter.convertEntityToDto(planoContaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates Plano de contas")
	public ResponseEntity<PlanoConta> update(@PathVariable java.util.UUID id, @Valid @RequestBody PlanoConta planoConta) {
		try {
			PlanoContaEntity planoContaEntity = planoContaService.update(id, planoContaDTOConverter.convertDtoToEntity(planoConta));
			return ResponseEntity.ok(planoContaDTOConverter.convertEntityToDto(planoContaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes Plano de contas")
	public void delete(@PathVariable java.util.UUID id) {
		planoContaService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Plano de contas")
	public PageResult<PlanoConta> list(PlanoContaListFilter planoContaListFilter, Pageable pageable) {
		Page<PlanoContaEntity> page = planoContaService.list(planoContaListFilter, pageable);
		List<PlanoConta> content = page.getContent().stream().map(pe -> planoContaDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<PlanoConta> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Plano de contas with a query param")
	public Collection<PlanoContaAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaAutoComplete> result = planoContaService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/planoContaCodigoAutoComplete")
	@ApiOperation(value = "Retrieves a list of Plano de contas with a query param")
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaCodigoAutoComplete> result = planoContaService.planoContaCodigoAutoComplete(query);
		return result;
	}
	
	@GetMapping("/planoContaDescricaoAutoComplete")
	@ApiOperation(value = "Retrieves a list of Plano de contas with a query param")
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaDescricaoAutoComplete> result = planoContaService.planoContaDescricaoAutoComplete(query);
		return result;
	}
	
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/planoContaPlanoContaPaiAutoComplete")
	@ApiOperation(value = "Retrieves a list of PlanoContaAutoComplete by query planoContaPlanoContaPaiAutoComplete over PlanoConta with a query param")
	public Collection<PlanoContaAutoComplete> planoContaPlanoContaPaiAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaAutoComplete> result = planoContaService.planoContaPlanoContaPaiAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
