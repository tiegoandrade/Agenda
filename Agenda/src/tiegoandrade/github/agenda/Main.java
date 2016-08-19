package tiegoandrade.github.agenda;

import java.io.IOException;

/**
 * Classe que executa a aplicação.
 * 
 * @version 1.0 18-08-2016
 * @author Tiego
 *
 */
public class Main {
	
	/**
	 * Método que executa a lógica de funcionamento da agenda
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Aplicacao aplicacao = new Aplicacao();
		aplicacao.iniciar();
	}
}
