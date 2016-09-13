package dirlididi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dirlididi.domain.Problema;
import dirlididi.repositories.ProblemaRepository;

@Service
public class ProblemaServiceImpl {
	@Autowired
	private ProblemaRepository problemaRepository;

	public void updateProblema(Problema problema) {
		if (problemaRepository.exists(problema.getId())) {
			problemaRepository.save(problema);
		}
	}
}
