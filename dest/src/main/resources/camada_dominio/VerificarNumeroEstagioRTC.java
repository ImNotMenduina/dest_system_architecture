package camada_dominio;

import camada_dados.GatewayPedido;
import entidades.PedidoDTO;
import entidades.SituacaoPedidoDTO;

public class VerificarNumeroEstagioRTC implements Command{

	private int numeroPedidoEstagio;
	
	private GatewayPedido dados = new GatewayPedido(); 
	
	public VerificarNumeroEstagioRTC(int numeroPedidoEstagio) {
		this.numeroPedidoEstagio = numeroPedidoEstagio;
	}

	@Override
	public Object executar() {
		
		PedidoDTO pd = dados.buscar(numeroPedidoEstagio);
		
		String nomeSupervisor = pd.getNomeSupervisor();
		
		String nomeAluno = pd.getNomeAluno();
		
		String nomeEmpresa = pd.getNomeEmpresa();
		
		//System.out.println(nomeSupervisor);
		//System.out.println(nomeAluno);
		//System.out.println(nomeEmpresa);
		
		return new SituacaoPedidoDTO(nomeAluno, nomeEmpresa, true);
	}

}
