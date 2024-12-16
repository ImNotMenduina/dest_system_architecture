package exception;

public class UsuarioLogadoEx extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UsuarioLogadoEx(String mensagem) {
		super(mensagem);
	}
}
