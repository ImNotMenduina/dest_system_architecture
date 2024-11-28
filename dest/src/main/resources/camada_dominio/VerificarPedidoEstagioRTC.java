package camada_dominio;

import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class VerificarPedidoEstagioRTC implements Command {
	
	private Integer ira;
	private Integer chCumprida;
	private String endereco;

	public VerificarPedidoEstagioRTC(Integer ira, Integer chCumprida, String endereco) {
		this.ira = ira;
		this.chCumprida = chCumprida;
		this.endereco = endereco;
	}

	@Override
	public Object executar() throws EstagioJaSupervisionadoEx, PedidoEstagioNExistenteEx {
		if (chCumprida >= 80.0) {
			if (ira >= 6.0) {
				
			}
		}
		
		return null;
	}

}
