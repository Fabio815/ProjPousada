package br.ifsc.pousada.modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.daopersistir.LeitorJson;

public class Funcionario extends Pessoa {
	private String cargo;

	public Funcionario(String nome, LocalDate dtNascimento, String telefone, String cpf) {
		super(nome, dtNascimento, telefone, cpf);
		// TODO Auto-generated constructor stub
	}

	public Funcionario() {
		
	}
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public static Funcionario carregarFuncionario() {
		ObjectMapper objeto = new ObjectMapper();
		Funcionario f = null;
		try {
			String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\funcionario.json");
			objeto.registerModule(new JavaTimeModule());
			TypeReference<List<Funcionario>> tipoClasse = new TypeReference<List<Funcionario>>() {};
			List<Funcionario> funcionario = objeto.readValue(json, tipoClasse);
			f = funcionario.getFirst();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return f;
	}
}
