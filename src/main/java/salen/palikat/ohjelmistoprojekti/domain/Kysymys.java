package salen.palikat.ohjelmistoprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kysymys {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

//olikos tää nyt näin
@ManyToOne	
@JoinColumn(name = "kysely")
private Kysely kysely;
private Kysymystyyppi tyyppi;

//kommentoin tätä t. jussi
public Kysymys()
{
	
}
}
