package salen.palikat.ohjelmistoprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KyselyRepository extends CrudRepository<Kysely, Long> {
	List<Kysely> findByName(String name);
}