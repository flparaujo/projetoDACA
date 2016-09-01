package dirlididi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Solucao")
@Table(name = "tb_solucao")
public class Solucao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Normal proprietarioDaSolucao;
	@Column
	private String corpoSolucao;
	@Column
	private String entrada;
	@Column
	private String saida;

	public Solucao(String corpoSolucao, String entrada, String saida) {
		this.corpoSolucao = corpoSolucao;
		this.entrada = entrada;
		this.saida = saida;
	}

	public Solucao() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Normal getProprietarioDaSolucao() {
		return proprietarioDaSolucao;
	}

	public void setProprietarioDaSolucao(Normal proprietarioDaSolucao) {
		this.proprietarioDaSolucao = proprietarioDaSolucao;
	}

	public String getCorpoSolucao() {
		return corpoSolucao;
	}

	public void setCorpoSolucao(String corpoSolucao) {
		this.corpoSolucao = corpoSolucao;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

}
