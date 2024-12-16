package entidades;

public class SituacaoDiscenteDTO {
	
	public enum Situacao {
		IRA_N_ATENDE, CH_N_ATENDE
	}
	
	private boolean situacao;
	private Situacao err;
	
	public SituacaoDiscenteDTO(boolean st) {
		this.situacao = st;
	}
	
	public SituacaoDiscenteDTO(boolean st, Situacao err) {
		this.situacao = st;
		this.err = err;
	}
	
	public boolean getSituacao() {
		return this.situacao;
	}
	
	public Situacao getErr() {
		return this.err;
	}

}
