package view;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import controller.HashLinear;
import controller.HashLista;
import controller.ManipulaArquivo;
import controller.Ordenacao;
import controller.Producao;
import controller.ProducaoController;

public class Principal {
	public static void main(String[] args) throws IOException {
		Producao producao = new Producao();
		ProducaoController controller = new ProducaoController();
		ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

		String path = "C:\\Users\\f-win\\OneDrive\\Documentos\\Fatec\\3º Semestre\\Estrutura de Dados\\Manipulacao Arquivos";

		File arquivoEntrada = new File(path, "entrada.txt");
		File arquivoSaidaBubbleSort = new File(path, "saidaBubbleSort.txt");
		File arquivoSaidaMergeSort = new File(path, "saidaMergeSort.txt");
		File arquivoSaidaInsertionSort = new File(path, "saidaInsertionSort.txt");

		if (arquivoEntrada.exists())
			arquivoEntrada.delete();

		controller.create(arquivoEntrada);
		
		HashLista hashLista = new HashLista(26);

		for (int i = 0; i < 40; i++) {
			producao.setId(BigInteger.valueOf(i + 1));
			producao.setProduto(Character.toString(letra()) + Character.toString(letra()) + Character.toString(letra())
					+ Character.toString(letra()) + Character.toString(letra()) + Character.toString(letra()));
			producao.setQuantidade((int) (100 + Math.random() * 901));
			producao.setDataProducao(LocalDate.now());
			producao.setHoraProducao(LocalTime.now());
			producao.setCustoProducao((int) (1000 + Math.random() * 9001));
			controller.insert(arquivoEntrada, producao);
		}

		int opc = -1;
		while (opc != 7) {
			opc = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Inserir dados no arquivo de entrada"
					+ " \n2 - Ordenar dados \n3 - Deletar dados \n4 - Atualizar dados "
					+ "\n5 - Ler dados \n6 - Pesquisar dados \n7 - Tabela Hash e Tabela de Espalhamento\n0 - Finalizar"));

			switch (opc) {
			case 1:
				producao.setId(BigInteger.valueOf(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID"))));
				producao.setProduto(JOptionPane.showInputDialog(null, "Digite o nome do produto"));
				producao.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade")));
				producao.setDataProducao(LocalDate.now());
				producao.setHoraProducao(LocalTime.now());
				producao.setCustoProducao(
						Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o custo da produção")));
				controller.insert(arquivoEntrada, producao);
				JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso");
				break;
			case 2:
				List<Producao> listaProducao;
				listaProducao = manipulaArquivo.lerArquivo(arquivoEntrada);
				Ordenacao.bubbleSort(listaProducao);
				controller.create(arquivoSaidaBubbleSort);
				listaProducao.forEach(itemProducao -> {
					try {
						controller.insert(arquivoSaidaBubbleSort, itemProducao);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				listaProducao = manipulaArquivo.lerArquivo(arquivoEntrada);
				Ordenacao.iniciaMergeSort(listaProducao);
				controller.create(arquivoSaidaMergeSort);
				listaProducao.forEach(itemProducao -> {
					try {
						controller.insert(arquivoSaidaMergeSort, itemProducao);
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

				listaProducao = manipulaArquivo.lerArquivo(arquivoEntrada);
				Ordenacao.insertionSort(listaProducao);
				controller.create(arquivoSaidaInsertionSort);
				listaProducao.forEach(itemProducao -> {
					try {
						controller.insert(arquivoSaidaInsertionSort, itemProducao);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				JOptionPane.showMessageDialog(null, "Dados ordenados com sucesso.");
				break;

			case 3:
				controller.delete(arquivoEntrada);
				JOptionPane.showMessageDialog(null, "Objeto deletado com sucesso");
				break;
			case 4:
				producao.setId(BigInteger.valueOf(Integer
						.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do " + "produto a ser atualizado"))));
				producao.setProduto(JOptionPane.showInputDialog(null, "Digite o nome do produto"));
				producao.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a quantidade")));
				producao.setDataProducao(LocalDate.now());
				producao.setHoraProducao(LocalTime.now());
				producao.setCustoProducao(
						Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o custo da produção")));
				controller.update(arquivoEntrada, producao);
				JOptionPane.showMessageDialog(null, "Objeto atualizado com sucesso.");
				break;
			case 5:
				System.out.println("Dados do arquivo de entrada\n");
				controller.read(arquivoEntrada);

				System.out.println("\n\nDados do arquivo BubbleSort\n");
				controller.read(arquivoSaidaBubbleSort);

				System.out.println("\n\nDados do arquivo MergeSort\n");
				controller.read(arquivoSaidaMergeSort);

				System.out.println("\n\nDados do arquivo InsertionSort\n");
				controller.read(arquivoSaidaInsertionSort);
				break;

			case 6:
				int opc1 = -1;
				while (opc1 != 0) {
					opc1 = Integer.parseInt(JOptionPane
							.showInputDialog("Escolha uma das opções de pesquisa:\n\n1 - Arquivo de entrada\n"
									+ "2 - Arquivo de saida Bubble Sort\n3 - Arquivo de saida Insertion Sort\n"
									+ "4 - Arquivo de saida Merge Sort\n0 - Voltar"));
					BigInteger idPesquisado;
					List<Producao> listaDePesquisa;
					int encontrado = 0;
					switch (opc1) {
					case 1:
						idPesquisado = BigInteger.valueOf(Integer
								.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do produto desejado")));
						listaDePesquisa = manipulaArquivo.lerArquivo(arquivoEntrada);
						for (Producao itemProducao : listaDePesquisa) {
							if (itemProducao.getId().equals(idPesquisado)) {
								JOptionPane.showMessageDialog(null, itemProducao.toString());
								encontrado = 1;
								break;
							}
						}
						if (encontrado == 0) {
							System.out.println("Produto não encontrado");
						}
						break;
					case 2:
						idPesquisado = BigInteger.valueOf(Integer
								.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do produto desejado")));
						listaDePesquisa = manipulaArquivo.lerArquivo(arquivoSaidaBubbleSort);
						for (Producao itemProducao : listaDePesquisa) {
							if (itemProducao.getId().equals(idPesquisado)) {
								JOptionPane.showMessageDialog(null, itemProducao.toString());
								encontrado = 1;
								break;
							}
						}
						if (encontrado == 0) {
							System.out.println("Produto não encontrado");
						}
						break;
					case 3:
						idPesquisado = BigInteger.valueOf(Integer
								.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do produto desejado")));
						listaDePesquisa = manipulaArquivo.lerArquivo(arquivoSaidaInsertionSort);
						for (Producao itemProducao : listaDePesquisa) {
							if (itemProducao.getId().equals(idPesquisado)) {
								JOptionPane.showMessageDialog(null, itemProducao.toString());
								encontrado = 1;
								break;
							}
						}
						if (encontrado == 0) {
							System.out.println("Produto não encontrado");
						}
						break;
					case 4:
						idPesquisado = BigInteger.valueOf(Integer
								.parseInt(JOptionPane.showInputDialog(null, "Informe o ID do produto desejado")));
						listaDePesquisa = manipulaArquivo.lerArquivo(arquivoSaidaMergeSort);
						for (Producao itemProducao : listaDePesquisa) {
							if (itemProducao.getId().equals(idPesquisado)) {
								JOptionPane.showMessageDialog(null, itemProducao.toString());
								encontrado = 1;
								break;
							}
						}
						if (encontrado == 0) {
							System.out.println("Produto não encontrado");
						}
						break;
					case 0:
						opc = -1;
						break;
					default:
						JOptionPane.showMessageDialog(null, "Opção inválida.");
					}
				}
				break;
			case 7:
				int opcHash = -1;
				while(opcHash != 0) {
					opcHash = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Tabela Hash Linear \n2 - Tabela Hash Lista \n\n0 - Voltar"));
					switch(opcHash) {
					case 1:
						System.out.println("\n\nTabela Hash");
						listaProducao = manipulaArquivo.lerArquivo(arquivoSaidaMergeSort);
						HashLinear hashLinear = new HashLinear(listaProducao.size());
						for(Producao item : listaProducao) {
							hashLinear.insere(item.getProduto());
						}
						hashLinear.imprime();
						break;
					case 2:
						System.out.println("\n\nTabela de Espalhamento");
						listaProducao = manipulaArquivo.lerArquivo(arquivoSaidaMergeSort);
						for(Producao item : listaProducao) {
							hashLista.insere(item.getProduto());
						}
						hashLista.imprime();
						break;
					case 0:
						break;
						default:
							JOptionPane.showMessageDialog(null, "Opção inválida!");
					}
				}
				break;
			case 0:
				JOptionPane.showMessageDialog(null, "Fim.");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");
			}
		}
	}

	private static char letra() {
		return ((char) (97 + Math.random() * 26));
	}
}
