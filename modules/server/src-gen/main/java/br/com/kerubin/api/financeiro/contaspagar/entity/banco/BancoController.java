/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.banco;

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
@RequestMapping("financeiro/contas_pagar/entities/banco")
@Api(value = "Banco", tags = {"Banco"}, description = "Operations for Banco")
public class BancoController {
	
	@Autowired
	private BancoService bancoService;
	
	@Autowired
	BancoDTOConverter bancoDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Banco")
	public ResponseEntity<Banco> create(@Valid @RequestBody Banco banco) {
		BancoEntity bancoEntity = bancoService.create(bancoDTOConverter.convertDtoToEntity(banco));
		return ResponseEntity.status(HttpStatus.CREATED).body(bancoDTOConverter.convertEntityToDto(bancoEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Banco")
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
	@ApiOperation(value = "Updates Banco")
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
	@ApiOperation(value = "Deletes Banco")
	public void delete(@PathVariable java.util.UUID id) {
		bancoService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Banco")
	public PageResult<Banco> list(BancoListFilter bancoListFilter, Pageable pageable) {
		Page<BancoEntity> page = bancoService.list(bancoListFilter, pageable);
		List<Banco> content = page.getContent().stream().map(pe -> bancoDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<Banco> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Banco with a query param")
	public Collection<BancoAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<BancoAutoComplete> result = bancoService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/bancoNomeAutoComplete")
	@ApiOperation(value = "Retrieves a list of Banco with a query param")
	public Collection<BancoNomeAutoComplete> bancoNomeAutoComplete(@RequestParam("query") String query) {
		Collection<BancoNomeAutoComplete> result = bancoService.bancoNomeAutoComplete(query);
		return result;
	}
	
	
				
	// findBy methods
	
	@Transactional(readOnly = true)
	@GetMapping("/findBancoByNumero")
	@ApiOperation(value = "Retrieves Banco by numero")
	public ResponseEntity<Banco> findBancoByNumero(String numero) {
		BancoEntity content = bancoService.findBancoByNumero(numero);
		return ResponseEntity.ok(bancoDTOConverter.convertEntityToDto(content));
	}
	
}
