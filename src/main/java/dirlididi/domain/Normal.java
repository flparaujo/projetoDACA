package dirlididi.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "Normal")
@Table(name = "tb_user_normal")
public class Normal extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Problema> problemas;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Solucao> solucoes;

	public Normal(String email, String senha) {
		super(email, senha);
		this.problemas = new ArrayList<>();
		this.solucoes = new ArrayList<>();
	}

	public Normal() {

	}

	public List<Problema> getProblemas() {
		return problemas;
	}

	public void setProblemas(List<Problema> problemas) {
		this.problemas = problemas;
	}

	public List<Solucao> getSolucoes() {
		return solucoes;
	}

	public void setSolucoes(List<Solucao> solucoes) {
		this.solucoes = solucoes;
	}

}
