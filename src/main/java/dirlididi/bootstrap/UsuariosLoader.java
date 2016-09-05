package dirlididi.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dirlididi.domain.Administrador;
import dirlididi.repositories.AdministradorRepository;

@Component
public class UsuariosLoader implements ApplicationListener<ContextRefreshedEvent> {
	private AdministradorRepository administradorRepository;

	@Autowired
	public void setAdministradorRepository(AdministradorRepository administradorRepository) {
		this.administradorRepository = administradorRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (administradorRepository.findAll().isEmpty()) {
			for (int i = 0; i < 3; i++) {
				Administrador admin = new Administrador("xpto" + i + "@kkk.com", "cab@l15terios");
				administradorRepository.save(admin);
			}
		}
	}

}
