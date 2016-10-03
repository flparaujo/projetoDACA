package dirlididi.restcontroller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.domain.Normal;
import dirlididi.repositories.NormalRepository;
import dirlididi.services.NormalServiceImpl;
import io.swagger.annotations.ApiOperation;

@RestController
@SuppressWarnings("javadoc")
public class UserRestController {
	@Autowired
	private NormalRepository normalRepository;
	@Autowired
	private NormalServiceImpl normalService;

	@ApiOperation(value = "Cadastrar um usuario")
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public Normal cadastrarUsuario(@RequestBody Normal normal) {
		return normalRepository.save(normal);
	}

	@ApiOperation(value = "Atualizar um usuario")
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Normal> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Normal usuarioNormal) {
		Normal usarioAtual = normalRepository.findOne(id);
		if (usarioAtual == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		usarioAtual.setEmail(usuarioNormal.getEmail());
		usarioAtual.setSenha(usuarioNormal.getSenha());

		normalService.updateNormal(usarioAtual);

		return new ResponseEntity<>(usarioAtual, HttpStatus.OK);
	}

	@ApiOperation(value = "Deletar um usuario")
	@RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Normal> deletarUsuario(@PathVariable("id") Long id) {
		Normal usuarioNormal = normalRepository.findOne(id);

		if (usuarioNormal == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		normalRepository.delete(usuarioNormal);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Lista todos os usuarios cadastrados")
	@RequestMapping(value = "/api/user", method = RequestMethod.GET)
	public List<Normal> getUsuarios() {
		return normalRepository.findAll();
	}

	@ApiOperation(value = "Lista os 10 usuários que mais enviaram soluções")
	@RequestMapping(value = "/api/rank/", method = RequestMethod.GET)
	public ResponseEntity<List<Normal>> getUsuariosQueMaisResolveram() {
		List<Normal> listNormal = normalRepository.findAll();
		if (listNormal.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Collections.sort(listNormal);
		return new ResponseEntity<>(listNormal.subList(0, 10), HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/";
	}
}
