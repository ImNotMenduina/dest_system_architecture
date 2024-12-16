package camada_dominio;

import java.io.IOException;

import camada_dados.GatewayPedido;
import entidades.SituacaoDiscenteDTO;
import entidades.SituacaoPedidoDTO;
import exception.ChMaxNCumpridaEx;
import exception.ChNCumpridaEx;
import exception.IRAnAtendeEx;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ContCriarPedidoEstagio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GatewayPedido dadosPedido = new GatewayPedido();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer ira = Integer.parseInt(request.getParameter("ira"));
		Integer chCumprida = Integer.parseInt(request.getParameter("ch_cumprida"));
		String endereco = (String) request.getParameter("endereco");

		// System.out.println(ira + " " + chCumprida + " " + endereco);

		SituacaoDiscenteDTO st = null;
		try {
			Command rt = new VerificarPedidoEstagioRTC(ira, chCumprida, endereco);
			st = (SituacaoDiscenteDTO) rt.executar();
			
			HttpSession session = request.getSession();

			String email = (String) session.getAttribute("email");

			request.setAttribute("email", email);
			request.setAttribute("pedido", "pedido");
			request.setAttribute("ira", ira);
			request.setAttribute("endereco", endereco);
			request.setAttribute("chCumprida", chCumprida);
		} catch (ChNCumpridaEx e) {
			//st = new SituacaoDiscenteDTO(false, Situacao.CH_N_ATENDE);
			request.setAttribute("mensagem", "ERRO: CH Cumprida não atende aos requisitos (80h)");
		} catch (IRAnAtendeEx e) {
			//st = new SituacaoDiscenteDTO(false, Situacao.IRA_N_ATENDE);
			request.setAttribute("mensagem", "ERRO: IRA não atende aos requisitos minimos (6.0)");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String matricula = (String) request.getParameter("matricula");
		String ira = (String) request.getParameter("ira");
		String cargaHora = (String) request.getParameter("cargaHora");
		String endereco = (String) request.getParameter("endereco");
		String infoPrimeiro = (String) request.getParameter("infoPrimeiro");
		String nomeEmpresa = (String) request.getParameter("nomeEmpresa");
		String endEmpresa = (String) request.getParameter("endEmpresa");
		String modalidade = (String) request.getParameter("modalidade");
		String cargaHoraSem = (String) request.getParameter("cargaHoraSem");
		String valorBolsa = (String) request.getParameter("valorBolsa");
		String resumo = (String) request.getParameter("resumo");

		SituacaoPedidoDTO st = null;
		try {
			Command rt = new CriarPedidoDeEstagioRTC(nome, matricula, ira, cargaHora, endereco, infoPrimeiro,
					nomeEmpresa, endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo);
			
			st = (SituacaoPedidoDTO) rt.executar();
			
			dadosPedido.armazenarPedidoEstagio(nome, matricula, ira, cargaHora, endereco, infoPrimeiro, nomeEmpresa,
					endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo);
			
			request.setAttribute("mensagem", "Pedido Criado !");		
		} catch (ChMaxNCumpridaEx e) {
			st = new SituacaoPedidoDTO(false);
			request.setAttribute("mensagem", "ERRO: Carga horaria maxima semanal não atende aos requisitos (<= 30h)");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("criarPedido.jsp");
		dispatcher.forward(request, response);
	}
}
