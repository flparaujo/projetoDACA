package dirlididi.DTO;

import java.util.List;

import dirlididi.domain.Problema;
import dirlididi.domain.Solucao;

public class EstisticasGlobalDTO {
	private Integer submitters;
	private Integer problems;

	public EstisticasGlobalDTO(List<Solucao> listSolucao, List<Problema> listProblema) {
		this.submitters = listSolucao.size();
		this.problems = listProblema.size();
	}

	public Integer getSubmitters() {
		return submitters;
	}

	public void setSubmitters(Integer submitters) {
		this.submitters = submitters;
	}

	public Integer getProblems() {
		return problems;
	}

	public void setProblems(Integer problems) {
		this.problems = problems;
	}

}
