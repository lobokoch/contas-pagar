/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-02T06:23:49.436
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.agenciabancaria;		

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
public class AgenciaBancariaServiceImpl implements AgenciaBancariaService {
	
	@Autowired
	private AgenciaBancariaRepository agenciaBancariaRepository;
	
	@Autowired
	private AgenciaBancariaListFilterPredicate agenciaBancariaListFilterPredicate;
	
	
	
	@Transactional
	public AgenciaBancariaEntity create(AgenciaBancariaEntity agenciaBancariaEntity) {
		return agenciaBancariaRepository.save(agenciaBancariaEntity);
	}
	
	@Transactional(readOnly = true)
	public AgenciaBancariaEntity read(java.util.UUID id) {
		return getAgenciaBancariaEntity(id);
	}
	
	@Transactional
	public AgenciaBancariaEntity update(java.util.UUID id, AgenciaBancariaEntity agenciaBancariaEntity) {
		AgenciaBancariaEntity entity = getAgenciaBancariaEntity(id);
		BeanUtils.copyProperties(agenciaBancariaEntity, entity, "id");
		entity = agenciaBancariaRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		agenciaBancariaRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<AgenciaBancariaEntity> list(AgenciaBancariaListFilter agenciaBancariaListFilter, Pageable pageable) {
		Predicate predicate = agenciaBancariaListFilterPredicate.mountAndGetPredicate(agenciaBancariaListFilter);
		
		Page<AgenciaBancariaEntity> resultPage = agenciaBancariaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private AgenciaBancariaEntity getAgenciaBancariaEntity(java.util.UUID id) {
		Optional<AgenciaBancariaEntity> agenciaBancariaEntity = agenciaBancariaRepository.findById(id);
		if (!agenciaBancariaEntity.isPresent()) {
			throw new IllegalArgumentException("AgenciaBancaria not found:" + id.toString());
		}
		return agenciaBancariaEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<AgenciaBancariaAutoComplete> autoComplete(String query) {
		Collection<AgenciaBancariaAutoComplete> result = agenciaBancariaRepository.autoComplete(query);
		return result;
	}
	
	
}
