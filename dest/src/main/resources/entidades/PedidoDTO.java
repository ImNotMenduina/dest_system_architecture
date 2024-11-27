package entidades;

public class PedidoDTO {
	
	private String nomeSupervisor;
	private String nomeAluno;
	private String nomeEmpresa;
	
	public PedidoDTO(String nomeSupervisor, String nomeAluno, String nomeEmpresa) {
		this.nomeSupervisor = nomeSupervisor;
		this.nomeAluno = nomeAluno;
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeSupervisor() {
		return nomeSupervisor;
	}

	public String getNomeAluno() {
		return this.nomeAluno;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
}
