package dirlididi.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.DTO.EstatisticasUserDTO;
import dirlididi.DTO.EstisticasGlobalDTO;
import dirlididi.domain.Normal;
import dirlididi.repositories.NormalRepository;
import dirlididi.repositories.ProblemaRepository;
import dirlididi.repositories.SolucaoRepository;
import dirlididi.util.Util;
import io.swagger.annotations.ApiOperation;

@RestController
@SuppressWarnings("javadoc")
public class StatisticsRestController {
	@Autowired
	private ProblemaRepository problemaRepository;
	@Autowired
	private SolucaoRepository solucaoRepository;
	@Autowired
	private NormalRepository normalRepository;

	@ApiOperation(value = "Retorna as estatisticas globais")
	@RequestMapping(value = "/api/stats/global", method = RequestMethod.GET)
	public ResponseEntity<EstisticasGlobalDTO> getEstatisticasGlobais() {
		EstisticasGlobalDTO estatisticasGlobal = new EstisticasGlobalDTO(solucaoRepository.findAll(),
				problemaRepository.findAll());

		return new ResponseEntity<>(estatisticasGlobal, HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna as estatisticas do usuario")
	@RequestMapping(value = "/api/stats/user", method = RequestMethod.GET)
	public ResponseEntity<EstatisticasUserDTO> getEstatisticarDoUsuario() {
		Normal usuarioLogado = normalRepository.findNormalByEmail(Util.userNameUsuarioLogado());

		EstatisticasUserDTO estatisticasDoUsuario = new EstatisticasUserDTO(usuarioLogado.getSolucoes());

		return new ResponseEntity<>(estatisticasDoUsuario, HttpStatus.OK);
	}

}
