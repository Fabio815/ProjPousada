package br.ifsc.pousada.principal;

import java.util.Scanner;

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
		
	}

}
