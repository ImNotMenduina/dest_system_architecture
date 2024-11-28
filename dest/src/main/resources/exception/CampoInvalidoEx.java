package exception;

public class CampoInvalidoEx extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CampoInvalidoEx(String mensagem) {
		super(mensagem);
	}
}
