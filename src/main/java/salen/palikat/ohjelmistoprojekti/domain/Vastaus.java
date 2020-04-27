package salen.palikat.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Vastaus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long vastaus_id;
	
	private String vastaus;
	
	//@JsonManagedReference
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "kysymys_id")
	private Kysymys kysymys;


	//käytetään yksilöimään kyselun vastaukset jotta saadaan grooupattua yhteen
	private int sessioid;

	public int getSessioid() {
		return sessioid;
	}
	public void setSessioid(int sessioid) {
		this.sessioid = sessioid;
	}
	

	public Long getVastaus_id() {
		return vastaus_id;
	}
	public void setVastaus_id(Long vastaus_id) {
		this.vastaus_id = vastaus_id;
	}
	// Jotain turhaa, tekeekö näillä mitään?
//	public int getSessionkey() {
//		return sessioid;
//	}
//	public void setSessionkey(int sessionkey) {
//		this.sessioid = sessionkey;
//	}
	public String getVastaus() {
		return vastaus;
	}
	public void setVastaus(String vastaus) {
		this.vastaus = vastaus;
	}
	public Kysymys getKysymys() {
		return kysymys;
	}
	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}
	
	public Vastaus(String vastaus, Kysymys kysymys) {
		super();
		this.vastaus = vastaus;
		this.kysymys = kysymys;
	}

	@Override
	public String toString() {
		return "Vastaus [id=" + vastaus_id + ", vastaus=" + vastaus + ", kysymys=" + kysymys + "]";
	}
	public Vastaus() {
		
	}

	
	
}
