package dirlididi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dirlididi.domain.Normal;
import dirlididi.repositories.NormalRepository;

@Service
public class NormalServiceImpl {
	@Autowired
	private NormalRepository normalRepository;

	public void updateNormal(Normal normal) {
		if (isNormalExist(normal)) {
			normalRepository.save(normal);
		}
	}

	public boolean isNormalExist(Normal normal) {
		return normalRepository.exists(normal.getId());
	}
}
