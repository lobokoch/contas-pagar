/**********************************************************************************************
Code generated with MKL Plug-in version: 3.9.0
Code generated at time stamp: 2019-06-08T10:32:58.121
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;		

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
public class FornecedorServiceImpl implements FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorListFilterPredicate fornecedorListFilterPredicate;
	
	
	
	@Transactional
	public FornecedorEntity create(FornecedorEntity fornecedorEntity) {
		return fornecedorRepository.save(fornecedorEntity);
	}
	
	@Transactional(readOnly = true)
	public FornecedorEntity read(java.util.UUID id) {
		return getFornecedorEntity(id);
	}
	
	@Transactional
	public FornecedorEntity update(java.util.UUID id, FornecedorEntity fornecedorEntity) {
		FornecedorEntity entity = getFornecedorEntity(id);
		BeanUtils.copyProperties(fornecedorEntity, entity, "id");
		entity = fornecedorRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		fornecedorRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<FornecedorEntity> list(FornecedorListFilter fornecedorListFilter, Pageable pageable) {
		Predicate predicate = fornecedorListFilterPredicate.mountAndGetPredicate(fornecedorListFilter);
		
		Page<FornecedorEntity> resultPage = fornecedorRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private FornecedorEntity getFornecedorEntity(java.util.UUID id) {
		Optional<FornecedorEntity> fornecedorEntity = fornecedorRepository.findById(id);
		if (!fornecedorEntity.isPresent()) {
			throw new IllegalArgumentException("Fornecedor not found:" + id.toString());
		}
		return fornecedorEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<FornecedorAutoComplete> autoComplete(String query) {
		Collection<FornecedorAutoComplete> result = fornecedorRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	public Collection<FornecedorNomeAutoComplete> fornecedorNomeAutoComplete(String query) {
		Collection<FornecedorNomeAutoComplete> result = fornecedorRepository.fornecedorNomeAutoComplete(query);
		return result;
	}
	
	
}
