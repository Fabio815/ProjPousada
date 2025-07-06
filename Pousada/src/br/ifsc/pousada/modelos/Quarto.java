package br.ifsc.pousada.modelos;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.daopersistir.LeitorJson;

public class Quarto implements Listar {
	public enum Tipo {
		SIMPLES, DUPLO
	};
	private Boolean status; //TODO true = disponivel / false = indisponivel
	private Tipo tipo;
	private Integer numero;
	private Double preco;
	
	public Quarto() {
		
	}
	
	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public static Quarto carregarQuarto(Integer numero) {
		ObjectMapper objeto = new ObjectMapper();
		Quarto quarto = null;
		try {
			if (numero != null) {
				String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\Quartos.json");
				objeto.registerModule(new JavaTimeModule());
				TypeReference<List<Quarto>> tipoClasse = new TypeReference<List<Quarto>>() {};
				List<Quarto> quartos = objeto.readValue(json, tipoClasse);
				for (Quarto q : quartos) {
					if (q.getNumero().equals(numero)) {
						quarto = q;
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return quarto;
	}
	
	public void listar() {
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
