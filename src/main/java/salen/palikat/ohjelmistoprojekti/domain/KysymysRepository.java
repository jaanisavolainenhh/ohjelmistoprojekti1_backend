
package salen.palikat.ohjelmistoprojekti.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin //Lisätty jotta reactiappi  toimisi  https://docs.spring.io/spring-data/rest/docs/current/reference/html/#reference
public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
	//Kysymys findById(Long id);
//	@Override
//    Kysymys findById(Long id);
}