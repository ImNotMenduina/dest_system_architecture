package camada_dominio;

import entidades.SituacaoDiscenteDTO;
import entidades.SituacaoPedidoDTO;

public class ContCriarPedidoEstagio {

	public enum Tipos {
		VERIFICAR_PEDIDO, CRIAR_PEDIDO
	}

	public SituacaoDiscenteDTO servico(Tipos tipoServico, Integer ira, Integer chCumprida, String endereco) {

		switch (tipoServico) {
		case VERIFICAR_PEDIDO:

			// Command rt = new VerificarPedidoEstagioRTC(ira, chCumprida, endereco);

		default:
			break;

		}

		return null;
	}

	public SituacaoPedidoDTO servico(Tipos tipoServico, String nome, String matricula, Integer ch, String endereco,
			String infoPrimeiro, String nomeEmpresa, String endEmpresa, String modalidade, Integer chSemanal,
			String valorBolsa, String resumo) {
		
		switch (tipoServico) {
		case CRIAR_PEDIDO:

			// Command rt = new VerificarPedidoEstagioRTC(ira, chCumprida, endereco);

		default:
			break;

		}

		return null;
	}

}
