package dirlididi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dirlididi.domain.Normal;

public interface NormalRepository extends CrudRepository<Normal, Long> {
	List<Normal> findAll();

	Normal findNormalByEmail(String email);
}
