/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;		

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
public class ContaBancariaServiceImpl implements ContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private ContaBancariaListFilterPredicate contaBancariaListFilterPredicate;
	
	
	
	@Transactional
	public ContaBancariaEntity create(ContaBancariaEntity contaBancariaEntity) {
		return contaBancariaRepository.save(contaBancariaEntity);
	}
	
	@Transactional(readOnly = true)
	public ContaBancariaEntity read(java.util.UUID id) {
		return getContaBancariaEntity(id);
	}
	
	@Transactional
	public ContaBancariaEntity update(java.util.UUID id, ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaEntity entity = getContaBancariaEntity(id);
		BeanUtils.copyProperties(contaBancariaEntity, entity, "id");
		entity = contaBancariaRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		contaBancariaRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<ContaBancariaEntity> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable) {
		Predicate predicate = contaBancariaListFilterPredicate.mountAndGetPredicate(contaBancariaListFilter);
		
		Page<ContaBancariaEntity> resultPage = contaBancariaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private ContaBancariaEntity getContaBancariaEntity(java.util.UUID id) {
		Optional<ContaBancariaEntity> contaBancariaEntity = contaBancariaRepository.findById(id);
		if (!contaBancariaEntity.isPresent()) {
			throw new IllegalArgumentException("ContaBancaria not found:" + id.toString());
		}
		return contaBancariaEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<ContaBancariaAutoComplete> autoComplete(String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(String query) {
		Collection<ContaBancariaNumeroContaAutoComplete> result = contaBancariaRepository.contaBancariaNumeroContaAutoComplete(query);
		return result;
	}
	
	
}
