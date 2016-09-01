package dirlididi.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "Teste")
@Table(name = "tb_teste")
public class Teste {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Transient
	private String proprietario;
	@Column
	private String nome;
	@Column
	private String dica;
	@Column
	private String entrada;
	@Column
	private String saida;
	@Column
	private boolean isPrivado;
	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Calendar dataDeCriacao;

	public Teste(String nome, String dica, String entrada, String saida, boolean isPrivado) {
		this.nome = nome;
		this.dica = dica;
		this.entrada = entrada;
		this.saida = saida;
		this.isPrivado = isPrivado;
		setDataDeCriacao(Calendar.getInstance());
	}

	public Teste() {
		setDataDeCriacao(Calendar.getInstance());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Calendar getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Calendar dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

}
