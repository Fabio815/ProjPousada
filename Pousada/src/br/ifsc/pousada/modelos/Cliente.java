package br.ifsc.pousada.modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.daopersistir.LeitorJson;

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
	
	

	@Override
	public String toString() {
		return super.toString() + " email=" + email + "]";
	}

	public static Cliente carregarCliente(String cpf) {
		ObjectMapper objeto = new ObjectMapper();
		Cliente cliente = null;
		try {
			String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\clientes.json");
			objeto.registerModule(new JavaTimeModule());
			TypeReference<List<Cliente>> tipoClasse = new TypeReference<List<Cliente>>() {};
			List<Cliente> clientes = objeto.readValue(json, tipoClasse);
			for (Cliente c : clientes) {
				if (c.getCpf().equals(cpf)) {
					cliente = c;
					break;
				}
			}
			
			System.err.println(cliente.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cliente;
	}
	
	
	
}
