package tiegoandrade.github.agenda;

/**
 * Representa uma exce��o gen�rica da aplica��o de agenda.
 * 
 * @author Tiego
 *
 */
public class AgendaException extends Exception {

	/**
	 * Construtor
	 * 
	 * @see Exception$Exception(String)
	 * 
	 * @param message
	 *            Mensagem informada pelo desenvolvedor caso haja alguma
	 *            erro.
	 */
	public AgendaException(String message) {
		super(message);
	}

}
