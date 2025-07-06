package br.ifsc.pousada.daopersistir;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.modelos.Cliente;
import br.ifsc.pousada.modelos.Funcionario;
import br.ifsc.pousada.modelos.Quarto;
import br.ifsc.pousada.modelos.Reserva;
import br.ifsc.pousada.principal.Main;

public class FazerReserva {
	private static final Scanner leitura = new Scanner(System.in);
	
	public static void cadastroReserva() {
		while(true) {
			System.err.println("""
					[1] Listar Clientes
					[2] Cadastrar Reserva
					[3] Listar Quartos
					""");
			System.err.print("Opção: ");
			int opcao = leitura.nextInt();
			switch (opcao) {
				case 1:
					new Cliente().listar();
					break;
				case 2:
					System.err.print("Número do quarto: ");
					int numeroQuarto = leitura.nextInt();
					leitura.nextLine();
					System.err.print("Digite o cpf do Cliente: ");
					String cpf = leitura.nextLine();
					
					Quarto quarto = Quarto.carregarQuarto(numeroQuarto);
					Cliente cliente = Cliente.carregarCliente(cpf);
					Funcionario funcionario = Funcionario.carregarFuncionario();
					cadastrarReserva(cliente, funcionario, quarto);
					break;
				case 3:
					new Quarto().listar();
					break;
				default: {
					Main.menu();
					break;
				}
			}
		}
	}
	
	private static void cadastrarReserva(Cliente cliente, Funcionario funcionario, Quarto quarto) {
		ObjectMapper objeto = new ObjectMapper();
		String CAMINHO_ARQUIVO = "C:\\Users\\fabio\\git\\repository\\Pousada\\reserva.json";
		
		try {
			objeto.registerModule(new JavaTimeModule());
			File arquivo = new File(CAMINHO_ARQUIVO);
			Reserva reserva = new Reserva();
			List<Reserva> listReserva = null;
			
			//Deixa o quarto indisponível
			reserva.setCliente(cliente);
			if (!quarto.getStatus()) {
				System.err.println("Esse quarto está ocupado!");
				cadastroReserva();
			}
			quarto.setStatus(false);
			reserva.setQuarto(quarto);
			reserva.setFuncionario(funcionario);
			reserva.setDtEntrada(LocalDate.now());
			reserva.setValorTotal(quarto.getPreco());
			
			boolean reservaCadastrada = false;
			
			if (arquivo.exists()) {
				String json = LeitorJson.readJson(CAMINHO_ARQUIVO);
				listReserva = objeto.readValue(json, new TypeReference<List<Reserva>>() {});
			} else {
				listReserva = new ArrayList<>();
			}
			int tamanhoReservaOld = listReserva.size();
			listReserva.add(reserva);
			
			if (listReserva.size() > tamanhoReservaOld) {
				objeto.writerWithDefaultPrettyPrinter().writeValue(arquivo, listReserva);
				System.err.println("Reserva feita com sucesso!");
				reservaCadastrada = true;
			}
			
			if (!reservaCadastrada) {
				System.err.println("Falha ao fazer a reserva");
			}
			
			System.out.println("Cliente adicionado com sucesso!");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			Main.menu();
		}
	}
}
