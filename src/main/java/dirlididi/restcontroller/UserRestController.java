package dirlididi.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dirlididi.domain.Normal;
import dirlididi.domain.Usuario;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserRestController {
	
	private List<Usuario> listUsuario = new ArrayList<>();
	
	@ApiOperation(value = "Cadastrar um usuario")
    @RequestMapping(value = "/api/user", method = RequestMethod.POST, params = { "email", "senha" })
    public Usuario cadastrar(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
	Normal usuario = new Normal(email, senha);
	listUsuario.add(usuario);

	return usuario;
    }
	
	@ApiOperation(value = "Lista todos os usuarios cadastrados")
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public List<Usuario> getUsuario() {
	return listUsuario;
    }
	
	@ApiOperation(value = "Lista os usuários que mais enviaram soluções")
    @RequestMapping(value = "/api/rank/", method = RequestMethod.GET)
    public List<Usuario> getUsuariosQueMaisResolveram() {
	// TODO
	return null;
    }
}
