package tiegoandrade.github.agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que representa os contatos de uma agenda.
 * 
 * @author Tiego
 *
 */
public class Contato {

	/** Nome do contato. */
	private String nome;

	/** Telefone do contato. */
	private String telefone;

	/**
	 * Construtor
	 * 
	 * @param nome
	 *            Nome do contato
	 * @param telefone
	 *            Telefone do contato
	 */
	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	/** Recupera o nome do contato. */
	public String getNome() {
		return nome;
	}

	/** Insere um novo nome para um contato. */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Recupera o telefone do contato. */
	public String getTelefone() {
		return telefone;
	}

	/** Insere um novo telefone para um contato. */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Valida os dados de um determinado contato.
	 * 
	 * @throws AgendaException
	 *             Lançada se ocorrer alguma falha na validação
	 */
	public void validarDados() throws AgendaException {
		validarNome();
		validarTelefone();
	}

	/**
	 * Valida o nome de um contato.
	 * 
	 * @throws AgendaException
	 *             Lançada se o nome de um contato for vazio.
	 */
	private void validarNome() throws AgendaException {
		if (nome == null || nome.trim().length() == 0) {
			throw new AgendaException("O nome do contato não pode ser vazio");
		}
	}

	/**
	 * Valida o telefone de um contato. O formato do telefone deve ser o
	 * seguinte: 'XXXX-XXXX'.
	 * 
	 * @throws AgendaException
	 *             Lançada se expressão regular não combinar com o telefone
	 *             informado
	 */
	private void validarTelefone() throws AgendaException {
		String regex = "\\d\\d\\d\\d-\\d\\d\\d\\d";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(telefone);

		if (!m.matches()) {
			throw new AgendaException("O telefone " + telefone
					+ " não é válido");
		}
	}
	
	/**
	 * Mostra as informações do contato.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return nome + " -> " + telefone;
	}
}
