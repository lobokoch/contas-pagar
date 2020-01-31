/**********************************************************************************************
Code generated with MKL Plug-in version: 60.0.6
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.contabancaria;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;


import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaAutoComplete;

import br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria.AgenciaBancariaRepository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class ContaBancariaServiceImpl implements ContaBancariaService {
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private ContaBancariaListFilterPredicate contaBancariaListFilterPredicate;
	
	
	@Autowired
	private AgenciaBancariaRepository agenciaBancariaRepository;
	
	
	@Transactional
	@Override
	public ContaBancariaEntity create(ContaBancariaEntity contaBancariaEntity) {
		return contaBancariaRepository.save(contaBancariaEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaBancariaEntity read(java.util.UUID id) {
		return getContaBancariaEntity(id);
	}
	
	@Transactional
	@Override
	public ContaBancariaEntity update(java.util.UUID id, ContaBancariaEntity contaBancariaEntity) {
		// ContaBancariaEntity entity = getContaBancariaEntity(id);
		// BeanUtils.copyProperties(contaBancariaEntity, entity, "id");
		// entity = contaBancariaRepository.save(entity);
		
		ContaBancariaEntity entity = contaBancariaRepository.save(contaBancariaEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		contaBancariaRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		contaBancariaRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<ContaBancariaEntity> list(ContaBancariaListFilter contaBancariaListFilter, Pageable pageable) {
		Predicate predicate = contaBancariaListFilterPredicate.mountAndGetPredicate(contaBancariaListFilter);
		
		Page<ContaBancariaEntity> resultPage = contaBancariaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected ContaBancariaEntity getContaBancariaEntity(java.util.UUID id) {
		Optional<ContaBancariaEntity> contaBancariaEntity = contaBancariaRepository.findById(id);
		if (!contaBancariaEntity.isPresent()) {
			throw new IllegalArgumentException("ContaBancaria not found:" + id.toString());
		}
		return contaBancariaEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaAutoComplete> autoComplete(String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<AgenciaBancariaAutoComplete> agenciaBancariaAgenciaAutoComplete(String query) {
		Collection<AgenciaBancariaAutoComplete> result = agenciaBancariaRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaNumeroContaAutoComplete> contaBancariaNumeroContaAutoComplete(String query) {
		Collection<ContaBancariaNumeroContaAutoComplete> result = contaBancariaRepository.contaBancariaNumeroContaAutoComplete(query);
		return result;
	}
	
	
}
