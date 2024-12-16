package service;

import java.io.IOException;

import camada_dados.GatewayUser;
import camada_dominio.ContIdentificarUsuario;
import camada_dominio.ContIdentificarUsuario.Tipos;
import entidades.UsuarioDTO;
import exception.LoginInvalidoEx;
import exception.UsuarioInvalidoEx;
import exception.UsuarioLogadoEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class IdentificarUsuarioService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GatewayUser dados = new GatewayUser();

	private ContIdentificarUsuario ctrCommand = new ContIdentificarUsuario();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("password");

		RequestDispatcher dispatcher = null;
		UsuarioDTO user = null;
		boolean log = false;

		try {
			// HTTP SESSION - INICIA UMA SESSÃO PARA O CLIENTE
			HttpSession session = request.getSession(false);

			if (session != null && session.getAttribute("email") != null) {
				log = true;
			}

			user = ctrCommand.service(Tipos.IDENTIFICAR, email, senha, log);

			session.setAttribute("email", user.getEmail());
			request.setAttribute("mensagem", "Bem-vindo(a) " + user.getEmail());
			dispatcher = request.getRequestDispatcher("index.jsp");
		} catch (UsuarioInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: " + e.getMessage());
			dispatcher = request.getRequestDispatcher("login.jsp");
		} catch (LoginInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: " + e.getMessage());
			dispatcher = request.getRequestDispatcher("login.jsp");
		} catch (UsuarioLogadoEx e) {
			request.setAttribute("mensagem", "ERRO: " + e.getMessage());
			dispatcher = request.getRequestDispatcher("login.jsp");
		}
		dispatcher.forward(request, response);
	}
}
