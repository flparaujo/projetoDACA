package dirlididi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dirlididi.domain.Problema;

public interface ProblemaRepository extends CrudRepository<Problema, Long> {
	List<Problema> findAll();
}
