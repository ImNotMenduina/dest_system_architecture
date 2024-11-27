package controladores;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import camada_dados.Database;
import camada_dominio.ContCriarSupervisor;
import camada_dominio.ContCriarSupervisor.Tipos;
import entidades.SituacaoPedidoDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CriarSupervisorServelet
 */
public class CriarSupervisorServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarSupervisorServelet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer num = Integer.parseInt(request.getParameter("numero"));

		ContCriarSupervisor ct = new ContCriarSupervisor();

		SituacaoPedidoDTO st = ct.servico(Tipos.VERIFICAR_NUMERO_ESTAGIO, num);

		if (st == null) {
			request.setAttribute("mensagem", "ERRO: Pedido de Estágio (" + num + ") inexistente.");
		} else if (st.getSituacao() == false) {
			request.setAttribute("mensagem", "ERRO: O estágio (" + num + ") já supervisionado.");
		} else {
			request.setAttribute("nomeAluno", st.getNomeAluno());
			request.setAttribute("nomeEmpresa", st.getNomeEmpresa());
			request.setAttribute("numeroPedido", num);
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

		ContCriarSupervisor ct = new ContCriarSupervisor();
		ct.servico(Tipos.CRIAR_SUPERVISOR, nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedido, funcao);

		response.sendRedirect("index.html");
	}

}
