package entidades;

public class SituacaoPedidoDTO {
	
	public enum Situacao {
		INEXISTENTE, EXISTE_SUPERVISOR
	}
	
	private String nomeAluno;
	private String nomeEmpresa;
	private boolean situacao;
	private Situacao err =  null;
	
	public SituacaoPedidoDTO(boolean situacao, Situacao err) {
		this.situacao = situacao;
		this.err = err;
	}

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
	
	public Situacao getErr() {
		return this.err;
	}
}
