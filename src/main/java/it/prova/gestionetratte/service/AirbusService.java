package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {

	List<Airbus> listAllElements();

	List<Airbus> listAllElementsEager();

	Airbus caricaSingoloElemento(Long id);

	Airbus caricaSingoloElementoConTratte(Long id);

	Airbus aggiorna(Airbus airbusInstance);

	Airbus inserisciNuovo(Airbus airbusInstance);

	void rimuovi(Airbus airbusInstance);

	List<Airbus> findByExample(Airbus example, Integer pageNo, Integer pageSize, String sortBy);

	List<Airbus> listaAirbusEvidenziandoSovrapposizioni();

}
