package camada_dominio;

import camada_dados.GatewayUser;
import entidades.SupervisorDTO;
import entidades.UsuarioDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.LoginInvalidoEx;
import exception.PedidoEstagioNExistenteEx;
import exception.UsuarioInvalidoEx;

public class IdentificarUsuarioRTC implements Command {

	private String email;
	private String senha;

	private ContIdentificarUsuario cont = null;

	public IdentificarUsuarioRTC(String email, String senha, ContIdentificarUsuario cont) {
		this.email = email;
		this.senha = senha;
		this.cont = cont;
	}

	@Override
	public Object executar() throws UsuarioInvalidoEx, LoginInvalidoEx {

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
