package camada_dominio;

import camada_dominio.ContCriarPedidoEstagio.Tipos;
import entidades.SituacaoDiscenteDTO;
import entidades.SituacaoDiscenteDTO.Situacao;
import entidades.SituacaoPedidoDTO;
import exception.ChMaxNCumpridaEx;
import exception.ChNCumpridaEx;
import exception.IRAnAtendeEx;

public class ContCriarPedidoEstagio {

	public enum Tipos {
		VERIFICAR_PEDIDO, CRIAR_PEDIDO
	}

	public SituacaoDiscenteDTO servico(Tipos tipoServico, Integer ira, Integer chCumprida, String endereco) {

		switch (tipoServico) {
		case VERIFICAR_PEDIDO:

			try {
				Command rt = new VerificarPedidoEstagioRTC(ira, chCumprida, endereco);
				return (SituacaoDiscenteDTO) rt.executar();
			} catch (ChNCumpridaEx e) {
				return new SituacaoDiscenteDTO(false, Situacao.CH_N_ATENDE);
			} catch (IRAnAtendeEx e) {
				return new SituacaoDiscenteDTO(false, Situacao.IRA_N_ATENDE);
			}

		default:
			break;

		}

		return null;
	}

	public SituacaoPedidoDTO servico(Tipos tipoServico, String nome, String matricula, String ira, String cargaHora,
			String endereco, String infoPrimeiro, String nomeEmpresa, String endEmpresa, String modalidade,
			String cargaHoraSem, String valorBolsa, String resumo) {

		switch (tipoServico) {
		case CRIAR_PEDIDO:

			try {
				Command rt = new CriarPedidoDeEstagioRTC(nome, matricula, ira, cargaHora, endereco, infoPrimeiro,
						nomeEmpresa, endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo);
				return (SituacaoPedidoDTO) rt.executar();
			} catch (ChMaxNCumpridaEx e) {
				return new SituacaoPedidoDTO(false);
			}

		default:
			break;

		}

		return null;
	}

}
