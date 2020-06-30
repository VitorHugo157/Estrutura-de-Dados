package controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;

public class Producao {
	private BigInteger id;
	private String produto;
	private int quantidade;
	private LocalDate dataProducao;
	private LocalTime horaProducao;
	private long custoProducao;
	
	public Producao() {}

	public Producao(BigInteger id, String produto, int quantidade, LocalDate dataProducao, LocalTime horaProducao, long custoProducao) {
		this.setId(id);
		this.produto = produto;
		this.quantidade = quantidade;
		this.dataProducao = dataProducao;
		this.horaProducao = horaProducao;
		this.custoProducao = custoProducao;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataProducao() {
		return dataProducao;
	}

	public void setDataProducao(LocalDate dataProducao) {
		this.dataProducao = dataProducao;
	}

	public LocalTime getHoraProducao() {
		return horaProducao;
	}

	public void setHoraProducao(LocalTime horaProducao) {
		this.horaProducao = horaProducao;
	}

	public long getCustoProducao() {
		return custoProducao;
	}

	public void setCustoProducao(long custoProducao) {
		this.custoProducao = custoProducao;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}
	
}