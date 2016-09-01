package dirlididi.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.DTO.EstisticasGlobalDTO;
import dirlididi.domain.Normal;
import dirlididi.repositories.ProblemaRepository;
import dirlididi.repositories.SolucaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
public class StatisticsRestController {
	@Autowired
	private ProblemaRepository problemaRepository;
	@Autowired
	private SolucaoRepository solucaoRepository;

	private Normal usuarioLogado = new Normal("xpto@kkk.com", "Pi141516");

	@ApiOperation(value = "Retorna as estatisticas globais")
	@RequestMapping(value = "/api/stats/global", method = RequestMethod.GET)
	public EstisticasGlobalDTO getEstatisticasGlobais() {
		EstisticasGlobalDTO estatisticasGlobal = new EstisticasGlobalDTO(solucaoRepository.findAll(),
				problemaRepository.findAll());

		return estatisticasGlobal;
	}

	@ApiOperation(value = "Retorna as estatisticas do usuario")
	@RequestMapping(value = "/api/stats/user", method = RequestMethod.GET)
	public Map<String, Integer> getEstatisticarDoUsuario() {
		Map<String, Integer> estatisticasDoUsuario = new HashMap<String, Integer>();

		estatisticasDoUsuario.put("pass", usuarioLogado.getSolucoes().size());

		return estatisticasDoUsuario;
	}

}
