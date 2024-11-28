package service;

import camada_dominio.ContCriarSupervisor;
import camada_dominio.ContCriarSupervisor.Tipos;
import entidades.SituacaoPedidoDTO;

public class CriarSupervisorService {
	public CriarSupervisorService() {
	}

	public SituacaoPedidoDTO informarNumeroEstagio(String numeroPedidoEstagio) {
		Integer num = Integer.parseInt(numeroPedidoEstagio);
		ContCriarSupervisor ct = new ContCriarSupervisor();
		SituacaoPedidoDTO st = ct.servico(Tipos.VERIFICAR_NUMERO_ESTAGIO, num);
		return st;
	}

	public void CriarSupervisor(String nome, String email, String senha, String telefone, String nomeEmpresa,
			String cnpj, int numeroPedidoEstagio, String funcao) {
		ContCriarSupervisor ct = new ContCriarSupervisor();
		ct.servico(Tipos.CRIAR_SUPERVISOR, nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio,
				funcao);
	}
}
