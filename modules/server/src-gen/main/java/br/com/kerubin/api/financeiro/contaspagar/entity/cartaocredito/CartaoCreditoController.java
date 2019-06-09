/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;

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
@RequestMapping("entities/cartaoCredito")
public class CartaoCreditoController {
	
	@Autowired
	private CartaoCreditoService cartaoCreditoService;
	
	@Autowired
	CartaoCreditoDTOConverter cartaoCreditoDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<CartaoCredito> create(@Valid @RequestBody CartaoCredito cartaoCredito) {
		CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.create(cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCredito));
		return ResponseEntity.status(HttpStatus.CREATED).body(cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
	public ResponseEntity<CartaoCredito> read(@PathVariable java.util.UUID id) {
		try {
			CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.read(id);
			return ResponseEntity.ok(cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<CartaoCredito> update(@PathVariable java.util.UUID id, @Valid @RequestBody CartaoCredito cartaoCredito) {
		try {
			CartaoCreditoEntity cartaoCreditoEntity = cartaoCreditoService.update(id, cartaoCreditoDTOConverter.convertDtoToEntity(cartaoCredito));
			return ResponseEntity.ok(cartaoCreditoDTOConverter.convertEntityToDto(cartaoCreditoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		cartaoCreditoService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<CartaoCredito> list(CartaoCreditoListFilter cartaoCreditoListFilter, Pageable pageable) {
		Page<CartaoCreditoEntity> page = cartaoCreditoService.list(cartaoCreditoListFilter, pageable);
		List<CartaoCredito> content = page.getContent().stream().map(pe -> cartaoCreditoDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<CartaoCredito> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<CartaoCreditoAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoService.autoComplete(query);
		return result;
	}
	
	
	
}
