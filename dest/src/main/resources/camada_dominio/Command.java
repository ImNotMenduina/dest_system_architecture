package camada_dominio;

import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public interface Command {
	Object executar() throws EstagioJaSupervisionadoEx, PedidoEstagioNExistenteEx;
}
