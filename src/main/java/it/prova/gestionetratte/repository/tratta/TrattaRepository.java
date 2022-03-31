package it.prova.gestionetratte.repository.tratta;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long> {
	@Query("from Tratta t join fetch t.airbus where t.id = ?1")
	Tratta findSingleTrattaEager(Long id);

	@Query("select t from Tratta t join fetch t.airbus")
	List<Tratta> findAllTrattaEager();

	List<Tratta> findAll(Specification<Tratta> specificationCriteria, Pageable paging);

	Set<Tratta> findByStatoEquals(Stato attiva);

}
