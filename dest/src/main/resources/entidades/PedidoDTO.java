package entidades;

public class PedidoDTO {

	private String nomeAluno;
	private String nomeEmpresa;
	private SupervisorDTO supervisor = null;
	
	public PedidoDTO(String nomeAluno, String nomeEmpresa, SupervisorDTO supervisor) {
		this.nomeAluno = nomeAluno;
		this.nomeEmpresa = nomeEmpresa;
		this.supervisor = supervisor;
	}
	
	public PedidoDTO(String nomeAluno, String nomeEmpresa) {
		this.nomeAluno = nomeAluno;
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeAluno() {
		return this.nomeAluno;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	
	public SupervisorDTO getSupervisor() {
		return this.supervisor;
	}
}
