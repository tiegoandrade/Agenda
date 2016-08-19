package tiegoandrade.github.agenda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Utilizada para ler os dados do console.
 * 
 * @version 1.0 18-08-2016
 * @author Tiego
 * 
 */
public class Console {
	
	/**
	 * L� uma string digitada pelo usu�rio no console.
	 * 
	 * @return Uma linha do que foi digitado pelo usu�rio.
	 */
	public static String readString() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o dado do teclado");
		}
	}

	/**
	 * L� um caractere digitado pelo usu�rio no console.
	 * 
	 * @return O primeiro caractere de uma String digitada pelo usu�rio.
	 */
	public static char readChar() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			return reader.readLine().charAt(0);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler o dado do teclado");
		}
	}

	/**
	 * L� um inteiro digitado pelo usu�rio no console.
	 * 
	 * @return Uma convers�o de um String para um objeto da classe Integer.
	 */
	public static int readInt() {
		String str = readString();

		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			throw new RuntimeException(str + " n�o � um inteiro v�lido");
		}
	}
}
