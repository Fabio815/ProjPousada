package br.ifsc.pousada.modelos;

import java.time.LocalDate;

public class Reserva {
	private LocalDate dtEntrada;
	private LocalDate dtSaida;
	private Double valorTotal;
	
	public LocalDate getDtEntrada() {
		return dtEntrada;
	}
	
	public void setDtEntrada(LocalDate dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	
	public LocalDate getDtSaida() {
		return dtSaida;
	}
	
	public void setDtSaida(LocalDate dtSaida) {
		this.dtSaida = dtSaida;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
}
