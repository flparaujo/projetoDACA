package dirlididi.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Administrador")
@Table(name = "tb_user_admin")
public class Administrador extends Usuario {

	public Administrador(String email, String senha) {
		super(email, senha, TipoUsuario.ADMIN);
	}

}
