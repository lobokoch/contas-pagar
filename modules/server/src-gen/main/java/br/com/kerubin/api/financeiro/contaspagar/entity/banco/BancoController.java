/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

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
@RequestMapping("entities/banco")
public class BancoController {
	
	@Autowired
	private BancoService bancoService;
	
	@Autowired
	BancoDTOConverter bancoDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Banco> create(@Valid @RequestBody Banco banco) {
		BancoEntity bancoEntity = bancoService.create(bancoDTOConverter.convertDtoToEntity(banco));
		return ResponseEntity.status(HttpStatus.CREATED).body(bancoDTOConverter.convertEntityToDto(bancoEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
	public ResponseEntity<Banco> read(@PathVariable java.util.UUID id) {
		try {
			BancoEntity bancoEntity = bancoService.read(id);
			return ResponseEntity.ok(bancoDTOConverter.convertEntityToDto(bancoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Banco> update(@PathVariable java.util.UUID id, @Valid @RequestBody Banco banco) {
		try {
			BancoEntity bancoEntity = bancoService.update(id, bancoDTOConverter.convertDtoToEntity(banco));
			return ResponseEntity.ok(bancoDTOConverter.convertEntityToDto(bancoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		bancoService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<Banco> list(BancoListFilter bancoListFilter, Pageable pageable) {
		Page<BancoEntity> page = bancoService.list(bancoListFilter, pageable);
		List<Banco> content = page.getContent().stream().map(pe -> bancoDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<Banco> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<BancoAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<BancoAutoComplete> result = bancoService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/bancoNomeAutoComplete")
	public Collection<BancoNomeAutoComplete> bancoNomeAutoComplete(@RequestParam("query") String query) {
		Collection<BancoNomeAutoComplete> result = bancoService.bancoNomeAutoComplete(query);
		return result;
	}
	
	
}
