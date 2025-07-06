package br.ifsc.pousada.modelos;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.ifsc.pousada.daopersistir.LeitorJson;
import br.ifsc.pousada.modelos.Servico.TipoServico;

public class Reserva {
	private LocalDate dtEntrada;
	private LocalDate dtSaida;
	private Double valorTotal;
	private Cliente cliente;
	private Funcionario funcionario;
	private Quarto quarto;
	private List<Servico> servicos;
	
	public Reserva() {
		
	}
	
	public LocalDate getDtEntrada() {
		return dtEntrada;
	}
	public void setDtEntrada(LocalDate dtEntrada) {
		this.dtEntrada = dtEntrada;
	}
	
	public LocalDate getDtSaida() {
		return dtSaida;
	}
	
	public void setDtSaida(LocalDate dtSaida) {
		this.dtSaida = dtSaida;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public Quarto getQuarto() {
		return quarto;
	}
	
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}
	
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	public void adicionarServico(TipoServico tipo, double valor) {
		if (servicos == null) {
			servicos = new ArrayList<>();
		}
		Servico novo = new Servico();
		novo.setTipoServico(tipo);
		novo.setValor(valor);
		servicos.add(novo);
		valorTotal += valor;
	}
	
	public static Reserva carregarReserva(String cpf) {
		Reserva reserva = null;
		ObjectMapper objeto = new ObjectMapper();
		if (!cpf.isEmpty()) {
			try {
				String CAMINHO = "C:\\Users\\fabio\\git\\repository\\Pousada\\reserva.json";
				File arquivo = new File(CAMINHO);
				
				if(arquivo.exists()) {
					String json = LeitorJson.readJson(CAMINHO);
					List<Reserva> listaReservas = objeto.readValue(json, new TypeReference<List<Reserva>>() {});
					for (Reserva r : listaReservas) {
						if(r.getFuncionario().getCpf().equals(cpf)) {
							reserva = r;
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return reserva;
	}

	public static void listarReservas() {
		ObjectMapper objeto = new ObjectMapper();
		objeto.registerModule(new JavaTimeModule());
		try {
			String json = LeitorJson.readJson("C:\\Users\\fabio\\git\\repository\\Pousada\\reserva.json");
			List<Reserva> reservas = objeto.readValue(json, new TypeReference<List<Reserva>>() {});
			StringBuffer r = new StringBuffer();
			for (Reserva re : reservas) {
				r.append("Nome: " + re.getCliente().getNome()).append("\n");
				r.append("CPF: " + re.getCliente().getCpf()).append("\n");
				r.append("Número do quarto: " + re.getQuarto().getNumero()).append("\n");
				r.append("Valor Total: " + re.getValorTotal()).append("\n");
				r.append("Data de entrada: " + re.getDtEntrada()).append("\n");
				System.err.println(r.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
