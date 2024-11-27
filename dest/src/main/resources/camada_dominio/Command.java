package camada_dominio;

import exception.EstagioJaSupervisionadoEx;

public interface Command {
	Object executar() throws EstagioJaSupervisionadoEx;
}
