package br.ifsc.pousada.daopersistir;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ifsc.pousada.modelos.Quarto;

public class ListarQuartos {

	public static void listar() {
		ObjectMapper objeto = new ObjectMapper();
		try {
			String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\Quartos.json");
			List<Quarto> quarto = objeto.readValue(json, new TypeReference<List<Quarto>>() {});
			for (Quarto q : quarto) {
				String status = q.getStatus() ? "Disponível" : "Indisponível";
				System.err.println("Número: " + q.getNumero());
				System.err.println("Tipo: " + q.getTipo());
				System.err.println("Status: " + status);
				System.err.println("Preço: " + q.getPreco());
				System.err.println("----------------------------------------------------");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
