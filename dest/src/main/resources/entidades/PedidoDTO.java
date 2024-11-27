package entidades;

public class PedidoDTO {

	private String nomeAluno;
	private String nomeEmpresa;
	
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
}
