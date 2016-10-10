package dirlididi.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import dirlididi.domain.Administrador;
import dirlididi.domain.Problema;
import dirlididi.repositories.AdministradorRepository;
import dirlididi.repositories.ProblemaRepository;

@Component
public class ProblemasLoader implements ApplicationListener<ContextRefreshedEvent> {
	private AdministradorRepository administradorRepository;
	private ProblemaRepository problemaRepository;

	@Autowired
	public void setAdministradorRepository(AdministradorRepository administradorRepository) {
		this.administradorRepository = administradorRepository;
	}

	@Autowired
	public void setProblemaRepository(ProblemaRepository problemaRepository) {
		this.problemaRepository = problemaRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (problemaRepository.findAll().isEmpty()) {
			for (int i = 0; i < 30; i++) {
				Problema problema = new Problema("problema " + i, "descrição " + i, null);
				problema.setPrivado(false);

				problemaRepository.save(problema);
			}
		}

	}

}
