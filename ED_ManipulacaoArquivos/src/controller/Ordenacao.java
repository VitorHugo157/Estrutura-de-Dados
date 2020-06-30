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

public class Ordenacao {

	private Producao criaProducao(String linha) {
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

	public List<Producao> obtemListaDoArquivo(File arquivo) throws IOException {
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		List<Producao> listaProducao = new LinkedList<>();
		String linha = "";
		buffer.readLine();
		while (linha != null) {
			linha = buffer.readLine();
			if (linha != null) {
				listaProducao.add(criaProducao(linha));
			}
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		return listaProducao;
	}

	public static void bubbleSort(List<Producao> lista) {

		for (int ultimo = lista.size() - 1; ultimo > 0; ultimo--) {
			for (int i = 0; i < ultimo; i++) {
				if (lista.get(i).getQuantidade() > lista.get(i + 1).getQuantidade()) {
					Producao producaoAux = lista.get(i);
					lista.set(i, lista.get(i + 1));
					lista.set(i + 1, producaoAux);
				}
			}
		}
	}
	
	public static void insertionSort(List<Producao> lista, List<Producao> l2) {
		int j = 0;
		for(int i = 1; i < lista.size(); i++) {
			l2.set(i, lista.get(i));
			j = i - 1;
			
			while(j >= 0 && l2.get(i).getQuantidade() < lista.get(j).getQuantidade()) {
				lista.set(j + 1, lista.get(j));
				j--;
			}
			lista.set(j + 1, l2.get(i));
		}
	}

	public static void mergeSort(List<Producao> l1, List<Producao> l2, int init, int fim) {
		if (init < fim) {
			int meio = (init + fim) / 2;
			mergeSort(l1, l2, init, meio);
			mergeSort(l1, l2, meio + 1, fim);
			intercalar(l1, l2, init, meio, fim);
		}
	}
	
	

	private static void intercalar(List<Producao> l1, List<Producao> l2, int init, int meio, int fim) {
		for (int k = init; k <= fim; k++) {
			l2.set(k, l1.get(k));
		}

		int i = init;
		int j = meio + 1;

		for (int k = init; k <= fim; k++) {
			if (i > meio)
				l1.set(k, l2.get(j++));
			else if (j > fim)
				l1.set(k, l2.get(i++));
			else if (l2.get(i).getQuantidade()< l2.get(j).getQuantidade())
				l1.set(k, l2.get(i++));
			else
				l1.set(k, l2.get(j++));
		}
	}

	public void createArquivoSaida(String path, String arquivo, List<Producao> lista) throws IOException {
		File arq = new File(path, arquivo);
		String linha = "ID;Produto;quantidade;dataProducao;horaProducao;custoProducao;\n";
		FileWriter fileWriter = new FileWriter(arq);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.write(linha);
		lista.forEach(itemProducao -> {
			printWriter.write(itemProducao.getId() + ";" + itemProducao.getProduto() + ";"
					+ itemProducao.getQuantidade() + ";" + itemProducao.getDataProducao() + ";"
					+ itemProducao.getHoraProducao() + ";" + itemProducao.getCustoProducao() + ";\n");
		});
		printWriter.flush();
		printWriter.close();
		fileWriter.close();
	}
	
}