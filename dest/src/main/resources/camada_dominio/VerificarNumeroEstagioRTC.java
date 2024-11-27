package camada_dominio;

import java.sql.Connection;

import camada_dados.Database;
import camada_dados.GatewayPedido;
import entidades.PedidoDTO;
import entidades.SituacaoPedidoDTO;
import entidades.SupervisorDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class VerificarNumeroEstagioRTC implements Command {

	private int numeroPedidoEstagio;

	private GatewayPedido dados = new GatewayPedido();

	public VerificarNumeroEstagioRTC(int numeroPedidoEstagio) {
		this.numeroPedidoEstagio = numeroPedidoEstagio;
	}

	@Override
	public SituacaoPedidoDTO executar() throws PedidoEstagioNExistenteEx, EstagioJaSupervisionadoEx {
		// Database db = Database.getInstance();

		PedidoDTO pd = null;

		pd = dados.buscarPedido(numeroPedidoEstagio);

		if (pd != null) {
			SupervisorDTO sp = dados.buscarPedidoSupervisor(numeroPedidoEstagio);

			if (sp == null) {
				String nomeAluno = pd.getNomeAluno();
				String nomeEmpresa = pd.getNomeEmpresa();
				return new SituacaoPedidoDTO(nomeAluno, nomeEmpresa, true);
			} else {
				throw new EstagioJaSupervisionadoEx("Estágio já supervisionado");
			}
		} else {
			throw new PedidoEstagioNExistenteEx("Pedido de Estágio Inexistente");
		}
	}
}
