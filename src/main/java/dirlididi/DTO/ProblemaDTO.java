package dirlididi.DTO;

import dirlididi.domain.Problema;

public class ProblemaDTO {
	private Long codigo;
	private boolean resolvido;

	public ProblemaDTO(Problema problema) {
		setCodigo(problema.getId());
		setResolvido(true);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public boolean isResolvido() {
		return resolvido;
	}

	public void setResolvido(boolean resolvido) {
		this.resolvido = resolvido;
	}

}
