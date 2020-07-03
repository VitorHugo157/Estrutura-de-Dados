package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ProducaoController implements IObjetoController {

	private ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

	@Override
	public void create(File arquivo) throws IOException {
		List<Producao> listaVazia = new LinkedList<Producao>();
		manipulaArquivo.createArquivo(arquivo, listaVazia);
	}

	public void insert(File arquivo, Producao producao) throws IOException {
		String linha = manipulaArquivo.converteProducaoParaString(producao);
		manipulaArquivo.insereNoArquivo(arquivo, linha);
	}

	@Override
	public void update(File arquivo, Producao producao) throws IOException {

		List<Producao> listaProducao = manipulaArquivo.lerArquivo(arquivo);

		for (Producao itemProducao : listaProducao) {
			if (itemProducao.getId().equals(producao.getId())) {
				listaProducao.set((itemProducao.getId().intValue() - 1), producao);
				break;
			}
		}
		manipulaArquivo.createArquivo(arquivo, listaProducao);
	}

	@Override
	public void read(File arquivo) throws IOException {

		List<Producao> listaProducao = manipulaArquivo.lerArquivo(arquivo);
		
		System.out.println(manipulaArquivo.cabecalho().replace(";", " | "));

		listaProducao.forEach(itemProducao -> {
			System.out.println(itemProducao.toString());
		});
	}

	@Override
	public void delete(File arquivo) throws IOException {
		List<Producao> listaProducao = manipulaArquivo.lerArquivo(arquivo);

		listaProducao.remove(0);

		manipulaArquivo.createArquivo(arquivo, listaProducao);

	}
}
