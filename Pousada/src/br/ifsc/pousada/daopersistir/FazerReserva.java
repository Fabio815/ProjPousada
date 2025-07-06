package br.ifsc.pousada.daopersistir;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.modelos.Cliente;
import br.ifsc.pousada.modelos.Funcionario;
import br.ifsc.pousada.modelos.Quarto;
import br.ifsc.pousada.modelos.Reserva;

public class FazerReserva {
	private static final Scanner leitura = new Scanner(System.in);
	
	public static void cadastroReserva() {
		System.err.println("""
				[1] Listar Clientes
				[2] Cadastrar Reserva
				[3] Listar Quartos
				""");
		System.err.print("Opção: ");
		int opcao = leitura.nextInt();
		switch (opcao) {
			case 1:
				Cliente.carregarDadosClientes();
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
				ListarQuartos.listar();
				break;
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
			
			reserva.setCliente(cliente);
			reserva.setQuarto(quarto);
			reserva.setFuncionario(funcionario);
			reserva.setDtEntrada(LocalDate.now());
			reserva.setValorTotal(quarto.getPreco());
			
			if (arquivo.exists()) {
				CollectionType tipoLista = objeto.getTypeFactory()
					.constructCollectionType(List.class, Reserva.class);
				
				listReserva = objeto.readValue(arquivo, tipoLista);
			} else {
				listReserva = new ArrayList<>();
			}
			
			listReserva.add(reserva);
			
			//final var json = objeto.writerWithDefaultPrettyPrinter().writeValueAsString(clientes); //transformanfo objeto em json
			objeto.writerWithDefaultPrettyPrinter().writeValue(arquivo, listReserva);

			System.out.println("Cliente adicionado com sucesso!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
