package dirlididi.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.DTO.ProblemaDTO;
import dirlididi.domain.Normal;
import dirlididi.domain.Problema;
import dirlididi.domain.Solucao;
import dirlididi.repositories.NormalRepository;
import dirlididi.repositories.ProblemaRepository;
import dirlididi.services.ProblemaServiceImpl;
import dirlididi.util.Util;
import io.swagger.annotations.ApiOperation;

@RestController
@SuppressWarnings("javadoc")
public class ProblemaRestController {
	@Autowired
	private ProblemaRepository problemaRepository;
	@Autowired
	private ProblemaServiceImpl problemaServiceImpl;
	@Autowired
	private NormalRepository normalRepository;
	private Normal usuarioLogado;

	private List<Problema> listProblema = new ArrayList<>();

	public void setUsuarioLogado(Normal usuarioLogado) {
		this.usuarioLogado = normalRepository.findNormalByEmail(Util.userNameUsuarioLogado());
	}

	@ApiOperation(value = "Lista todos os problemas")
	@RequestMapping(value = "/api/problem/", method = RequestMethod.GET)
	public List<Problema> getProblemas() {
		return problemaRepository.findAllPublic();
	}

	/**
	 * Revisar
	 * 
	 * @param numeroDaPagina
	 * @return
	 */
	@ApiOperation(value = "Retorna uma lista de problemas em um bloco de tamanho 100")
	@RequestMapping(value = "/api/problem/pagination/", method = RequestMethod.GET, params = { "numeroDaPagina" })
	public List<Problema> getProblemasPaginados(@RequestParam(value = "numeroDaPagina") Integer numeroDaPagina) {
		List<Problema> listAuxProblema = new ArrayList<>();

		for (Problema problema : listProblema) {
			if (!problema.isPrivado()) {
				listAuxProblema.add(problema);
			}
		}

		Collections.sort(listAuxProblema, new Comparator<Problema>() {
			@Override
			public int compare(Problema p1, Problema p2) {
				return p1.getDataDeCriacao().compareTo(p2.getDataDeCriacao());
			}
		});

		return listAuxProblema.subList(0, listAuxProblema.size());
	}

	@ApiOperation(value = "Pesquisa um problema")
	@RequestMapping(value = "/api/problem/{id}", method = RequestMethod.GET)
	public Problema getProblema(@PathVariable Long id) {
		return problemaRepository.findById(id);
	}

	@ApiOperation(value = "Lista os problemas que o usuario resolveu")
	@RequestMapping(value = "/api/solved/", method = RequestMethod.GET)
	public ResponseEntity<List<ProblemaDTO>> getProblemasResolvidos() {
		List<Problema> listProblemasResolvidosAux = usuarioLogado.getProblemas();

		if (listProblemasResolvidosAux.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		List<ProblemaDTO> listProblemasResolvidos = new ArrayList<>();

		for (Problema problema : listProblemasResolvidosAux) {
			listProblemasResolvidos.add(new ProblemaDTO(problema));
		}

		return new ResponseEntity<>(listProblemasResolvidos, HttpStatus.OK);
	}

	/**
	 * Admin
	 * 
	 * @param problema
	 * @return
	 */
	@ApiOperation(value = "Cadastrar um problema")
	@RequestMapping(value = "/api/problem/", method = RequestMethod.POST)
	public Problema criarProblema(@RequestBody Problema problema) {
		return problemaRepository.save(problema);
	}

	/**
	 * Admin
	 * 
	 * @param id
	 * @param problema
	 * @return
	 */
	@ApiOperation(value = "Editar um problema")
	@RequestMapping(value = "/api/problem/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Problema> editarProblema(@PathVariable Long id, @RequestBody Problema problema) {
		Problema problemaAtual = problemaRepository.findById(id);
		if (problemaAtual == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		problemaAtual.setNome(problema.getNome());
		problemaAtual.setDescricao(problema.getDescricao());
		problemaAtual.setDica(problema.getDica());
		problemaAtual.setPrivado(problema.isPrivado());

		problemaServiceImpl.updateProblema(problemaAtual);
		return new ResponseEntity<>(problemaAtual, HttpStatus.OK);
	}

	@ApiOperation(value = "Submeter uma solucao")
	@RequestMapping(value = "/api/solved/", method = RequestMethod.POST)
	public List<?> submeterSolucao(@RequestBody Solucao solucao) {

		return null;
	}

}
