package br.ifsc.pousada.modelos;

import java.time.LocalDate;

public class Funcionario extends Pessoa{
	private String cargo;

	public Funcionario(String nome, LocalDate dtNascimento, String telefone, String cpf) {
		super(nome, dtNascimento, telefone, cpf);
		// TODO Auto-generated constructor stub
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
