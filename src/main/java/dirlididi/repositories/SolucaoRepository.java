package dirlididi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dirlididi.domain.Solucao;

public interface SolucaoRepository extends CrudRepository<Solucao, Long> {
	List<Solucao> findAll();
}
