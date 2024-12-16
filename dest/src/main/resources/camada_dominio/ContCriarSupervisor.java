package camada_dominio;

import java.io.IOException;

import camada_dados.GatewayPedido;
import camada_dados.GatewaySupervisor;
import entidades.PedidoDTO;
import entidades.SituacaoPedidoDTO;
import entidades.SupervisorDTO;
import exception.CampoInvalidoEx;
import exception.EmailInvalidoEx;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ContCriarSupervisor {
	public enum Tipos {
		VERIFICAR_NUMERO_ESTAGIO, CRIAR_SUPERVISOR
	}

	private GatewaySupervisor dadosSupervisor = new GatewaySupervisor();
	private GatewayPedido dadosPedido = new GatewayPedido();

	public SituacaoPedidoDTO service(Tipos tipoServico, int numeroPedidoEstagio)
			throws PedidoEstagioNExistenteEx, EstagioJaSupervisionadoEx {

		PedidoDTO pd = dadosPedido.buscarPedido(numeroPedidoEstagio);

		switch (tipoServico) {
		case VERIFICAR_NUMERO_ESTAGIO:
			Command rt = new VerificarPedidoRTC(pd);
			return (SituacaoPedidoDTO) rt.executar();
		default:
			return null;
		}
	}

	public void service(Tipos tipoServico, String nome, String email, String senha, String telefone, String nomeEmpresa,
			String cnpj, int numeroPedidoEstagio, String funcao) throws ServletException, IOException {

		SupervisorDTO supervisor = dadosPedido.buscarPedidoSupervisor(numeroPedidoEstagio);

		switch (tipoServico) {
		case CRIAR_SUPERVISOR:
			Command rt = new CriarSupervisorRTC(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio,
					funcao);
			rt.executar();

			// CRIA SUPERVISOR
			dadosSupervisor.inserir(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio, funcao);
			// BUSCA QUAL O ID DO SUPERVISOR
			Integer supervisorId = dadosSupervisor.buscarId(email);
			// ASSOCIA O SUPERVISOR AO PEDIDO INFORMADO FK
			dadosPedido.atribuirSupervisor(numeroPedidoEstagio, supervisorId);
		default:
			return;
		}
	}

}
