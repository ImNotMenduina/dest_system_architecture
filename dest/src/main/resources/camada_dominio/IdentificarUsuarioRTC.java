package camada_dominio;

import camada_dados.GatewayUser;
import entidades.SupervisorDTO;
import entidades.UsuarioDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.LoginInvalidoEx;
import exception.PedidoEstagioNExistenteEx;
import exception.UsuarioInvalidoEx;
import exception.UsuarioLogadoEx;

public class IdentificarUsuarioRTC implements Command {

	private String email;
	private String senha;
	private boolean log;
	
	private ContIdentificarUsuario cont = null;

	public IdentificarUsuarioRTC(String email, String senha, ContIdentificarUsuario cont, boolean log) {
		this.email = email;
		this.senha = senha;
		this.cont = cont;
		this.log = log;
	}

	@Override
	public Object executar() throws UsuarioInvalidoEx, LoginInvalidoEx, UsuarioLogadoEx {
		
		if (log == true) {
			throw new UsuarioLogadoEx("O usuário já foi logado");
		}

		if (email.isEmpty() || senha.isEmpty()) {
			throw new LoginInvalidoEx("Um ou mais campos inválidos.");
		}
		
		//MENSAGEM PARA O CONTROLADOR BUSCAR O USUARIO
		UsuarioDTO user = cont.buscarUsuario(email, senha);
		
		if (user == null) {			
			throw new UsuarioInvalidoEx("Usuario invalido.");
		}	
		
		return user;
	} 
}
