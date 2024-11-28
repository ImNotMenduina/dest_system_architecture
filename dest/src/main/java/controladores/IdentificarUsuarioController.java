package controladores;

import java.io.IOException;

import camada_dominio.ContCriarPedidoEstagio;
import camada_dominio.ContIdentificarUsuario;
import camada_dominio.ContIdentificarUsuario.Tipos;
import entidades.SituacaoDiscenteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdentificarUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public IdentificarUsuarioController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		ContIdentificarUsuario ct = new ContIdentificarUsuario();
		
		System.out.println(email);
		System.out.println(senha);
		
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		
		ct.servico(Tipos.IDENTIFICAR, email, senha);
		
		System.out.println("okok");
	
	}

}
