package camada_dominio;

import exception.CampoInvalidoEx;
import exception.EmailInvalidoEx;

public class CriarSupervisorRTC implements Command {
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private String nomeEmpresa;
	private String cnpj;
	private int numeroPedidoEstagio;
	private String funcao;

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
		if (validarEmail(email) != true) {
			throw new EmailInvalidoEx("Email inválido");
		}
		
		if (validarCampos(nome, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio, funcao) != true) {
			throw new CampoInvalidoEx("Um ou mais campos não preenchidos");
		}
		
		return true;
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
