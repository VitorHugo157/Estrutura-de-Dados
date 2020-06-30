package view;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.Ordenacao;
import controller.Producao;
import controller.ProducaoController;

public class Principal {
	public static void main(String[] args) throws IOException {
		Producao producao = new Producao();
		ProducaoController controller = new ProducaoController();
		Ordenacao ordenacao = new Ordenacao();
		
		String path = "C:\\Users\\f-win\\OneDrive\\Documentos\\Fatec\\3º Semestre\\Estrutura de Dados\\Manipulacao Arquivos";
		String nomeArquivoEntrada = "entrada.txt";
		
		File arquivoEntrada = new File(path, nomeArquivoEntrada);
		
		if(arquivoEntrada.exists()) {
			arquivoEntrada.delete();
		}
		
		controller.create(path, nomeArquivoEntrada);
		
		for(int i = 0; i < 5; i++) {
			producao.setId(BigInteger.valueOf(i + 1));
			producao.setProduto("Produto " + (i + 1));
			producao.setQuantidade((int)(100 + Math.random() * 901));
			producao.setDataProducao(LocalDate.now());
			producao.setHoraProducao(LocalTime.now());
			producao.setCustoProducao((int)(1000 + Math.random() * 9001));
			controller.insert(producao, path, nomeArquivoEntrada);
		}
		System.out.println("Arquivo de entrada\n");
		controller.read(path, nomeArquivoEntrada);
		
		List<Producao> l2 = listaVazia(producao);
		
		int opc = -1;
		while(opc != 0) {
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - BubbleSort \n2 - InsertionSort \n0 - Sair"));
			switch(opc) {
			case 1:
				System.out.println("\nOrdenado com bubbleSort\n");
				List<Producao> lista1 = ordenacao.obtemListaDoArquivo(arquivoEntrada);
				Ordenacao.bubbleSort(lista1);
				ordenacao.createArquivoSaida(path, "bubbleSort.txt", lista1);
				controller.read(path, "bubbleSort.txt");
				break;
			case 2:
				System.out.println("\n\nOrdenado com InsertionSort\n");
				List<Producao> lista2 = ordenacao.obtemListaDoArquivo(arquivoEntrada);
				Ordenacao.insertionSort(lista2, l2);
				ordenacao.createArquivoSaida(path, "InsertionSort.txt", lista2);
				controller.read(path, "InsertionSort.txt");
				break;
			case 0:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida");
			}
		}
	}
	
	public static List<Producao> listaVazia(Producao producao){
		List<Producao> listaVazia = new LinkedList<>();
		for(int i = 0; i < 5; i++) {
			producao.setId(null);
			producao.setProduto(null);
			producao.setQuantidade(0);
			producao.setDataProducao(null);
			producao.setHoraProducao(null);
			producao.setCustoProducao(0);
			listaVazia.add(producao);
		}
		return listaVazia;
	}
	
}