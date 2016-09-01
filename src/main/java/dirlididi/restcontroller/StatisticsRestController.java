package dirlididi.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.domain.Normal;
import io.swagger.annotations.ApiOperation;

@RestController
public class StatisticsRestController {
	
	private Map<String, Integer> listSolucao = new HashMap<String, Integer>();
	private Map<String, Integer> listProblema = new HashMap<String, Integer>();
	private Normal usuarioLogado = new Normal("xpto@kkk.com", "Pi141516");

	@ApiOperation(value = "Retorna as estatisticas globais")
    @RequestMapping(value = "/api/stats/global", method = RequestMethod.GET)
    public Map<String, Integer> getEstatisticasGlobais() {
	Map<String, Integer> estatisticasGlobal = new HashMap<String, Integer>();

	estatisticasGlobal.put("submitters", listSolucao.size());
	estatisticasGlobal.put("problems", listProblema.size());

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
