package entidades;

public class UsuarioDTO {
	
	private String email;
	private boolean situacao;
	
	public UsuarioDTO(String email, boolean situacao) {
		this.email = email;
		this.situacao = situacao;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean getSituacao() {
		return this.situacao;
	}

}
