package it.prova.gestionetratte.repository.airbus;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>, PagingAndSortingRepository<Airbus, Long> {

	Airbus findByCodiceAndDescrizione(String codice, String descrizione);

	@Query("select distinct a from Airbus a left join fetch a.tratte ")
	List<Airbus> findAllEager();

	@Query("from Airbus a left join fetch a.tratte where a.id=?1")
	Airbus findByIdEager(Long idAirbus);

	List<Airbus> findAll(Specification<Airbus> specificationCriteria, Pageable paging);
}
