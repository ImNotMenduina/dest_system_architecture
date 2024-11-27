package camada_dominio;

import entidades.SituacaoPedidoDTO;
import entidades.SituacaoPedidoDTO.Situacao;
import exception.CampoInvalidoEx;
import exception.EmailInvalidoEx;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class ContCriarSupervisor {
	// public CamadaDadosInterface dados = new CamadaDadosMock();

	public enum Tipos {
		VERIFICAR_NUMERO_ESTAGIO, CRIAR_SUPERVISOR
	}

	public SituacaoPedidoDTO servico(Tipos tipoServico, int numeroPedidoEstagio) {
		switch (tipoServico) {
		case VERIFICAR_NUMERO_ESTAGIO:

			try {
				Command rt = new VerificarNumeroEstagioRTC(numeroPedidoEstagio);
				return (SituacaoPedidoDTO) rt.executar();
			} catch (EstagioJaSupervisionadoEx e) {
				return new SituacaoPedidoDTO(false, Situacao.EXISTE_SUPERVISOR);
			} catch (PedidoEstagioNExistenteEx e) {
				return new SituacaoPedidoDTO(false, Situacao.INEXISTENTE);
			}

		default:
			return null;
		}
	}

	public Object servico(Tipos tipoServico, String nome, String email, String senha, String telefone,
			String nomeEmpresa, String cnpj, int numeroPedidoEstagio, String funcao)
			throws EmailInvalidoEx, CampoInvalidoEx {
		switch (tipoServico) {
		case CRIAR_SUPERVISOR:
			Boolean emlValidator = validarEmail(email);

			if (emlValidator) {
				Boolean payload = validarCampos(nome, senha, telefone, nomeEmpresa, cnpj, numeroPedidoEstagio, funcao);

				if (payload) {

					try {
						Command rt = new CriarSupervisorRTC(nome, email, senha, telefone, nomeEmpresa, cnpj,
								numeroPedidoEstagio, funcao);
						return rt.executar();
					} catch (EstagioJaSupervisionadoEx e) {
						return new SituacaoPedidoDTO(false, Situacao.EXISTE_SUPERVISOR);
					} catch (PedidoEstagioNExistenteEx e) {
						return new SituacaoPedidoDTO(false, Situacao.INEXISTENTE);
					}
				} else {
					throw new CampoInvalidoEx("Um ou mais campos estão inválidos");
				}
			} else {
				throw new EmailInvalidoEx("Email informado inválido");
			}

		default:
			return null;
		}
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
