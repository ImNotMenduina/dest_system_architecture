package service;

import camada_dominio.ContCriarPedidoEstagio;
import camada_dominio.ContCriarPedidoEstagio.Tipos;
import entidades.SituacaoDiscenteDTO;
import entidades.SituacaoPedidoDTO;

public class CriarPedidoIntencaoService {
	public CriarPedidoIntencaoService() {
	}

	public SituacaoDiscenteDTO verificarPedidoDeEstagio(Integer ira, Integer chCumprida, String endereco) {
		ContCriarPedidoEstagio ct = new ContCriarPedidoEstagio();
		SituacaoDiscenteDTO st = ct.servico(Tipos.VERIFICAR_PEDIDO, ira, chCumprida, endereco);
		return st;
	}

	public SituacaoPedidoDTO CriarPedidoDeEstagio(String nome, String matricula, String ira, String cargaHora,
			String endereco, String infoPrimeiro, String nomeEmpresa, String endEmpresa, String modalidade,
			String cargaHoraSem, String valorBolsa, String resumo) {

		ContCriarPedidoEstagio ct = new ContCriarPedidoEstagio();
		SituacaoPedidoDTO st = ct.servico(Tipos.CRIAR_PEDIDO, nome, matricula, ira, cargaHora, endereco, infoPrimeiro,
				nomeEmpresa, endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo);
		return st;
	}
}
