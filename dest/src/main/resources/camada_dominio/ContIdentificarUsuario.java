package camada_dominio;

import entidades.UsuarioDTO;
import entidades.UsuarioDTO.Situacao;
import exception.LoginInvalidoEx;
import exception.UsuarioInvalidoEx;

public class ContIdentificarUsuario {
	
	public enum Tipos {
		IDENTIFICAR;
	}

	public UsuarioDTO servico(Tipos tipoServico, String email, String senha) {
		switch (tipoServico) {
		case IDENTIFICAR:
			
			Command rt = new IdentificarUsuarioRTC(email, senha);
			
			try {
				return (UsuarioDTO) rt.executar();
			} catch (UsuarioInvalidoEx e) {
				return new UsuarioDTO(false, Situacao.USUARIO_INVALIDO);
			} catch (LoginInvalidoEx e) {
				return new UsuarioDTO(false, Situacao.LOGIN_INVALIDO);
			}
		
		default:
			return null;
		}
	}
}
