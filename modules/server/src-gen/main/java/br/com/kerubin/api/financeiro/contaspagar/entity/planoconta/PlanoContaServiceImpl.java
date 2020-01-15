/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:17.669
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.contaspagar.entity.planoconta;

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
public class PlanoContaServiceImpl implements PlanoContaService {
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@Autowired
	private PlanoContaListFilterPredicate planoContaListFilterPredicate;
	
	
	@Transactional
	@Override
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity) {
		return planoContaRepository.save(planoContaEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public PlanoContaEntity read(java.util.UUID id) {
		return getPlanoContaEntity(id);
	}
	
	@Transactional
	@Override
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity) {
		// PlanoContaEntity entity = getPlanoContaEntity(id);
		// BeanUtils.copyProperties(planoContaEntity, entity, "id");
		// entity = planoContaRepository.save(entity);
		
		PlanoContaEntity entity = planoContaRepository.save(planoContaEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		planoContaRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		planoContaRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable) {
		Predicate predicate = planoContaListFilterPredicate.mountAndGetPredicate(planoContaListFilter);
		
		Page<PlanoContaEntity> resultPage = planoContaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected PlanoContaEntity getPlanoContaEntity(java.util.UUID id) {
		Optional<PlanoContaEntity> planoContaEntity = planoContaRepository.findById(id);
		if (!planoContaEntity.isPresent()) {
			throw new IllegalArgumentException("PlanoConta not found:" + id.toString());
		}
		return planoContaEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> autoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContaPaiAutoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query) {
		Collection<PlanoContaCodigoAutoComplete> result = planoContaRepository.planoContaCodigoAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query) {
		Collection<PlanoContaDescricaoAutoComplete> result = planoContaRepository.planoContaDescricaoAutoComplete(query);
		return result;
	}
	
	
}
