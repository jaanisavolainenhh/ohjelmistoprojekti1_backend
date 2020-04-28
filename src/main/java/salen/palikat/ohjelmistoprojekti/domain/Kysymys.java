package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//id nimet muutettu
	private Long kysymys_id;

	// olikos tää nyt näin
	@ManyToOne
	@JsonBackReference
	//vaihdettu viittaamaan yksilöydympään kysely id, jotta all toimii
	@JoinColumn(name = "kysely_id")
	private Kysely kysely;
	private Kysymystyyppi tyyppi;
	private String kysymys;
	//Vaihdettu Cascadetype Mergestä takaisin alliin
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "kysymys")
	@JsonManagedReference
	private List<Vaihtoehto> vaihtoehdot;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "kysymys")
	@JsonManagedReference
	private List<Vastaus> vastaus;

	
	public List<Vastaus> getVastaus() {
		return vastaus;
	}


	public void setVastaus(List<Vastaus> vastaus) {
		this.vastaus = vastaus;
	}


	@Override
	public String toString() {
		return "Kysymys [id=" + kysymys_id;
	}


	public Kysymys(Kysely kysely, Kysymystyyppi tyyppi, String kysymys, List<Vaihtoehto> vaihtoehdot) {
		super();
		this.kysely = kysely;
		this.tyyppi = tyyppi;
		this.kysymys = kysymys;
		this.vaihtoehdot = vaihtoehdot;
	}


	


	public Long getKysymys_id() {
		return kysymys_id;
	}


	public void setKysymys_id(Long kysymys_id) {
		this.kysymys_id = kysymys_id;
	}


	public Kysely getKysely() {
		return kysely;
	}


	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}


	public Kysymystyyppi getTyyppi() {
		return tyyppi;
	}


	public void setTyyppi(Kysymystyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}


	public String getKysymys() {
		return kysymys;
	}


	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}
	
	public Kysymys(String kysymys, Kysymystyyppi tyyppi, List<Vaihtoehto> vaihtoehdot) {
		super();
		this.kysymys = kysymys;
		this.tyyppi = tyyppi;
		this.vaihtoehdot = vaihtoehdot;
	}
	
	public Kysymys(String kysymys, Kysymystyyppi tyyppi) {
		super();
		this.kysymys = kysymys;
		this.tyyppi = tyyppi;
	}
	
	public Kysymys(String kysymys, Kysymystyyppi tyyppi, Kysely kysely) {
		super();
		this.kysely = kysely;
		this.tyyppi = tyyppi;
		this.kysymys = kysymys;
	}


	public Kysymys(String kysymys, List<Vaihtoehto> vaihtoehdot) {
		super();
		this.kysymys = kysymys;
		this.vaihtoehdot = vaihtoehdot;
	}


	public List<Vaihtoehto> getVaihtoehdot() {
		return vaihtoehdot;
	}


	public void setVaihtoehdot(List<Vaihtoehto> vaihtoehdot) {
		this.vaihtoehdot = vaihtoehdot;
	}


	public Kysymys() {

	}
}
