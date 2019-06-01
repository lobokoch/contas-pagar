/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contapagar;

import java.util.Collection;
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.kerubin.api.financeiro.contaspagar.common.PageResult;


@RestController
@RequestMapping("entities/contaPagar")
public class ContaPagarController {
	
	@Autowired
	private ContaPagarService contaPagarService;
	
	@Autowired
	ContaPagarDTOConverter contaPagarDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<ContaPagar> create(@Valid @RequestBody ContaPagar contaPagar) {
		ContaPagarEntity contaPagarEntity = contaPagarService.create(contaPagarDTOConverter.convertDtoToEntity(contaPagar));
		return ResponseEntity.status(HttpStatus.CREATED).body(contaPagarDTOConverter.convertEntityToDto(contaPagarEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
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
	public void delete(@PathVariable java.util.UUID id) {
		contaPagarService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<ContaPagar> list(ContaPagarListFilter contaPagarListFilter, Pageable pageable) {
		Page<ContaPagarEntity> page = contaPagarService.list(contaPagarListFilter, pageable);
		List<ContaPagar> content = page.getContent().stream().map(pe -> contaPagarDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<ContaPagar> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<ContaPagarAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarAutoComplete> result = contaPagarService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/contaPagarDescricaoAutoComplete")
	public Collection<ContaPagarDescricaoAutoComplete> contaPagarDescricaoAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarDescricaoAutoComplete> result = contaPagarService.contaPagarDescricaoAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaPagarAgrupadorAutoComplete")
	public Collection<ContaPagarAgrupadorAutoComplete> contaPagarAgrupadorAutoComplete(@RequestParam("query") String query) {
		Collection<ContaPagarAgrupadorAutoComplete> result = contaPagarService.contaPagarAgrupadorAutoComplete(query);
		return result;
	}
	
	@GetMapping("/contaPagarSumFields")
	public ContaPagarSumFields getContaPagarSumFields(ContaPagarListFilter contaPagarListFilter) {
		ContaPagarSumFields result = contaPagarService.getContaPagarSumFields(contaPagarListFilter);
		return result;
	}
	
	
	@PutMapping("/actionBaixarContaComUmClique/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void actionBaixarContaComUmClique(@PathVariable java.util.UUID id) {
		try {
			contaPagarService.actionBaixarContaComUmClique(id);
		}
		catch(IllegalStateException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
	
	@PutMapping("/actionEstornarPagamentoContaComUmClique/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
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
	public void actionFazerCopiasContaPagar(@Valid @RequestBody ContaPagarMakeCopies contaPagarMakeCopies) {
		try {
			contaPagarService.actionFazerCopiasContaPagar(contaPagarMakeCopies);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
}
