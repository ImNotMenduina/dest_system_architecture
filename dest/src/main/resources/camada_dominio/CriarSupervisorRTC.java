package camada_dominio;

import java.sql.Connection;

import camada_dados.Database;
import camada_dados.GatewaySupervisor;
import entidades.SupervisorDTO;

public class CriarSupervisorRTC implements Command {
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String nomeEmpresa;
	private String cnpj;
	private int numeroPedidoEstagio;
	private String funcao;
	
	private GatewaySupervisor dados = new GatewaySupervisor();

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
	public Object executar() {	
		Connection connection = Database.getInstance().getConnection();
		
		SupervisorDTO supervisor = dados.buscar(numeroPedidoEstagio);
		
		if (supervisor == null) {			
			dados.inserir(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio, funcao);
		} else {
			System.out.println(supervisor.getEmail());
		}
		return supervisor;
	}
}
