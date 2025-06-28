package br.ifsc.pousada.daopersistir;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.modelos.Cliente;
import br.ifsc.pousada.modelos.Pessoa;

public class FazerReserva {
	private static final Scanner leitura = new Scanner(System.in);
	
	public static void cadastroReserva() {
		System.err.println("""
				[1] Listar Clientes
				[2] Cadastrar Reserva
				""");
		System.err.print("Opção: ");
		int opcao = leitura.nextInt();
		switch (opcao) {
			case 1:
				carregarDadosClientes();
				break;
		}
	}
	
	public static void carregarDadosClientes() {
		ObjectMapper objeto = new ObjectMapper();
		try {
			objeto.registerModule(new JavaTimeModule());
			String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\clientes.json");
			List<Cliente> clientes = objeto.readValue(json, new TypeReference<List<Cliente>>() {});
			for(Cliente c : clientes) {
				System.err.println("CPF: " + c.getCpf());
				System.err.println("Nome: " + c.getNome());
				System.err.println("Data Nascimento: " + c.getDtNascimento());
				System.err.println("Telefone: " + c.getTelefone());
				System.err.println("Email: " + c.getEmail());
				System.err.println("\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
