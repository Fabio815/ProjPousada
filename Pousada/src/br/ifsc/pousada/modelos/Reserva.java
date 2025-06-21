package br.ifsc.pousada.modelos;

import java.time.LocalDate;
import java.util.List;

public class Reserva {
	private LocalDate dtEntrada;
	private LocalDate dtSaida;
	private Double valorTotal;
	private Cliente cliente;
	private Funcionario funcionario;
	private Quarto quarto;
	private List<Servico> servicos;
	
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
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
}
