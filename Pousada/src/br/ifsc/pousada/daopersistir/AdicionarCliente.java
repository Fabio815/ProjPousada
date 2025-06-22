package br.ifsc.pousada.daopersistir;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.modelos.Cliente;

public class AdicionarCliente {
	private static final Scanner leitura = new Scanner(System.in);

	public static void adicionarCliente() {

		System.out.print("Nome: ");
		String nome = leitura.next();

		System.out.print("Data de Nascimento (dd/MM/yyyy): ");
		String dtNascimento = leitura.next();
		DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dtNascimento, formatarData);

		System.out.print("CPF: ");
		String cpf = leitura.next();

		System.out.print("Email: ");
		String email = leitura.next();

		System.out.print("Telefone: ");
		String telefone = leitura.next();
		
		Cliente cliente = new Cliente(nome, data, telefone, cpf, email);
		adicionar(cliente);
	}

	public static void adicionar(Cliente cliente) {
		String CAMINHO_ARQUIVO = "C:\\Users\\fabio\\git\\repository\\Pousada\\clientes.json";
		ObjectMapper objeto = new ObjectMapper();
		try {
			objeto.registerModule(new JavaTimeModule());

			File arquivo = new File(CAMINHO_ARQUIVO);
			List<Cliente> clientes = null;

			if (arquivo.exists()) {
				CollectionType tipoLista = objeto.getTypeFactory()
					.constructCollectionType(List.class, Cliente.class);
				
				clientes = objeto.readValue(arquivo, tipoLista);
			} else {
				clientes = new ArrayList<>();
			}
			
			clientes.add(cliente);
			//final var json = objeto.writerWithDefaultPrettyPrinter().writeValueAsString(clientes); //transformanfo objeto em json
			objeto.writerWithDefaultPrettyPrinter().writeValue(arquivo, clientes);

			System.out.println("Cliente adicionado com sucesso!");

		} catch (Exception e) {
			System.err.println("Erro ao salvar cliente: " + e.getMessage());
		}
	}
}
