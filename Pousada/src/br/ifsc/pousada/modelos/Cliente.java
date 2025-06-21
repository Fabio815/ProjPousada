package br.ifsc.pousada.modelos;

import java.time.LocalDate;

public class Cliente extends Pessoa {
	private String email;
	
	public Cliente(String nome, LocalDate dtNascimento, String telefone, String cpf, String email) {
		super(nome, dtNascimento, telefone, cpf);
		this.email = email;
	}
	
	public Cliente() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
