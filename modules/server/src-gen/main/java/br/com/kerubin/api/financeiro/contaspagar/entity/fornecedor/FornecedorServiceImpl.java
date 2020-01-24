/**********************************************************************************************
Code generated with MKL Plug-in version: 55.0.3
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.fornecedor;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class FornecedorServiceImpl implements FornecedorService {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private FornecedorListFilterPredicate fornecedorListFilterPredicate;
	
	
	@Transactional
	@Override
	public FornecedorEntity create(FornecedorEntity fornecedorEntity) {
		return fornecedorRepository.save(fornecedorEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public FornecedorEntity read(java.util.UUID id) {
		return getFornecedorEntity(id);
	}
	
	@Transactional
	@Override
	public FornecedorEntity update(java.util.UUID id, FornecedorEntity fornecedorEntity) {
		// FornecedorEntity entity = getFornecedorEntity(id);
		// BeanUtils.copyProperties(fornecedorEntity, entity, "id");
		// entity = fornecedorRepository.save(entity);
		
		FornecedorEntity entity = fornecedorRepository.save(fornecedorEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		fornecedorRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		fornecedorRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<FornecedorEntity> list(FornecedorListFilter fornecedorListFilter, Pageable pageable) {
		Predicate predicate = fornecedorListFilterPredicate.mountAndGetPredicate(fornecedorListFilter);
		
		Page<FornecedorEntity> resultPage = fornecedorRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected FornecedorEntity getFornecedorEntity(java.util.UUID id) {
		Optional<FornecedorEntity> fornecedorEntity = fornecedorRepository.findById(id);
		if (!fornecedorEntity.isPresent()) {
			throw new IllegalArgumentException("Fornecedor not found:" + id.toString());
		}
		return fornecedorEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<FornecedorAutoComplete> autoComplete(String query) {
		Collection<FornecedorAutoComplete> result = fornecedorRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<FornecedorNomeAutoComplete> fornecedorNomeAutoComplete(String query) {
		Collection<FornecedorNomeAutoComplete> result = fornecedorRepository.fornecedorNomeAutoComplete(query);
		return result;
	}
	
	
}
