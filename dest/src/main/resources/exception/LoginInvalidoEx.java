package exception;

public class LoginInvalidoEx extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginInvalidoEx(String mensagem) {
		super(mensagem);
	}
}
