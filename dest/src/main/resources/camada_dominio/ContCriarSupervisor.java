package camada_dominio;

import entidades.SituacaoPedidoDTO;
import exception.EstagioJaSupervisionadoEx;

public class ContCriarSupervisor {
	// public CamadaDadosInterface dados = new CamadaDadosMock();

	public enum Tipos {
		VERIFICAR_NUMERO_ESTAGIO, CRIAR_SUPERVISOR
	}

	public SituacaoPedidoDTO servico(Tipos tipoServico, int numeroPedidoEstagio) {
		switch (tipoServico) {
		case VERIFICAR_NUMERO_ESTAGIO:

			Command rt = new VerificarNumeroEstagioRTC(numeroPedidoEstagio);

			try {
				return (SituacaoPedidoDTO) rt.executar();
			} catch (EstagioJaSupervisionadoEx e) {
				e.printStackTrace();
			}

		default:
			return null;
		}
	}

	public Object servico(Tipos tipoServico, String nome, String email, String senha, String telefone,
			String nomeEmpresa, String cnpj, int numeroPedidoEstagio, String funcao) {
		switch (tipoServico) {
		case CRIAR_SUPERVISOR:
			Boolean emlValidator = validarEmail(email);
			Boolean payload = validarCampos(nome, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio, funcao);

			Command rt = new CriarSupervisorRTC(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio,
					funcao);

			try {
				return rt.executar();
			} catch (EstagioJaSupervisionadoEx e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static boolean validarEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		return email.matches(emailRegex);
	}

	public Boolean validarCampos(String nome, String senha, String telefone, String nomeEmpresa, String cnpj,
			Integer numeroPedidoEstagio, String funcao) {
		return !(isNullOrEmpty(nome) || isNullOrEmpty(senha) || isNullOrEmpty(telefone) || isNullOrEmpty(nomeEmpresa)
				|| isNullOrEmpty(cnpj) || numeroPedidoEstagio == null || isNullOrEmpty(funcao));
	}

	private boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
}
