package dirlididi.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dirlididi.org.mindrot.jbcrypt.BCrypt;

@MappedSuperclass
public abstract class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column
	@JsonIgnore
	private String senha;
	@Column
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	public Usuario(String email, String senha, TipoUsuario tipo) {
		this.email = email;
		this.senha = senha;
		//this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
		this.tipo = tipo;
	}

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		//this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
		this.senha = senha;
	}

	public boolean checkSenha(String senha) {
		return BCrypt.checkpw(senha, this.senha);
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

}
