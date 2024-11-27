package entidades;

public class SituacaoPedidoDTO {
	private String nomeAluno;
	private String nomeEmpresa;

	public SituacaoPedidoDTO(String nomeAluno, String nomeEmpresa, boolean b) {
		this.nomeAluno = nomeAluno;
		this.nomeEmpresa = nomeEmpresa;
	}
	
	public String getNomeAluno() {
		return this.nomeAluno;
	}
	
	public String getNomeEmpresa() {
		return this.nomeEmpresa;
	}
}
