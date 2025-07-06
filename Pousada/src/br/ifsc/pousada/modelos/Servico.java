package br.ifsc.pousada.modelos;

public class Servico {
	public enum TipoServico {
	    LAVANDERIA,
	    CAFE_DA_MANHA,
	    ALMOCO,
	    JANTAR,
	    SALA_REUNIAO,
	    SERVICO_DE_QUARTO
	}
	private TipoServico tipoServico;
	private Double valor;
	
	public Servico() {
		
	}
	
	public TipoServico getTipoServico() {
		return tipoServico;
	}
	
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
