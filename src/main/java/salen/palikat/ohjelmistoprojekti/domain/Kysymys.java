package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// olikos tää nyt näin
	@ManyToOne
	@JoinColumn(name = "kysely")
	private Kysely kysely;
	private Kysymystyyppi tyyppi;
	private String kysymys;
	private List<String> vaihtoehdot;

	
	public Kysymys(Kysely kysely, Kysymystyyppi tyyppi, String kysymys, List<String> vaihtoehdot) {
		super();
		this.kysely = kysely;
		this.tyyppi = tyyppi;
		this.kysymys = kysymys;
		this.vaihtoehdot = vaihtoehdot;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public List<String> getVaihtoehdot() {
		return vaihtoehdot;
	}


	public void setVaihtoehdot(List<String> vaihtoehdot) {
		this.vaihtoehdot = vaihtoehdot;
	}


	public Kysymys() {

	}
}
