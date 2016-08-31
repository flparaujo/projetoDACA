package dirlididi.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Problema implements Comparable<Problema> {
	private String proprietario;

	private String nome;

	private String descricao;

	private String codigo;

	private String dica;

	private boolean isPrivado;

	private Date dataCriacao;

	private List<Teste> testes = new ArrayList<>();

	public Problema(String proprietario, String nome, String descricao, String codigo, String dica, boolean isPrivado,
			ArrayList<Teste> testes) {
		this.proprietario = proprietario;
		this.nome = nome;
		this.descricao = descricao;
		this.codigo = codigo;
		this.dica = dica;
		this.isPrivado = isPrivado;
		this.dataCriacao = new Date();
		this.testes = testes;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDica() {
		return dica;
	}

	public void setDica(String dica) {
		this.dica = dica;
	}

	public boolean isPrivado() {
		return isPrivado;
	}

	public void setPrivado(boolean isPrivado) {
		this.isPrivado = isPrivado;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

	@Override
	public int compareTo(Problema p) {
		return getDataCriacao().compareTo(p.getDataCriacao());
	}
}
