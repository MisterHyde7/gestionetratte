package it.prova.gestionetratte.model;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tratta")
public class Tratta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "codice")
	private String codice;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "data")
	private Date data;

	@Column(name = "oraDecollo")
	private LocalTime oraDecollo;

	@Column(name = "oraAtterraggio")
	private LocalTime oraAtterraggio;

	@Column(name = "stato")
	private Stato stato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "airbus_id")
	private Airbus airbus;

	public Tratta() {
		super();
	}

	public Tratta(String codice, String descrizione, Date data, LocalTime oraDecollo, LocalTime oraAtterraggio,
			Stato stato) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
	}

	public Tratta(Long id, String codice, String descrizione, Date data, LocalTime oraDecollo, LocalTime oraAtterraggio,
			Stato stato) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
	}

	public Tratta(Long id, String codice, String descrizione, Date data, LocalTime oraDecollo, LocalTime oraAtterraggio,
			Stato stato, Airbus airbus) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.data = data;
		this.oraDecollo = oraDecollo;
		this.oraAtterraggio = oraAtterraggio;
		this.stato = stato;
		this.airbus = airbus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public LocalTime getOraDecollo() {
		return oraDecollo;
	}

	public void setOraDecollo(LocalTime oraDecollo) {
		this.oraDecollo = oraDecollo;
	}

	public LocalTime getOraAtterraggio() {
		return oraAtterraggio;
	}

	public void setOraAtterraggio(LocalTime oraAtterraggio) {
		this.oraAtterraggio = oraAtterraggio;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Airbus getAirbus() {
		return airbus;
	}

	public void setAirbus(Airbus airbus) {
		this.airbus = airbus;
	}

}
