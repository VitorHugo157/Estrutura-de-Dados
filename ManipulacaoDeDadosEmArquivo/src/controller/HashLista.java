package controller;

import java.util.LinkedList;
import java.util.List;

public class HashLista {
	
	private List<String>[] hashProducao;
	private int tam_max;
	
	public HashLista(int tam) {
		hashProducao = new List[tam];
		this.tam_max = tam;
		for(int i = 0; i < tam; i++) {
			hashProducao[i] = null;
		}
	}
	
	private int funcaoHash(String item) {
		int chave = item.toLowerCase().codePointAt(0) - 97;
		return chave;
	}
	
	public void insere(String item) {
		int pos = funcaoHash(item);
		if(hashProducao[pos] != null) {
			hashProducao[pos].add(item);
//			if(hashProducao[pos].contains(item)) {
//				JOptionPane.showMessageDialog(null, "O item " + item + " j� foi cadastrado");
//				return;
//			}
		}else {
			hashProducao[pos] = new LinkedList();
			hashProducao[pos].add(item);
		}
	}
	
	public int busca(String item) {
		for(int i = 0; i < tam_max; i++) {
			if(hashProducao[i] != null) {
				if(hashProducao[i].contains(item)) {
					return i; // true
				}
			}
		}
		return -1; //false
	}
	
	public void imprime() {
		for (int i = 0; i < tam_max; i++) { 
			System.out.print("\n\n HASH[" + i + "] -> ");
			if ( hashProducao[i]!=null ) {
				System.out.print(hashProducao[i]);
			}else {
				System.out.print("null");
			}
		}
	}
}
