/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

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
@RequestMapping("entities/contaBancaria")
public class ContaBancariaController {
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@Autowired
	ContaBancariaDTOConverter contaBancariaDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<ContaBancaria> create(@Valid @RequestBody ContaBancaria contaBancaria) {
		ContaBancariaEntity contaBancariaEntity = contaBancariaService.create(contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
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
	public void delete(@PathVariable java.util.UUID id) {
		contaBancariaService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<ContaBancaria> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable) {
		Page<ContaBancariaEntity> page = contaBancariaService.list(contaBancariaListFilter, pageable);
		List<ContaBancaria> content = page.getContent().stream().map(pe -> contaBancariaDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<ContaBancaria> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<ContaBancariaAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/contaBancariaNumeroContaAutoComplete")
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaNumeroContaAutoComplete> result = contaBancariaService.contaBancariaNumeroContaAutoComplete(query);
		return result;
	}
	
	
}
