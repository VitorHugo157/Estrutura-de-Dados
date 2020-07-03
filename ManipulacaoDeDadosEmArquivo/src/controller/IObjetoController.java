package controller;

import java.io.File;
import java.io.IOException;

public interface IObjetoController {
	public void create(File arquivo)throws IOException;
	public void read(File arquivo)throws IOException;
	public void update(File arquivo, Producao producao)throws IOException;
	public void delete(File arquivo)throws IOException;

}
