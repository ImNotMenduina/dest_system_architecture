package service;

import java.io.IOException;

import camada_dominio.ContCriarSupervisor;
import camada_dominio.ContCriarSupervisor.Tipos;
import entidades.SituacaoPedidoDTO;
import exception.CampoInvalidoEx;
import exception.EmailInvalidoEx;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CriarSupervisorService extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContCriarSupervisor ctrCommand = new ContCriarSupervisor();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer numeroPedidoEstagio = Integer.parseInt(request.getParameter("numero"));

		SituacaoPedidoDTO st = null;

		try {
			st = ctrCommand.service(Tipos.VERIFICAR_NUMERO_ESTAGIO, numeroPedidoEstagio);

			request.setAttribute("nomeAluno", st.getNomeAluno());
			request.setAttribute("nomeEmpresa", st.getNomeEmpresa());
			request.setAttribute("numeroPedido", numeroPedidoEstagio);

		} catch (EstagioJaSupervisionadoEx e) {
			request.setAttribute("mensagem", "ERRO: Estágio (" + numeroPedidoEstagio + ") já supervisionado");
		} catch (PedidoEstagioNExistenteEx e) {
			request.setAttribute("mensagem", "ERRO: " + e.getMessage());
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

		RequestDispatcher dispatcher = null;

		try {
			ctrCommand.service(Tipos.CRIAR_SUPERVISOR, nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedido,
					funcao);
			request.setAttribute("mensagem", "Supervisor criado com sucesso.");
			dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
		} catch (EmailInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: " + e.getMessage());
			dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
		} catch (CampoInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: " + e.getMessage());
			dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
		} catch (EstagioJaSupervisionadoEx e) {
			request.setAttribute("mensagem", "ERRO: Estágio (" + numeroPedido + ") já supervisionado");
			dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
		}
		dispatcher.forward(request, response);
	}
}
