package camada_dominio;

import java.sql.Connection;

import camada_dados.Database;
import camada_dados.GatewayPedido;
import camada_dados.GatewaySupervisor;
import entidades.SupervisorDTO;
import exception.EstagioJaSupervisionadoEx;

public class CriarSupervisorRTC implements Command {
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String nomeEmpresa;
	private String cnpj;
	private int numeroPedidoEstagio;
	private String funcao;
	
	private GatewaySupervisor dadossp = new GatewaySupervisor();
	private GatewayPedido dadospd = new GatewayPedido();

	public CriarSupervisorRTC(String nome, String email, String senha, String telefone, String nomeEmpresa, String cnpj,
			int numeroPedidoEstagio, String funcao) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.nomeEmpresa = nomeEmpresa;
		this.cnpj = cnpj;
		this.numeroPedidoEstagio = numeroPedidoEstagio;
		this.funcao = funcao;
	}

	@Override
	public Object executar() throws EstagioJaSupervisionadoEx {	
		//Connection connection = Database.getInstance().getConnection();
		
		SupervisorDTO supervisor = dadossp.buscar(numeroPedidoEstagio);
		
		if (supervisor == null) {			
			dadossp.inserir(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio, funcao);
			Integer supervisorId = dadossp.buscarId(email);
			dadospd.atribuirSupervisor(numeroPedidoEstagio, supervisorId);
		} else {
			throw new EstagioJaSupervisionadoEx("Estágio já supervisionado");
		}
		return supervisor;
	}
}
