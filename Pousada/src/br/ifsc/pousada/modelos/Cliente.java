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
			if (cpf != null) {
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
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return cliente;
	}
	
	public static void carregarDadosClientes() {
		ObjectMapper objeto = new ObjectMapper();
		try {
			objeto.registerModule(new JavaTimeModule());
			String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\clientes.json");
			List<Cliente> clientes = objeto.readValue(json, new TypeReference<List<Cliente>>() {});
			int cont = 0;
			for(Cliente c : clientes) {
				System.err.println("Indice: [" + cont + "]");
				System.err.println("CPF: " + c.getCpf());
				System.err.println("Nome: " + c.getNome());
				System.err.println("Data Nascimento: " + c.getDtNascimento());
				System.err.println("Telefone: " + c.getTelefone());
				System.err.println("Email: " + c.getEmail());
				System.err.println("\n");
				cont++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
