package camada_dominio;

public class ContCriarSupervisor {
	// public CamadaDadosInterface dados = new CamadaDadosMock();

	public enum Tipos {
		VERIFICAR_NUMERO_ESTAGIO, CRIAR_SUPERVISOR
	}

	public Object servico(Tipos tipoServico, int numeroPedidoEstagio) {
		switch (tipoServico) {
		case VERIFICAR_NUMERO_ESTAGIO:
			Command rt = new VerificarNumeroEstagioRTC(numeroPedidoEstagio);
			return rt.executar();
		default:
			return null;
		}
	}

	public Object servico(Tipos tipoServico, String nome, String email, String senha, String telefone,
			String nomeEmpresa, String cnpj, String numeroPedidoEstagio, String funcao) {
		switch (tipoServico) {
		case CRIAR_SUPERVISOR:
			// Boolean emlValidator = validarEmail(email);
			// Boolean payload = validarCampos(nome, senha, telefone, nomeEmpresa, cnpj,
			// numeroPedidoEstagio, funcao);

			Command rt = new CriarSupervisorRTC(nome, email, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio,
					funcao);

			return rt.executar();
		}

		return null;
	}

	private static boolean validarCampos(String nome, String senha, String telefone, String nomeEmpresa, String cnpj,
			String numeroPedidoEstagio, String funcao) {
		return true;
	}

	public static boolean validarEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

		return email.matches(emailRegex);
	}
}
