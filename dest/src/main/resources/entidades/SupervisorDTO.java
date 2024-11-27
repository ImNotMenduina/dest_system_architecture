package entidades;

public class SupervisorDTO {

	private boolean situacao;
	private int supervisorId;

	public SupervisorDTO(int supervisorId, boolean situacao) {
		this.supervisorId = supervisorId;
		this.situacao = situacao;
	}

	public int getId() {
		return this.supervisorId;
	}

	public boolean getSituacao() {
		return this.situacao;
	}
}
