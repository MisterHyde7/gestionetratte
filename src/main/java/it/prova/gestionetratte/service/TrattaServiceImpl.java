package it.prova.gestionetratte.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;

@Service
public class TrattaServiceImpl implements TrattaService {

	@Autowired
	private TrattaRepository repository;

	public List<Tratta> listAllElements(boolean eager) {
		if (eager)
			return (List<Tratta>) repository.findAllTrattaEager();

		return (List<Tratta>) repository.findAll();
	}

	public Tratta caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Tratta caricaSingoloElementoEager(Long id) {
		return repository.findSingleTrattaEager(id);
	}

	@Transactional
	public Tratta aggiorna(Tratta filmInstance) {
		return repository.save(filmInstance);
	}

	@Transactional
	public Tratta inserisciNuovo(Tratta filmInstance) {
		return repository.save(filmInstance);
	}

	@Transactional
	public void rimuovi(Tratta filmInstance) {
		repository.delete(filmInstance);
	}

	public List<Tratta> findByExample(Tratta example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Tratta> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(
						cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			if (example.getData() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("data"), example.getData()));

			if (example.getOraDecollo() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("oraDecollo"), example.getOraDecollo()));

			if (example.getOraAtterraggio() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("oraAtterraggio"), example.getOraAtterraggio()));

			if (example.getStato() != null)
				predicates.add(cb.equal(root.get("stato"), example.getStato()));

			if (example.getAirbus() != null && example.getAirbus().getId() != null)
				predicates.add(cb.equal(cb.upper(root.get("airbus_id")), example.getAirbus().getId()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Override
	public void concludiTratte() {
		Set<Tratta> tratteDaConcludere = repository.findByStatoEquals(Stato.ATTIVA);

		for (Tratta trattaItem : tratteDaConcludere) {
			if (trattaItem.getOraAtterraggio().isBefore(LocalTime.now()))
				trattaItem.setStato(Stato.CONCLUSA);
		}
		repository.saveAll(tratteDaConcludere);
	}

}
