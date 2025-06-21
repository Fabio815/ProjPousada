package br.ifsc.pousada.modelos;

import java.time.LocalDate;

public class Pessoa {
	private String nome;
	private LocalDate dtNascimento;
	private String telefone;
	private String cpf;
	
	public Pessoa(String nome, LocalDate dtNascimento, String telefone, String cpf) {
		super();
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.telefone = telefone;
		this.cpf = cpf;
	}
	
	public Pessoa() {
		
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}
	
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
