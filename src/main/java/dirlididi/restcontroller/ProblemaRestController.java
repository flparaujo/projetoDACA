package dirlididi.restcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.domain.Problema;
import dirlididi.domain.Solucao;
import io.swagger.annotations.ApiOperation;

@RestController
public class ProblemaRestController {
	
	private List<Problema> listProblema = new ArrayList<>();

	@ApiOperation(value = "Lista todos os problemas")
    @RequestMapping(value = "/api/problem/", method = RequestMethod.GET)
    public List<Problema> getProblemas() {
	List<Problema> listAuxProblema = new ArrayList<>();

	for (Problema problema : listProblema) {
	    if (!problema.isPrivado()) {
		listAuxProblema.add(problema);
	    }
	}

	Collections.sort(listAuxProblema, new Comparator<Problema>() {
	    @Override
	    public int compare(Problema p1, Problema p2) {
		return p1.getDataCriacao().compareTo(p2.getDataCriacao());
	    }
	});

	return listAuxProblema;
    }
	
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
		return p1.getDataCriacao().compareTo(p2.getDataCriacao());
	    }
	});

	return listAuxProblema.subList(0, listAuxProblema.size());
    }

    @ApiOperation(value = "Pesquisa um problema")
    @RequestMapping(value = "/api/problem/", method = RequestMethod.GET, params = { "codigo" })
    public Problema getProblema(@RequestParam(value = "codigo") String codigo) {
	for (Problema problema : listProblema) {
	    if (problema.getCodigo().equals(codigo) && !problema.isPrivado()) {
		return problema;
	    }
	}
	return null;
    }

    @ApiOperation(value = "Lista os problemas que o usuario resolveu")
    @RequestMapping(value = "/api/solved/", method = RequestMethod.GET)
    public Map<String, Boolean> getProblemasResolvidos() {
	Map<String, Boolean> problemasResolvidos = new HashMap<String, Boolean>();

	problemasResolvidos.put("Q8lIaDijI", true);
	problemasResolvidos.put("Q4wuqKxnM", true);

	return problemasResolvidos;
    }

    @ApiOperation(value = "Cadastrar um problema")
    @RequestMapping(value = "/api/problem/", method = RequestMethod.POST)
    public String criarProblema(@RequestBody Problema problema) {
	return "";
    }

    @ApiOperation(value = "Editar um problema")
    @RequestMapping(value = "/api/problem/{id}", method = RequestMethod.PUT)
    public String editarProblema(@PathVariable String id, @RequestBody Problema problema) {
	return "";
    }

    @ApiOperation(value = "Submeter uma solucao")
    @RequestMapping(value = "/api/solved/", method = RequestMethod.POST)
    public List<?> submeterSolucao(@RequestBody Solucao solucao) {
	return null;
    }


}
