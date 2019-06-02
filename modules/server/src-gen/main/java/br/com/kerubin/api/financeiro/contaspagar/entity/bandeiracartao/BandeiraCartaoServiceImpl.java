/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.bandeiracartao;		

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
public class BandeiraCartaoServiceImpl implements BandeiraCartaoService {
	
	@Autowired
	private BandeiraCartaoRepository bandeiraCartaoRepository;
	
	@Autowired
	private BandeiraCartaoListFilterPredicate bandeiraCartaoListFilterPredicate;
	
	
	
	@Transactional
	public BandeiraCartaoEntity create(BandeiraCartaoEntity bandeiraCartaoEntity) {
		return bandeiraCartaoRepository.save(bandeiraCartaoEntity);
	}
	
	@Transactional(readOnly = true)
	public BandeiraCartaoEntity read(java.util.UUID id) {
		return getBandeiraCartaoEntity(id);
	}
	
	@Transactional
	public BandeiraCartaoEntity update(java.util.UUID id, BandeiraCartaoEntity bandeiraCartaoEntity) {
		BandeiraCartaoEntity entity = getBandeiraCartaoEntity(id);
		BeanUtils.copyProperties(bandeiraCartaoEntity, entity, "id");
		entity = bandeiraCartaoRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		bandeiraCartaoRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<BandeiraCartaoEntity> list(BandeiraCartaoListFilter bandeiraCartaoListFilter, Pageable pageable) {
		Predicate predicate = bandeiraCartaoListFilterPredicate.mountAndGetPredicate(bandeiraCartaoListFilter);
		
		Page<BandeiraCartaoEntity> resultPage = bandeiraCartaoRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private BandeiraCartaoEntity getBandeiraCartaoEntity(java.util.UUID id) {
		Optional<BandeiraCartaoEntity> bandeiraCartaoEntity = bandeiraCartaoRepository.findById(id);
		if (!bandeiraCartaoEntity.isPresent()) {
			throw new IllegalArgumentException("BandeiraCartao not found:" + id.toString());
		}
		return bandeiraCartaoEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<BandeiraCartaoAutoComplete> autoComplete(String query) {
		Collection<BandeiraCartaoAutoComplete> result = bandeiraCartaoRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	public Collection<BandeiraCartaoNomeBandeiraAutoComplete> bandeiraCartaoNomeBandeiraAutoComplete(String query) {
		Collection<BandeiraCartaoNomeBandeiraAutoComplete> result = bandeiraCartaoRepository.bandeiraCartaoNomeBandeiraAutoComplete(query);
		return result;
	}
	
	
}
