package exception;

public class UsuarioInvalidoEx extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UsuarioInvalidoEx(String mensagem) {
		super(mensagem);
	}
}
