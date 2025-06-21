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
	private static final String CAMINHO_ARQUIVO = "C:\\Users\\fabio\\git\\repository\\Pousada\\clientes.json";
	private static final ObjectMapper mapper = new ObjectMapper();

	public static void adicionarCliente() {
		Scanner leitura = new Scanner(System.in);
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
		try {
			mapper.registerModule(new JavaTimeModule());

			// 1. Carrega clientes existentes
			File arquivo = new File(CAMINHO_ARQUIVO);
			List<Cliente> clientes;

			if (arquivo.exists()) {
				CollectionType tipoLista = mapper.getTypeFactory()
					.constructCollectionType(List.class, Cliente.class);
				clientes = mapper.readValue(arquivo, tipoLista);
			} else {
				clientes = new ArrayList<>();
			}

			// 2. Adiciona novo cliente
			clientes.add(cliente);

			// 3. Salva a lista atualizada
			mapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, clientes);

			System.out.println("Cliente adicionado com sucesso!");

		} catch (Exception e) {
			System.err.println("Erro ao salvar cliente: " + e.getMessage());
		}
	}
}
