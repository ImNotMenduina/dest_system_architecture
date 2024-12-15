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

	private GatewayUser dados = new GatewayUser();

	public IdentificarUsuarioRTC(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	@Override
	public Object executar() throws UsuarioInvalidoEx, LoginInvalidoEx {
		// Connection connection = Database.getInstance().getConnection();
		
		if (email.isEmpty() || senha.isEmpty()) {
			throw new LoginInvalidoEx("Um ou mais campos inválidos.");
		}
				
		UsuarioDTO user = dados.buscar(email, senha);
		
		if (user != null) {			
			return user;
		} else {
			throw new UsuarioInvalidoEx("Usuario invalido.");
		}
	}
}
