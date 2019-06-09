/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;

import java.util.Collection;
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.kerubin.api.financeiro.contaspagar.common.PageResult;


@RestController
@RequestMapping("entities/agenciaBancaria")
public class AgenciaBancariaController {
	
	@Autowired
	private AgenciaBancariaService agenciaBancariaService;
	
	@Autowired
	AgenciaBancariaDTOConverter agenciaBancariaDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<AgenciaBancaria> create(@Valid @RequestBody AgenciaBancaria agenciaBancaria) {
		AgenciaBancariaEntity agenciaBancariaEntity = agenciaBancariaService.create(agenciaBancariaDTOConverter.convertDtoToEntity(agenciaBancaria));
		return ResponseEntity.status(HttpStatus.CREATED).body(agenciaBancariaDTOConverter.convertEntityToDto(agenciaBancariaEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
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
	public void delete(@PathVariable java.util.UUID id) {
		agenciaBancariaService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<AgenciaBancaria> list(AgenciaBancariaListFilter agenciaBancariaListFilter, Pageable pageable) {
		Page<AgenciaBancariaEntity> page = agenciaBancariaService.list(agenciaBancariaListFilter, pageable);
		List<AgenciaBancaria> content = page.getContent().stream().map(pe -> agenciaBancariaDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<AgenciaBancaria> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<AgenciaBancariaAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<AgenciaBancariaAutoComplete> result = agenciaBancariaService.autoComplete(query);
		return result;
	}
	
	
	
}
