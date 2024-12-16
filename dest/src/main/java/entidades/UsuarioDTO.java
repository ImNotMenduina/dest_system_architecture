package entidades;

public class UsuarioDTO {
	
	public enum Situacao {
		USUARIO_INVALIDO, LOGIN_INVALIDO
	}
	
	private String email;
	private boolean situacao;
	private Situacao err;
	
	public UsuarioDTO(String email, boolean situacao) {
		this.email = email;
		this.situacao = situacao;
	}
	
	public UsuarioDTO(boolean situacao, Situacao err) {
		this.situacao = situacao;
		this.err = err;
	}
	
	public Situacao getErr() {
		return this.err;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean getSituacao() {
		return this.situacao;
	}

}
