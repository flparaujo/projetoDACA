package dirlididi.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Teste {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Transient
	private String proprietario;
	@Transient
	private String nome;
	@Transient
	private String dica;
	@Transient
	private String entrada;
	@Transient
	private String saida;
	@Transient
	private boolean isPrivado;
	@Transient
	private Date data;

	public Teste(String nome, String dica, String entrada, String saida, boolean isPrivado) {
		this.nome = nome;
		this.dica = dica;
		this.entrada = entrada;
		this.saida = saida;
		this.isPrivado = isPrivado;
		this.data = new Date();
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		if (isPrivado) {
			return null;
		}
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public boolean isPrivado() {
		return isPrivado;
	}

	public void setPrivado(boolean isPrivado) {
		this.isPrivado = isPrivado;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
