package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class ManipulaArquivo {

	public void createArquivo(File arquivo, List<Producao> lista) throws IOException {
		FileWriter fileWriter = new FileWriter(arquivo);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.write(cabecalho() + "\n");
		lista.forEach(itemProducao -> {
			printWriter.write(converteProducaoParaString(itemProducao) + "\n");
		});
		printWriter.flush();
		printWriter.close();
		fileWriter.close();
	}
	
	public String cabecalho() {
		return "ID;Produto;quantidade;dataProducao;horaProducao;custoProducao;";
	}
	
	public String converteProducaoParaString(Producao producao) {
		return producao.getId() + ";" + producao.getProduto() + ";"
				+ producao.getQuantidade() + ";" + producao.getDataProducao() + ";"
				+ producao.getHoraProducao() + ";" + producao.getCustoProducao() + ";";
	}
	
	public List <Producao> lerArquivo(File arquivo) throws IOException {
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		List<Producao> listaProducao = new LinkedList<>();
		String linha = "";
		buffer.readLine();
		while (linha != null) {
			linha = buffer.readLine();
			if (linha != null) {
				listaProducao.add(converteStringParaProducao(linha));
			}
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		return listaProducao;
	}
	
	public Producao converteStringParaProducao(String linha) {
		String atributo[] = linha.split(";");

		Producao producao = new Producao();
		producao.setId(BigInteger.valueOf(Integer.parseInt(atributo[0])));
		producao.setProduto(atributo[1]);
		producao.setQuantidade(Integer.parseInt(atributo[2]));
		producao.setDataProducao(LocalDate.parse(atributo[3]));
		producao.setHoraProducao(LocalTime.parse(atributo[4]));
		producao.setCustoProducao(Integer.parseInt(atributo[5]));

		return producao;
	}
	
	public void insereNoArquivo (File arquivo, String linha) throws IOException {
		FileWriter fileWriter = new FileWriter(arquivo, arquivo.exists());
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.write(linha + "\n");
		printWriter.flush();
		printWriter.close();
		fileWriter.close();
	}

}
