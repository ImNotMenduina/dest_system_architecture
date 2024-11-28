package camada_dominio;

import camada_dados.GatewayUser;
import entidades.SituacaoPedidoDTO;
import entidades.SituacaoPedidoDTO.Situacao;
import entidades.UsuarioDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

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
			} catch (EstagioJaSupervisionadoEx | PedidoEstagioNExistenteEx e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
//			try {
//				Command rt = new VerificarNumeroEstagioRTC(numeroPedidoEstagio);
//				return (SituacaoPedidoDTO) rt.executar();
//			} catch (EstagioJaSupervisionadoEx e) {
//				return new SituacaoPedidoDTO(false, Situacao.EXISTE_SUPERVISOR);
//			} catch (PedidoEstagioNExistenteEx e) {
//				return new SituacaoPedidoDTO(false, Situacao.INEXISTENTE);
//			}

		default:
			return null;
		}
	}
}
