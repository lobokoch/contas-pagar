/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:20.750
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.cartaocredito;		

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Optional;
import java.util.Collection;

 
@Service
public class CartaoCreditoServiceImpl implements CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private CartaoCreditoListFilterPredicate cartaoCreditoListFilterPredicate;
	
	
	
	@Transactional
	public CartaoCreditoEntity create(CartaoCreditoEntity cartaoCreditoEntity) {
		return cartaoCreditoRepository.save(cartaoCreditoEntity);
	}
	
	@Transactional(readOnly = true)
	public CartaoCreditoEntity read(java.util.UUID id) {
		return getCartaoCreditoEntity(id);
	}
	
	@Transactional
	public CartaoCreditoEntity update(java.util.UUID id, CartaoCreditoEntity cartaoCreditoEntity) {
		CartaoCreditoEntity entity = getCartaoCreditoEntity(id);
		BeanUtils.copyProperties(cartaoCreditoEntity, entity, "id");
		entity = cartaoCreditoRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		cartaoCreditoRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<CartaoCreditoEntity> list(CartaoCreditoListFilter cartaoCreditoListFilter, Pageable pageable) {
		Predicate predicate = cartaoCreditoListFilterPredicate.mountAndGetPredicate(cartaoCreditoListFilter);
		
		Page<CartaoCreditoEntity> resultPage = cartaoCreditoRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private CartaoCreditoEntity getCartaoCreditoEntity(java.util.UUID id) {
		Optional<CartaoCreditoEntity> cartaoCreditoEntity = cartaoCreditoRepository.findById(id);
		if (!cartaoCreditoEntity.isPresent()) {
			throw new IllegalArgumentException("CartaoCredito not found:" + id.toString());
		}
		return cartaoCreditoEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<CartaoCreditoAutoComplete> autoComplete(String query) {
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoRepository.autoComplete(query);
		return result;
	}
	
	
}
