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
		int num = Integer.parseInt(request.getParameter("numero"));
		
		ContCriarSupervisor ct = new ContCriarSupervisor();
		
		SituacaoPedidoDTO st = ct.servico(Tipos.VERIFICAR_NUMERO_ESTAGIO, num);
		
		if (st == null) {
			request.setAttribute("mensagem", "ERRO: O estágio (" + num + ") já supervisionado.");
		} else {			
			request.setAttribute("nomeAluno", st.getNomeAluno());
			request.setAttribute("nomeEmpresa", st.getNomeEmpresa());
			request.setAttribute("numeroPedido", num);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
