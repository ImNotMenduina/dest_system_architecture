package camada_dominio;

import camada_dados.GatewayUser;
import entidades.SupervisorDTO;
import entidades.UsuarioDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class IdentificarUsuarioRTC implements Command {

	private String email;
	private String senha;

	private GatewayUser dados = new GatewayUser();

	public IdentificarUsuarioRTC(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	@Override
	public Object executar() throws EstagioJaSupervisionadoEx, PedidoEstagioNExistenteEx {
		// Connection connection = Database.getInstance().getConnection();
				
		UsuarioDTO user = dados.buscar(email, senha);
		
		return user;
	}

}
