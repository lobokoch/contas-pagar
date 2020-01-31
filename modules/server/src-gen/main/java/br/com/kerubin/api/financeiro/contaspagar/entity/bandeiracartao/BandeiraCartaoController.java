/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;

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
@RequestMapping("financeiro/contas_pagar/entities/bandeiraCartao")
@Api(value = "BandeiraCartao", tags = {"BandeiraCartao"}, description = "Operations for Bandeira de cartão")
public class BandeiraCartaoController {
	
	@Autowired
	private BandeiraCartaoService bandeiraCartaoService;
	
	@Autowired
	BandeiraCartaoDTOConverter bandeiraCartaoDTOConverter;
	
	@Transactional
	@PostMapping
	@ApiOperation(value = "Creates a new Bandeira de cartão")
	public ResponseEntity<BandeiraCartao> create(@Valid @RequestBody BandeiraCartao bandeiraCartao) {
		BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.create(bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartao));
		return ResponseEntity.status(HttpStatus.CREATED).body(bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieves Bandeira de cartão")
	public ResponseEntity<BandeiraCartao> read(@PathVariable java.util.UUID id) {
		try {
			BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.read(id);
			return ResponseEntity.ok(bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiOperation(value = "Updates Bandeira de cartão")
	public ResponseEntity<BandeiraCartao> update(@PathVariable java.util.UUID id, @Valid @RequestBody BandeiraCartao bandeiraCartao) {
		try {
			BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.update(id, bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartao));
			return ResponseEntity.ok(bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deletes Bandeira de cartão")
	public void delete(@PathVariable java.util.UUID id) {
		bandeiraCartaoService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	@ApiOperation(value = "Retrieves a list of Bandeira de cartão")
	public PageResult<BandeiraCartao> list(BandeiraCartaoListFilter bandeiraCartaoListFilter, Pageable pageable) {
		Page<BandeiraCartaoEntity> page = bandeiraCartaoService.list(bandeiraCartaoListFilter, pageable);
		List<BandeiraCartao> content = page.getContent().stream().map(pe -> bandeiraCartaoDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<BandeiraCartao> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	@ApiOperation(value = "Retrieves a list of Bandeira de cartão with a query param")
	public Collection<BandeiraCartaoAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<BandeiraCartaoAutoComplete> result = bandeiraCartaoService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/bandeiraCartaoNomeBandeiraAutoComplete")
	@ApiOperation(value = "Retrieves a list of Bandeira de cartão with a query param")
	public Collection<BandeiraCartaoNomeBandeiraAutoComplete> bandeiraCartaoNomeBandeiraAutoComplete(@RequestParam("query") String query) {
		Collection<BandeiraCartaoNomeBandeiraAutoComplete> result = bandeiraCartaoService.bandeiraCartaoNomeBandeiraAutoComplete(query);
		return result;
	}
	
	
}
