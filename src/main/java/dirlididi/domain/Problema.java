package dirlididi.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "Problema")
@Table(name = "tb_problema")
public class Problema implements Comparable<Problema> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	private Administrador proprietario;
	@Column
	private String nome;
	@Column
	private String descricao;
	@Column
	private String codigo;
	@Column
	private String dica;
	@Column
	private boolean isPrivado;
	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Calendar dataDeCriacao;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Teste> testes = new ArrayList<>();
	
	public Problema() {
		setDataDeCriacao(Calendar.getInstance());
	}
	
	public Problema(String nome, String descricao, Administrador proprietario) {
		setNome(nome);
		setDescricao(descricao);
		setProprietario(proprietario);
		setDataDeCriacao(Calendar.getInstance());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Administrador getProprietario() {
		return proprietario;
	}

	public void setProprietario(Administrador proprietario) {
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

	public Calendar getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Calendar dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

	@Override
	public int compareTo(Problema p) {
		return getDataDeCriacao().compareTo(p.getDataDeCriacao());
	}
}
