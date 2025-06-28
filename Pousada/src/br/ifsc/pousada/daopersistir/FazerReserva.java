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
			case 2:
				cadastrarReserva();
				break;
		}
	}
	
	private static void cadastrarReserva() {
		Cliente.carregarCliente("4843929392");
		
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
