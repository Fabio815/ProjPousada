package br.ifsc.pousada.modelos;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.daopersistir.LeitorJson;

public class Quarto {
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
}
