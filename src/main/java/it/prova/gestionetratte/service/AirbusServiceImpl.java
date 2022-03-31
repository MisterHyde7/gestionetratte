package it.prova.gestionetratte.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;

@Service
public class AirbusServiceImpl implements AirbusService {

	@Autowired
	private AirbusRepository repository;

	public List<Airbus> listAllElements() {
		return (List<Airbus>) repository.findAll();
	}

	public Airbus caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Airbus caricaSingoloElementoConFilms(Long id) {
		return repository.findByIdEager(id);
	}

	@Transactional
	public Airbus aggiorna(Airbus registaInstance) {
		return repository.save(registaInstance);
	}

	@Transactional
	public Airbus inserisciNuovo(Airbus registaInstance) {
		return repository.save(registaInstance);
	}

	@Transactional
	public void rimuovi(Airbus registaInstance) {
		repository.delete(registaInstance);
	}

	@Transactional
	public List<Airbus> listAllElementsEager() {
		return repository.findAllEager();
	}

	@Transactional
	public Airbus caricaSingoloElementoConTratte(Long id) {
		return repository.findByIdEager(id);
	}

	@Transactional
	public List<Airbus> findByExample(Airbus example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Airbus> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(
						cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			if (example.getDataInizioServizio() != null)
				predicates
						.add(cb.greaterThanOrEqualTo(root.get("dataInizioServizio"), example.getDataInizioServizio()));

			if (example.getPasseggeri() > 0)
				predicates.add(cb.greaterThanOrEqualTo(root.get("passeggeri"), example.getPasseggeri()));

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
	public List<Airbus> listaAirbusEvidenziandoSovrapposizioni() {

		boolean conSovrapposizioni = false;
		List<Airbus> airbusTotali = (List<Airbus>) repository.findAll();
		List<Airbus> airbusConErrore = new ArrayList<Airbus>();

		for (Airbus airbusItem : airbusTotali) {

			List<Tratta> tratteAirbus = new ArrayList<Tratta>();
			for (Tratta trattaItem : airbusItem.getTratte()) {
				tratteAirbus.add(trattaItem);
			}

			int size = tratteAirbus.size();
			while (size > 0) {
				for (int i = 0; i < tratteAirbus.size(); i++) {
					if ((tratteAirbus.get(size - 1).getOraAtterraggio().isAfter(tratteAirbus.get(i).getOraDecollo())))
						conSovrapposizioni = true;
				}
				if (conSovrapposizioni)
					break;
				size--;
			}
			if (conSovrapposizioni)
				airbusConErrore.add(airbusItem);
		}
		return airbusConErrore;
	}

}
