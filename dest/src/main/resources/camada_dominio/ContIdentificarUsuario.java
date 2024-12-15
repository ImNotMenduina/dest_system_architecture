package camada_dominio;

import java.io.IOException;

import camada_dados.GatewayUser;
import entidades.UsuarioDTO;
import exception.LoginInvalidoEx;
import exception.UsuarioInvalidoEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ContIdentificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GatewayUser dados = new GatewayUser();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String senha = request.getParameter("password");

		UsuarioDTO user = null;		
		
		try {
			Command rt = new IdentificarUsuarioRTC(email, senha, this);
			user = (UsuarioDTO) rt.executar();
			
			//HTTP SESSION - INICIA UMA SESSÃO PARA O CLIENTE
			HttpSession session = request.getSession();
			
			session.setAttribute("email", user.getEmail());
			request.setAttribute("mensagem", "Bem-vindo(a) " + user.getEmail());
		} catch (UsuarioInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: Usuario Invalido");
		} catch (LoginInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: Um ou mais campos invalidos");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
		dispatcher.forward(request, response);
	}

	public UsuarioDTO buscarUsuario(String email, String senha) {
		return dados.buscar(email, senha);
	}
}
