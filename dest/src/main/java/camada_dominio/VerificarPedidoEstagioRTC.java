package camada_dominio;

import entidades.SituacaoDiscenteDTO;
import exception.ChNCumpridaEx;
import exception.IRAnAtendeEx;

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
	public Object executar() throws IRAnAtendeEx, ChNCumpridaEx {
		if (chCumprida >= 80.0) {
			if (ira >= 6.0) {
				//akeitasdpapsdl
				return new SituacaoDiscenteDTO(true);

			} else {
				throw new IRAnAtendeEx("IRA não à regra de negócio");
			}
		} else {
			throw new ChNCumpridaEx("Carga Horaria mínima não cumprida");
		}
	}

}
