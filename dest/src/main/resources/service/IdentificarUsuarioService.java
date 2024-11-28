package service;

import camada_dominio.ContIdentificarUsuario;
import camada_dominio.ContIdentificarUsuario.Tipos;
import entidades.UsuarioDTO;

public class IdentificarUsuarioService {
	public IdentificarUsuarioService() {
	}

	public UsuarioDTO identificarUsuario(String email, String senha) {
		ContIdentificarUsuario ct = new ContIdentificarUsuario();
		UsuarioDTO user = ct.servico(Tipos.IDENTIFICAR, email, senha);
		return user;
	}
}
