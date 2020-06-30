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

public class ProducaoController implements IObjetoController {

	@Override
	public void create(String path, String arquivo) throws IOException {
		File dir = new File(path);
		File arq = new File(path, arquivo);
		if (dir.exists() && dir.isDirectory() && !arq.exists()) {
			String linha = "ID;Produto;quantidade;dataProducao;horaProducao;custoProducao;\n";
			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.write(linha);
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório invalido ou arquivo já existe");
		}
	}

	public void insert(Producao producao, String path, String arquivo) throws IOException {
		File dir = new File(path);
		File arq = new File(path, arquivo);
		if (dir.exists() && dir.isDirectory() && arq.exists()) {
			String conteudoProducao = producao.getId() + ";" + producao.getProduto() + ";" + producao.getQuantidade()
					+ ";" + producao.getDataProducao() + ";" + producao.getHoraProducao() + ";"
					+ producao.getCustoProducao() + ";";
			FileWriter fileWriter = new FileWriter(arq, arq.exists());
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.write(conteudoProducao + "\n");
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório invalido ou arquivo inexistente");
		}
	}

	@Override
	public void update(Producao producao, String path, String arquivo) throws IOException {
		File dir = new File(path);
		File arq = new File(path, arquivo);
		if (dir.exists() && dir.isDirectory() && arq.exists()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			List<Producao> listaDeObjeto = new LinkedList<Producao>();
			String linha = "";
			String Cabecalho = buffer.readLine();
			while (linha != null) {
				linha = buffer.readLine();
				if (linha != null) {
					listaDeObjeto.add(criaProducao(linha));
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();

			for (Producao itemProducao : listaDeObjeto) {
				if (itemProducao.getId().equals(producao.getId())) {
					listaDeObjeto.set((itemProducao.getId().intValue() - 1), producao);
					break;
				}
			}

			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.write(Cabecalho + "\n");
			for (Producao itemProducao : listaDeObjeto) {
				printWriter.write(itemProducao.getId() + ";" + itemProducao.getProduto() + ";"
						+ itemProducao.getQuantidade() + ";" + itemProducao.getDataProducao() + ";"
						+ itemProducao.getHoraProducao() + ";" + itemProducao.getCustoProducao() + ";");
			}
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório invalido ou arquivo inexistente");
		}
	}

	@Override
	public void read(String path, String arquivo) throws IOException {
		File dir = new File(path);
		File arq = new File(path, arquivo);
		if (dir.exists() && dir.isDirectory() && arq.exists()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			List<String> listaDeObjeto = new LinkedList<String>();
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha != null) {
					listaDeObjeto.add(linha);
					linha = buffer.readLine();
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			listaDeObjeto.forEach(itemProducao -> {
				System.out.println(itemProducao);
			});
		} else {
			throw new IOException("Diretório invalido ou arquivo inexistente");
		}
	}

	@Override
	public void delete(String path, String arquivo) throws IOException {
		File dir = new File(path);
		File arq = new File(path, arquivo);
		if (dir.exists() && dir.isDirectory() && arq.exists()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			List<String> listaDeObjeto = new LinkedList<String>();
			String linha = "";
			while (linha != null) {
				if (linha != null) {
					linha = buffer.readLine();
					listaDeObjeto.add(linha);
				}
			}
			buffer.close();
			leitor.close();
			fluxo.close();

			listaDeObjeto.remove(1);

			FileWriter fileWriter = new FileWriter(arq);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (String itemProducao : listaDeObjeto) {
				printWriter.write(itemProducao + "\n");
			}
			printWriter.flush();
			printWriter.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório invalido ou arquivo inexistente");
		}
	}

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
}