package br.ifsc.pousada.modelos;

import java.time.LocalDate;

public class Pagamento {
	public enum FormaPagamento { CARTAO, PIX, DINHEIRO }
	private Reserva reserva;
	private FormaPagamento formaPagamento;
	private LocalDate dtPagamento;
	
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public LocalDate getDtPagamento() {
		return dtPagamento;
	}
	
	public void setDtPagamento(LocalDate dtPagamento) {
		this.dtPagamento = dtPagamento;
	}
}
