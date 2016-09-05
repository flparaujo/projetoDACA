package dirlididi.domain;

public enum TipoUsuario {
	ADMIN("ADMIN"), NORMAL("NORMAL");

	private String tipo;

	TipoUsuario(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
}
