package entidades;

public class SupervisorDTO {

	private boolean situacao;
	private int supervisorId;
	private String nome;
	private String email;

	public SupervisorDTO(int supervisorId, boolean situacao) {
		this.supervisorId = supervisorId;
		this.situacao = situacao;
	}
	
	public SupervisorDTO(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public int getId() {
		return this.supervisorId;
	}

	public boolean getSituacao() {
		return this.situacao;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
}
