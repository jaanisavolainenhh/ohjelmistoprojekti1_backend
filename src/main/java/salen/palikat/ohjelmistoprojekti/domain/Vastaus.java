package salen.palikat.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vastaus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String vastaus;
	
	@ManyToOne
	@JoinColumn(name = "kysymys")
	private Kysymys kysymys;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
		return "Vastaus [id=" + id + ", vastaus=" + vastaus + ", kysymys=" + kysymys + "]";
	}

	public Vastaus()
	{
		
	}
}
