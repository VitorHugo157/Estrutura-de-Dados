package controller;

import java.io.IOException;

public interface IObjetoController {
	public void create(String path, String arquivo)throws IOException;
	public void read(String path, String arquivo)throws IOException;
	public void update(Producao producao, String path, String arquivo)throws IOException;
	public void delete(String path, String arquivo)throws IOException;

}