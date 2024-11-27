package entidades;

public class SupervisorDTO {
	
	private String nome;
	private String email;

	public SupervisorDTO(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
}
