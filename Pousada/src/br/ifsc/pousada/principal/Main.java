package br.ifsc.pousada.principal;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ifsc.pousada.daopersistir.AdicionarCliente;
import br.ifsc.pousada.modelos.Cliente;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

	public static void main(String[] args) {
		StringBuffer buf = new StringBuffer(1000);
		Scanner leitura = new Scanner(System.in);
		
		buf.append("""
				[1] Cadastro de cliente.
				[2] Serviço adicional.
				[3] Listar clientes hospedado
				[4] Listar quartos disponiveis
				[5] Fazer reserva
				""").append("\n");
		buf.append("Opção: ");
		System.err.print(buf.toString());
		int opcao = leitura.nextInt();
		switch(opcao) {
			case 1:
				AdicionarCliente.adicionarCliente();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
		}
		/*try {
            String json = "{\"nome\":\"Maria da Silva\",\"dtNascimento\":\"1990-05-15\",\"telefone\":\"(48) 99999-9999\",\"cpf\":\"123.456.789-00\",\"email\":\"maria.silva@email.com\"}";
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Suporte para LocalDate
            Cliente cliente = objectMapper.readValue(json, Cliente.class);
            System.out.println(cliente.getDtNascimento());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erro ao desserializar o JSON: " + e.getMessage());
        }*/
		
	}
}
