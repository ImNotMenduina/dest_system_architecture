package exception;

public class CampoInvalidoEx extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CampoInvalidoEx(String mensagem) {
		super(mensagem);
	}
}
