package controladores;

import java.io.IOException;

import camada_dominio.ContCriarPedidoEstagio;
import camada_dominio.ContIdentificarUsuario;
import camada_dominio.ContIdentificarUsuario.Tipos;
import entidades.SituacaoDiscenteDTO;
import entidades.UsuarioDTO;
import entidades.UsuarioDTO.Situacao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.IdentificarUsuarioService;

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

		IdentificarUsuarioService service = new IdentificarUsuarioService();
		UsuarioDTO user = service.identificarUsuario(email, senha);

		if (user.getSituacao() == false) {
			if (user.getErr() == Situacao.USUARIO_INVALIDO) {
				request.setAttribute("mensagem", "ERRO: Usuario Invalido");
			} else if (user.getErr() == Situacao.LOGIN_INVALIDO) {
				request.setAttribute("mensagem", "ERRO: Um ou mais campos invalidos");
			}
		} else {
			HttpSession session = request.getSession();
			
			session.setAttribute("email", user.getEmail());
			request.setAttribute("mensagem", "Bem-vindo(a) " + user.getEmail());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
		dispatcher.forward(request, response);
	}

}
