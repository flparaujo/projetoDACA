package dirlididi.domain;

public class Solucao {
	private String emailDoProprietario;

	private String codigoProblema;

	private String corpoSolucao;

	private String entrada;

	private String saida;

	public Solucao(String corpoSolucao, String entrada, String saida) {
		super();
		this.corpoSolucao = corpoSolucao;
		this.entrada = entrada;
		this.saida = saida;
	}

	public String getEmailDoProprietario() {
		return emailDoProprietario;
	}

	public void setEmailDoProprietario(String emailDoProprietario) {
		this.emailDoProprietario = emailDoProprietario;
	}

	public String getCodigoProblema() {
		return codigoProblema;
	}

	public void setCodigoProblema(String codigoProblema) {
		this.codigoProblema = codigoProblema;
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
