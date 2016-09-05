package dirlididi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dirlididi.domain.Administrador;

public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
	List<Administrador> findAll();
}
