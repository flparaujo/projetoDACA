package dirlididi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import dirlididi.domain.Problema;

public interface ProblemaRepository extends CrudRepository<Problema, Long> {
	List<Problema> findAll();
	
	@Query("select p from Problema p where p.isPrivado = false order by p.dataDeCriacao")
	List<Problema> findAllPublic();
	
	Problema findById(Long id);
}
