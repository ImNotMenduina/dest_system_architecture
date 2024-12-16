package camada_dominio;

import exception.ChMaxNCumpridaEx;

public class CriarPedidoDeEstagioRTC implements Command {

	private String nome;
	private String matricula;
	private String cargaHora;
	private String endereco;
	private String infoPrimeiro;
	private String nomeEmpresa;
	private String endEmpresa;
	private String modalidade;
	private String cargaHoraSem;
	private String valorBolsa;
	private String resumo;
	private String ira;

	public CriarPedidoDeEstagioRTC(String nome, String matricula, String ira, String cargaHora, String endereco,
			String infoPrimeiro, String nomeEmpresa, String endEmpresa, String modalidade, String cargaHoraSem,
			String valorBolsa, String resumo) {
		this.nome = nome;
		this.matricula = matricula;
		this.cargaHora = cargaHora;
		this.endereco = endereco;
		this.infoPrimeiro = infoPrimeiro;
		this.nomeEmpresa = nomeEmpresa;
		this.endEmpresa = endEmpresa;
		this.modalidade = modalidade;
		this.cargaHoraSem = cargaHoraSem;
		this.valorBolsa = valorBolsa;
		this.resumo = resumo;
	}

	@Override
	public Object executar() throws ChMaxNCumpridaEx {
		Float ch_semanal = Float.parseFloat(this.cargaHoraSem);

		if (ch_semanal > 30.0) {
			throw new ChMaxNCumpridaEx("Carga horaria maxima semanal não atende aos requisitos (<= 30h)");
		}
		return null;
	}

}
