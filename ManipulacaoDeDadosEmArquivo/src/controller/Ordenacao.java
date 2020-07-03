package controller;

import java.util.LinkedList;
import java.util.List;

public class Ordenacao {

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

	private static List<Producao> listaAux(List<Producao> lista) {
		List<Producao> listaAux = new LinkedList<Producao>();

		for (int i = 0; i < lista.size(); i++) {
			Producao producao = new Producao();
			producao.setId(null);
			producao.setProduto(null);
			producao.setQuantidade(0);
			producao.setDataProducao(null);
			producao.setHoraProducao(null);
			producao.setCustoProducao(0);
			listaAux.add(producao);
		}

		return listaAux;
	}

	public static void iniciaMergeSort(List<Producao> lista) {
		iniciaMergeSort(lista, listaAux(lista), 0, lista.size() - 1);
	}

	private static void iniciaMergeSort(List<Producao> l1, List<Producao> l2, int init, int fim) {
		if (init < fim) {
			int meio = (init + fim) / 2;
			iniciaMergeSort(l1, l2, init, meio);
			iniciaMergeSort(l1, l2, meio + 1, fim);
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
			if (i > meio) {
				l1.set(k, l2.get(j++));
			} else if (j > fim) {
				l1.set(k, l2.get(i++));
			} else if (l2.get(i).getQuantidade() < l2.get(j).getQuantidade()) {
				l1.set(k, l2.get(i++));
			} else {
				l1.set(k, l2.get(j++));
			}
		}
	}

	public static void insertionSort(List<Producao> lista) {
		iniciaInsertionSort(lista, listaAux(lista));
	}

	private static void iniciaInsertionSort(List<Producao> lista, List<Producao> listaAux) {
		int j = 0;
		for (int i = 1; i < lista.size(); i++) {
			listaAux.set(i, lista.get(i));
			j = i - 1;

			while (j >= 0 && listaAux.get(i).getQuantidade() < lista.get(j).getQuantidade()) {
				lista.set(j + 1, lista.get(j));
				j--;
			}
			lista.set(j + 1, listaAux.get(i));
		}
	}

	private static int hashCodeLetra(int letra) {
		return letra - 97;
	}

	public static Producao[] tabelaHash(List<Producao> lista) {
		Producao[] hashProducao = new Producao[lista.size()];

		lista.forEach(itemProducao -> {
			int numChar = hashCodeLetra(itemProducao.getProduto().toLowerCase().codePointAt(0));
			if (hashProducao[numChar] == null) {
				hashProducao[numChar] = itemProducao;
			} else {
				boolean posicaoVazia = false;
				int proximo = numChar;
				int anterio = numChar;
				while (posicaoVazia) {
					if (hashProducao[proximo++] == null) {
						hashProducao[numChar] = itemProducao;
						posicaoVazia = true;
					} else if (hashProducao[anterio--] == null) {
						hashProducao[numChar] = itemProducao;
						posicaoVazia = true;
					}
				}
			}
		});

		return hashProducao;
	}

	public static List<Producao>[] tabelaHashListaEncadeada(List<Producao> lista) {
		@SuppressWarnings("unchecked")
		List<Producao>[] hashListaProducao = new LinkedList[26];

		for (Producao itemProducao : lista) {
			int numChar = hashCodeLetra((int)itemProducao.getProduto().toLowerCase().codePointAt(0));
			hashListaProducao[numChar].add(itemProducao);
		}
		return hashListaProducao;
	}
}
