package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//id nimet muutettu
	private Long kysely_id;
	private String name;
	//vaihdettu mergestä alliin
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	@JsonManagedReference
	private List<Kysymys> kysymykset;

	public Kysely(List<Kysymys> kysymykset) {
		super();
		this.kysymykset = kysymykset;
	}
	
	public Kysely() {
		
	}

	public Kysely(String name) {
		super();
		this.name = name;
	}

	

	@Override
	public String toString() {
		return "Kysely [kysely_id=" + kysely_id + ", name=" + name ;
	}

	public Long getKysely_id() {
		return kysely_id;
	}

	public void setKysely_id(Long kysely_id) {
		this.kysely_id = kysely_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}
	
	
}
