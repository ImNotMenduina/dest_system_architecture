package exception;

public class EstagioJaSupervisionadoEx extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EstagioJaSupervisionadoEx() {
		super();
	}
	
	public EstagioJaSupervisionadoEx(String mensagem) {
		super(mensagem);
	}
}
