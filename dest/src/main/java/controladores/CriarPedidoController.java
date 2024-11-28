package controladores;

import java.io.IOException;

import camada_dominio.ContCriarPedidoEstagio;
import camada_dominio.ContCriarPedidoEstagio.Tipos;
import camada_dominio.ContIdentificarUsuario;
import entidades.SituacaoDiscenteDTO;
import entidades.SituacaoDiscenteDTO.Situacao;
import entidades.SituacaoPedidoDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CriarPedidoController
 */
public class CriarPedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CriarPedidoController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer ira = Integer.parseInt(request.getParameter("ira"));
		Integer chCumprida = Integer.parseInt(request.getParameter("ch_cumprida"));
		String endereco = (String) request.getParameter("endereco");

		// System.out.println(ira + " " + chCumprida + " " + endereco);
		ContCriarPedidoEstagio ct = new ContCriarPedidoEstagio();

		SituacaoDiscenteDTO st = ct.servico(Tipos.VERIFICAR_PEDIDO, ira, chCumprida, endereco);

		if (st.getSituacao() == true) {
			HttpSession session = request.getSession();

			String email = (String) session.getAttribute("email");
			
			request.setAttribute("email", email);
			request.setAttribute("pedido", "pedido");
			request.setAttribute("ira", ira);
			request.setAttribute("endereco", endereco);
			request.setAttribute("chCumprida", chCumprida);
		} else {
			if (st.getErr() == Situacao.CH_N_ATENDE) {
				request.setAttribute("mensagem", "ERRO: CH Cumprida não atende aos requisitos (80h)");
			} else if (st.getErr() == Situacao.IRA_N_ATENDE) {
				request.setAttribute("mensagem", "ERRO: IRA não atende aos requisitos minimos (6.0)");
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
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
