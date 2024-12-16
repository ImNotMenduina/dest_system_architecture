package camada_dominio;

import java.io.IOException;

import camada_dados.GatewayPedido;
import entidades.SituacaoDiscenteDTO;
import entidades.SituacaoPedidoDTO;
import exception.ChMaxNCumpridaEx;
import exception.ChNCumpridaEx;
import exception.IRAnAtendeEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ContCriarPedidoEstagio {
	private GatewayPedido dadosPedido = new GatewayPedido();

	public enum Tipos {
		VERIFICAR_PEDIDO_DE_INTENCAO, CRIAR_PEDIDO_DE_ESTAGIO
	}

	public SituacaoDiscenteDTO service(Tipos tipoServico, Integer ira, Integer chCumprida, String endereco)
			throws ServletException, IOException {

		switch (tipoServico) {
		case VERIFICAR_PEDIDO_DE_INTENCAO:
			Command rt = new VerificarPedidoEstagioRTC(ira, chCumprida, endereco);
			return (SituacaoDiscenteDTO) rt.executar();
		default:
			return null;
		}
	}

	public SituacaoPedidoDTO service(Tipos tipoServico, String nome, String matricula, String ira, String cargaHora,
			String endereco, String infoPrimeiro, String nomeEmpresa, String endEmpresa, String modalidade,
			String cargaHoraSem, String valorBolsa, String resumo) throws ServletException, IOException {

		switch (tipoServico) {
		case CRIAR_PEDIDO_DE_ESTAGIO:
			Command rt = new CriarPedidoDeEstagioRTC(nome, matricula, ira, cargaHora, endereco, infoPrimeiro,
					nomeEmpresa, endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo);

			dadosPedido.armazenarPedidoEstagio(nome, matricula, ira, cargaHora, endereco, infoPrimeiro, nomeEmpresa,
					endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo);

			return (SituacaoPedidoDTO) rt.executar();
		default:
			return null;
		}
	}
}
