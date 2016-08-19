package tiegoandrade.github.agenda;

/**
 * Representa uma exceção genérica da aplicação de agenda.
 * 
 * @version 1.0 18-08-2016
 * @author Tiego
 *
 */
@SuppressWarnings("serial")
public class AgendaException extends Exception {

	/**
	 * Construtor
	 * 
	 * @see Exception$Exception(String)
	 * 
	 * @param message Mensagem lançada pelo aplicativo caso haja algum erro.
	 */
	public AgendaException(String message) {
		super(message);
	}

}
