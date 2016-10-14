package dirlididi.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dirlididi.domain.Problema;
import dirlididi.domain.Teste;
import dirlididi.repositories.ProblemaRepository;
import dirlididi.repositories.TesteRepository;

@Component
public class ProblemasLoader implements ApplicationListener<ContextRefreshedEvent> {
	private ProblemaRepository problemaRepository;
	private TesteRepository testeRepository;

	@Autowired
	public void setProblemaRepository(ProblemaRepository problemaRepository) {
		this.problemaRepository = problemaRepository;
	}

	@Autowired
	public void setTesteRepository(TesteRepository testeRepository) {
		this.testeRepository = testeRepository;
	}
	@Transactional(readOnly = false)
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (problemaRepository.findAll().isEmpty()) {
			for (int i = 0; i < 30; i++) {
				Problema problema = new Problema("problema " + i, "descrição " + i, null);
				problema.setPrivado(false);
				problema.setDica("dica " + i);
				List<Teste> testes = new ArrayList<>();
				for (int j = 0; j < 3; j++) {
					Teste teste = new Teste("teste " + j, "dica " + j, "entrada " + j, "saida " + j, false);
					testes.add(teste);
					testeRepository.save(teste);

				}
				problema.setTestes(testes);
				problemaRepository.save(problema);
			}
		}

	}

}
