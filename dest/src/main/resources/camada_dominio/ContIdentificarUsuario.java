package camada_dominio;

import java.io.IOException;

import camada_dados.GatewayUser;
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

public class ContIdentificarUsuario {

	private GatewayUser dados = new GatewayUser();

	public enum Tipos {
		IDENTIFICAR
	}

	public UsuarioDTO service(Tipos tipoServico, String email, String senha, boolean log)
			throws ServletException, IOException {
		// HTTP SESSION - INICIA UMA SESSÃO PARA O CLIENTE
		switch (tipoServico) {
		case IDENTIFICAR:
			Command rt = new IdentificarUsuarioRTC(email, senha, this, log);
			return (UsuarioDTO) rt.executar();
		default:
			return null;
		}
	}

	public UsuarioDTO buscarUsuario(String email, String senha) {
		return dados.buscar(email, senha);
	}
}
