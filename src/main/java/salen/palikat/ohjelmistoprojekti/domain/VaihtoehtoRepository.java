package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VaihtoehtoRepository extends CrudRepository<Vaihtoehto, Long> {
	@Override
    List<Vaihtoehto> findAll();
}
