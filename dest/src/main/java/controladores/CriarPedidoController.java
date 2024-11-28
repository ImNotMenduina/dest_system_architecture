package controladores;

import java.io.IOException;

import camada_dominio.ContCriarPedidoEstagio;
import entidades.SituacaoDiscenteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer ira = Integer.parseInt(request.getParameter("ira"));
		Integer chCumprida = Integer.parseInt(request.getParameter("ch_cumprida"));
		String endereco = (String) request.getParameter("endereco");

		System.out.println(ira + " " + chCumprida + " " + endereco);
		
		ContCriarPedidoEstagio ct = new ContCriarPedidoEstagio();
		
		SituacaoDiscenteDTO st = ct.servico(ira, chCumprida, endereco);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
