package camada_dominio;

import entidades.PedidoDTO;
import entidades.SituacaoPedidoDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class VerificarPedidoRTC implements Command {
	PedidoDTO pedido = null;
	
	public VerificarPedidoRTC(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	@Override
	public Object executar() throws PedidoEstagioNExistenteEx  {
		//VERIFICA SE O PEDIDO EXISTE
		if (pedido == null) {
			throw new PedidoEstagioNExistenteEx("Pedido de Estágio Inexistente");
		}
		
		//VERIFICA SE PEDIDO JÁ SUPERVISIONADO
		if (pedido.getSupervisor() != null) {
			throw new EstagioJaSupervisionadoEx("Estágio já supervisionado");
		}
		
		return new SituacaoPedidoDTO(pedido.getNomeAluno(), pedido.getNomeEmpresa(), true);
	}

}
