package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface VastausRepository extends CrudRepository<Vastaus, Long> {
	
	List<Vastaus> findBySessioid(int sessioid);
}

