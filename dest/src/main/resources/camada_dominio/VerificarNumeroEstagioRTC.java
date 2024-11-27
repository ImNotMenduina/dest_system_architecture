package camada_dominio;

import java.sql.Connection;

import camada_dados.Database;
import camada_dados.GatewayPedido;
import entidades.PedidoDTO;
import entidades.SituacaoPedidoDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class VerificarNumeroEstagioRTC implements Command {

	private int numeroPedidoEstagio;

	private GatewayPedido dados = new GatewayPedido();

	public VerificarNumeroEstagioRTC(int numeroPedidoEstagio) {
		this.numeroPedidoEstagio = numeroPedidoEstagio;
	}

	@Override
	public SituacaoPedidoDTO executar() {
		Database db = Database.getInstance();

		PedidoDTO pd;
		try {
			pd = dados.buscarPedido(numeroPedidoEstagio);
			
			try {
				pd = dados.buscarPedidoSupervisor(numeroPedidoEstagio);
			} catch (EstagioJaSupervisionadoEx e) {
				e.printStackTrace();
				return null;
			}
			
			String nomeAluno = pd.getNomeAluno();

			String nomeEmpresa = pd.getNomeEmpresa();
			
			return new SituacaoPedidoDTO(nomeAluno, nomeEmpresa, true);
			
		} catch (PedidoEstagioNExistenteEx e) {
			e.printStackTrace();
			return null;
		}
	}
}
