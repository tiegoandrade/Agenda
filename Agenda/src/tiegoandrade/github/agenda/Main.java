package tiegoandrade.github.agenda;

import java.io.IOException;

/**
 * Classe que executa a aplica��o.
 * 
 * @version 1.0 18-08-2016
 * @author Tiego
 *
 */
public class Main {
	
	/**
	 * M�todo que executa a l�gica de funcionamento da agenda
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Aplicacao aplicacao = new Aplicacao();
		aplicacao.iniciar();
	}
}
