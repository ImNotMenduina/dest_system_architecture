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

public class ContCriarSupervisor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GatewaySupervisor dadosSupervisor = new GatewaySupervisor();
	private GatewayPedido dadosPedido = new GatewayPedido();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer numeroPedidoEstagio = Integer.parseInt(request.getParameter("numero"));

		SituacaoPedidoDTO st = null;

		try {
			PedidoDTO pd = dadosPedido.buscarPedido(numeroPedidoEstagio);
			SupervisorDTO sp = dadosPedido.buscarPedidoSupervisor(numeroPedidoEstagio);

			st = new SituacaoPedidoDTO(pd.getNomeAluno(), pd.getNomeEmpresa(), true);

			request.setAttribute("nomeAluno", st.getNomeAluno());
			request.setAttribute("nomeEmpresa", st.getNomeEmpresa());
			request.setAttribute("numeroPedido", numeroPedidoEstagio);

		} catch (EstagioJaSupervisionadoEx e) {
			request.setAttribute("mensagem", "ERRO: Estágio (" + numeroPedidoEstagio + ") já supervisionado");
		} catch (PedidoEstagioNExistenteEx e) {
			request.setAttribute("mensagem", "ERRO: Pedido de Estágio Inexistente");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("supervisor");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String telefone = request.getParameter("telefone");
		String nomeEmpresa = request.getParameter("nomeEmpresa");
		String cnpj = request.getParameter("cnpj");
		Integer numeroPedido = Integer.parseInt(request.getParameter("numeroPedidoEstagio"));
		String funcao = request.getParameter("funcao");

		try {
			SupervisorDTO supervisor = dadosPedido.buscarPedidoSupervisor(numeroPedido);

			Command rt = new CriarSupervisorRTC(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedido, funcao);
			rt.executar();

			// CRIA SUPERVISOR
			dadosSupervisor.inserir(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedido, funcao);
			// BUSCA QUAL O ID DO SUPERVISOR
			Integer supervisorId = dadosSupervisor.buscarId(email);
			// ASSOCIA O SUPERVISOR AO PEDIDO INFORMADO FK
			dadosPedido.atribuirSupervisor(numeroPedido, supervisorId);
			
			request.setAttribute("mensagem", "Supervisor criado com sucesso.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
			dispatcher.forward(request, response);
		} catch (EmailInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: Email inválido");
			RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
			dispatcher.forward(request, response);
		} catch (CampoInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: Um ou mais campos não preenchidos.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
			dispatcher.forward(request, response);
		} catch (EstagioJaSupervisionadoEx e) {
			request.setAttribute("mensagem", "ERRO: Estágio (" + numeroPedido + ") já supervisionado");
			RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
			dispatcher.forward(request, response);
		}
	}
}
