package controller;

public class HashLinear {

	private static String hash[];
	private static int TAM_MAX;

	public HashLinear(int tam) {
		hash = new String[tam];
		TAM_MAX = tam;
		for (int i = 0; i < tam; i++) {
			hash[i] = null;
		}
	}

	private int funcaohash(String item) {
		int v = item.toLowerCase().codePointAt(0) - 97;
		return v;
	}

	public void insere(String item) {
		int pos = funcaohash(item);
		if (hash[pos] != null) {
			if (item.equalsIgnoreCase(hash[pos])) {
				System.out.println("\n->ATENCAO Esse item ja foi cadastrado");
				return;
			}
			while (pos < TAM_MAX) {
				if (pos == TAM_MAX - 1) {
					pos = -1;
				}
				pos++;
				if (hash[pos] == null) {
					break;
				}
			}
		}

		hash[pos] = item;
	}

	public void imprime() {
		for (int i = 0; i < TAM_MAX; i++) {
			if (hash[i] != null) {
				System.out.print("\nHash[" + i + "] = " + hash[i]);
			}
		}
	}
}