package dirlididi.domain;

import java.util.ArrayList;
import java.util.List;

public class Normal extends Usuario {
	private List<Problema> problemas;
	private List<Solucao> solucoes;

	public Normal(String email, String senha) {
		super(email, senha);
		this.problemas = new ArrayList<>();
		this.solucoes = new ArrayList<>();
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
