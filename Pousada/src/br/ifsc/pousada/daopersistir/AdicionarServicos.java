package br.ifsc.pousada.daopersistir;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.modelos.Reserva;
import br.ifsc.pousada.modelos.Servico.TipoServico;
import br.ifsc.pousada.principal.Main;

public class AdicionarServicos {
	private static final Scanner leitura = new Scanner(System.in);
	
	public static void cadastrarServicos() {
		System.err.print("CPF: ");
		String cpf = leitura.nextLine();
		
		System.err.println("""
				Tipo de serviço:
				[1] Lavanderia
				[2] Café da manhã
				[3] Jantar
				[4] Sala de reunião
				[5] Serviço de quarto
				""");
		int opcao = leitura.nextInt();

		HashMap<Integer, TipoServico> opcoes = new HashMap<>();
		opcoes.put(1, TipoServico.LAVANDERIA);
		opcoes.put(2, TipoServico.CAFE_DA_MANHA);
		opcoes.put(3, TipoServico.JANTAR);
		opcoes.put(4, TipoServico.SALA_REUNIAO);
		opcoes.put(5, TipoServico.SERVICO_DE_QUARTO);

		HashMap<TipoServico, Double> mapTipoServico = new HashMap<>();
		mapTipoServico.put(TipoServico.LAVANDERIA, 50.44d);
		mapTipoServico.put(TipoServico.JANTAR, 60.30d);
		mapTipoServico.put(TipoServico.SALA_REUNIAO, 100.44d);
		mapTipoServico.put(TipoServico.SERVICO_DE_QUARTO, 20.34d);
		mapTipoServico.put(TipoServico.CAFE_DA_MANHA, 40.44d);

		String CAMINHO_ARQUIVO = "C:\\Users\\fabio\\git\\repository\\Pousada\\reserva.json";
		ObjectMapper objeto = new ObjectMapper();
		objeto.registerModule(new JavaTimeModule());

		try {
			File arquivo = new File(CAMINHO_ARQUIVO);
			List<Reserva> listaReserva = new ArrayList<>();

			if (arquivo.exists()) {
				String json = LeitorJson.readJson(CAMINHO_ARQUIVO);

				listaReserva = objeto.readValue(json, new TypeReference<List<Reserva>>() {});

				boolean reservaEncontrada = false;

				for (Reserva reserva : listaReserva) {
					if (reserva.getCliente().getCpf().equals(cpf)) {
						TipoServico tipo = opcoes.get(opcao);
						Double preco = mapTipoServico.get(tipo);
						
						reserva.adicionarServico(tipo, preco); // você deve ter esse método em Reserva
						
						System.out.println("Serviço adicionado à reserva de " + reserva.getCliente().getNome());
						reservaEncontrada = true;
						break;
					}
				}

				if (!reservaEncontrada) {
					System.out.println("Reserva com esse CPF não encontrada.");
				} else {
					// Salva a lista inteira de volta com a reserva modificada
					objeto.writerWithDefaultPrettyPrinter().writeValue(arquivo, listaReserva);
				}
			} else {
				System.err.println("Nenhuma reserva cadastrada.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Main.menu();
		}
	}
}






