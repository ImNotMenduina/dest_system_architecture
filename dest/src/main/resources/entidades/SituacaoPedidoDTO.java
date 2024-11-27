package entidades;

public class SituacaoPedidoDTO {
	private String nomeAluno;
	private String nomeEmpresa;
	private boolean situacao;

	public SituacaoPedidoDTO(String nomeAluno, String nomeEmpresa, boolean b) {
		this.nomeAluno = nomeAluno;
		this.nomeEmpresa = nomeEmpresa;
		this.situacao = b;
	}
	
	public SituacaoPedidoDTO(boolean b) {
		this.situacao = b;
	}
	
	public String getNomeAluno() {
		return this.nomeAluno;
	}
	
	public String getNomeEmpresa() {
		return this.nomeEmpresa;
	}
	
	public boolean getSituacao() {
		return this.situacao;
	}
}
