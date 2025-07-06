package br.ifsc.pousada.principal;

import java.util.Scanner;

import br.ifsc.pousada.daopersistir.AdicionarCliente;
import br.ifsc.pousada.daopersistir.AdicionarServicos;
import br.ifsc.pousada.daopersistir.FazerReserva;
import br.ifsc.pousada.daopersistir.ListarQuartos;
import br.ifsc.pousada.modelos.Cliente;

public class Main {
	static Scanner leitura = new Scanner(System.in);
	
	public static void main(String[] args) {
		StringBuffer buf = new StringBuffer(1000);

		
		buf.append("""
				[1] Cadastro de cliente.
				[2] Serviço adicional.
				[3] Listar cliente hospedado
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
				AdicionarServicos.cadastrarServicos();
				break;
			case 3:
				Cliente.carregarDadosClientes();
				break;
			case 4:
				ListarQuartos.listar();
				break;
			case 5:
				FazerReserva.cadastroReserva();
				break;
		}
	}
}
