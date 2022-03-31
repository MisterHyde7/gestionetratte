package it.prova.gestionetratte.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "airbus")
public class Airbus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "codice")
	private String codice;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "dataInizioServizio")
	private Date dataInizioServizio;

	@Column(name = "passeggeri")
	private int passeggeri;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "airbus")
	private Set<Tratta> tratte = new HashSet<Tratta>();

	public Airbus() {
		super();
	}

	public Airbus(String codice, String descrizione, Date dataInizioServizio, int passeggeri) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.passeggeri = passeggeri;
	}

	public Airbus(Long id, String codice, String descrizione, Date dataInizioServizio, int passeggeri) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.passeggeri = passeggeri;
	}

	public Airbus(Long id, String codice, String descrizione, Date dataInizioServizio, int passeggeri,
			Set<Tratta> tratte) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.passeggeri = passeggeri;
		this.tratte = tratte;
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

	public Date getDataInizioServizio() {
		return dataInizioServizio;
	}

	public void setDataInizioServizio(Date dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}

	public int getPasseggeri() {
		return passeggeri;
	}

	public void setPasseggeri(int passeggeri) {
		this.passeggeri = passeggeri;
	}

	public Set<Tratta> getTratte() {
		return tratte;
	}

	public void setTratte(Set<Tratta> tratte) {
		this.tratte = tratte;
	}

}
