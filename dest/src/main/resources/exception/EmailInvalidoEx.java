package exception;

public class EmailInvalidoEx extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailInvalidoEx(String mensagem) {
		super(mensagem);
	}
}
