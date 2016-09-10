package dirlididi.DTO;

import java.util.List;

import dirlididi.domain.Solucao;

public class EstatisticasUserDTO {
	private Integer pass;

	public EstatisticasUserDTO(List<Solucao> listSolucao) {
		this.pass = listSolucao.size();
	}

	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

}
