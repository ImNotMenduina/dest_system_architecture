package controladores;

import java.io.IOException;

import camada_dominio.ContCriarSupervisor;
import camada_dominio.ContCriarSupervisor.Tipos;
import entidades.SituacaoPedidoDTO;
import entidades.SituacaoPedidoDTO.Situacao;
import exception.CampoInvalidoEx;
import exception.EmailInvalidoEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CriarSupervisorService;

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

		String num = (String) request.getParameter("numero");

		CriarSupervisorService service = new CriarSupervisorService();
		SituacaoPedidoDTO st = service.informarNumeroEstagio(num);

		Integer n = Integer.parseInt(num);

		if (st.getSituacao() == false) {

			if (st.getErr() == Situacao.INEXISTENTE) {
				request.setAttribute("mensagem", "ERRO: Pedido de Estágio Inexistente");
			} else if (st.getErr() == Situacao.EXISTE_SUPERVISOR) {
				request.setAttribute("mensagem", "ERRO: Estágio (" + num + ") já supervisionado");
			}
		} else {
			request.setAttribute("nomeAluno", st.getNomeAluno());
			request.setAttribute("nomeEmpresa", st.getNomeEmpresa());
			request.setAttribute("numeroPedido", n);
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

		CriarSupervisorService service = new CriarSupervisorService();

		try {
			service.CriarSupervisor(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedido, funcao);
		} catch (EmailInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: Email inválido");
			RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
			dispatcher.forward(request, response);
		} catch (CampoInvalidoEx e) {
			request.setAttribute("mensagem", "ERRO: Um ou mais campos não preenchidos.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("criarSupervisor.jsp");
			dispatcher.forward(request, response);
		}

		response.sendRedirect("index.html");
	}

}
