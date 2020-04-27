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
public class Vaihtoehto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//id nimet muutettu
	private Long vaihtoehto_id;
	private String vaihtoehto;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "kysymys_id")
	private Kysymys kysymys;
	
	public Kysymys getKysymys() {
		return kysymys;
	}
	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	
	
	public Long getVaihtoehto_id() {
		return vaihtoehto_id;
	}
	public void setVaihtoehto_id(Long vaihtoehto_id) {
		this.vaihtoehto_id = vaihtoehto_id;
	}
	public String getVaihtoehto() {
		return vaihtoehto;
	}
	public void setVaihtoehto(String vaihtoehto) {
		this.vaihtoehto = vaihtoehto;
	}
	public Vaihtoehto(String vaihtoehto) {
		this.vaihtoehto = vaihtoehto;
	}
	
	public Vaihtoehto(String vaihtoehto, Kysymys kysymys) {
		super();
		this.vaihtoehto = vaihtoehto;
		this.kysymys = kysymys;
	}
	public Vaihtoehto() {
		
	}
	@Override
	public String toString() {
		return "Vaihtoehto [id=" + vaihtoehto_id + ", vaihtoehto=" + vaihtoehto + ", kysymys=" + kysymys + "]";
	}
}
