package exception;

public class PedidoEstagioNExistenteEx extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PedidoEstagioNExistenteEx() {
		super();
	}
	
	public PedidoEstagioNExistenteEx(String mensagem) {
		super(mensagem);
	}
}
