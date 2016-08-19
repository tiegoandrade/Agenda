package tiegoandrade.github.agenda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe que representa a agenda de contatos.
 * 
 * @version 1.0 18-08-2016
 * @author Tiego
 *
 */
public class Agenda {

	/**
	 * Map que armazena como chave o nome do contato e como valor o objeto de
	 * contato.
	 */
	private Map<String, Contato> contatosMap = new TreeMap<String, Contato>();

	/**
	 * Map que organiza os contatos por letras do alfabeto. A chave � uma letra
	 * e o valor � uma lista de contatos, cujo nome come�a com a letra
	 * especificada na chave do map.
	 */
	private Map<Character, List<Contato>> contatosPorLetrasMap = new TreeMap<Character, List<Contato>>();

	/** Gerenciador do arquivo que possui os registros da agenda. */
	private ArquivoAgenda arquivo = new ArquivoAgenda();

	/**
	 * Construtor
	 * 
	 * @throws IOException Lan�ada se ocorrer alguma exce��o de entrada e sa�da.
	 */
	public Agenda() throws IOException {
		
		// L� os contatos que est�o no arquivo com os registros da agenda.
		List<Contato> contatos = arquivo.ler();

		// Insere cada contato lido do arquivo na agenda.
		for (Contato contato : contatos) {
			try {
				inserir(contato);
			} catch (AgendaException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Insere um novo contato na agenda.
	 * 
	 * @param contato Nome e telefone do contato que ser� inserido na agenda.
	 * @throws AgendaException Lan�ada se o contato j� existir na agenda.
	 * @throws IOException Lan�ada se ocorrer alguma exce��o de entrada e sa�da.
	 */
	public void inserir(Contato contato) throws AgendaException, IOException {

		// Valida os dados do contato.
		contato.validarDados();

		// Verifica se o nome do contato j� existe.
		String nome = contato.getNome();
		if (contatosMap.containsKey(nome)) {
			throw new AgendaException("O contato " + nome + " j� existe");
		}

		// Caso o contato n�o exista, ele � inserido na lista de contatos.
		contatosMap.put(nome, contato);

		/*
		 * Armazena na vari�vel letraInicial a primeira letra do nome do
		 * contato, colocando-o em letra mai�scula.
		 */
		char letraInicial = Character.toUpperCase(nome.charAt(0));

		/*
		 * Busca e armazena em uma lista os nomes que come�am com uma
		 * determinada letra.
		 */
		List<Contato> contatosLetra = contatosPorLetrasMap.get(letraInicial);

		/*
		 * Se n�o houver, na lista, contatos que come�am com a mesma letra,
		 * instacia-se o "contatosLetra" como uma lista vazia e a adiciona em um
		 * MAP, junto com a letra inicial do nome do contato.
		 */
		if (contatosLetra == null) {
			contatosLetra = new ArrayList<Contato>();
			contatosPorLetrasMap.put(letraInicial, contatosLetra);
		}

		// Adiciona o contato na lista de contatos com uma mesma letra inicial.
		contatosLetra.add(contato);

		// Grava no arquivo uma lista com os valores dos contatos.
		arquivo.gravar(contatosMap.values());
	}

	/**
	 * Exclui um contato da agenda
	 * 
	 * @param nome Nome do contato que ser� exclu�do.
	 * @throws AgendaException Lan�ada se o contato n�o existir.
	 * @throws IOException Lan�ada se ocorrer alguma exce��o de entrada e sa�da.
	 */
	public void excluir(String nome) throws AgendaException, IOException {

		// Verifica se um contato realmente existe.
		verificarExistenciaContato(nome);

		// Remove o contato da map com o nome completo e o telefone do contato.
		contatosMap.remove(nome);

		// Remove o contato do map que cont�m a letra inicial do nome do contato.
		contatosPorLetrasMap.remove(nome.toUpperCase().charAt(0));

		// Atualiza o arquivo de contatos.
		arquivo.gravar(contatosMap.values());
	}

	/**
	 * Altera um contato na agenda.
	 * 
	 * @param contato Contato a ser alterado.
	 * @throws AgendaException Lan�ada se o contato n�o existir.
	 * @throws IOException Lan�ada se ocorrer alguma exce��o de entrada e sa�da.
	 */
	public void alterar(Contato contato) throws AgendaException, IOException {

		// Valida os dados do contato.
		contato.validarDados();

		// Verifica se o nome do contato que ser� alterado realmente existe
		verificarExistenciaContato(contato.getNome());

		// Recoloca o contato alterado no map.
		contatosMap.put(contato.getNome(), contato);

		// Atualiza o arquivo de contatos.
		arquivo.gravar(contatosMap.values());
	}

	/**
	 * Verifica se determinado contato j� existe.
	 * 
	 * @param nome Nome do contato a ser verificado
	 * @throws AgendaException Lan�ada caso o contato n�o exista
	 */
	private void verificarExistenciaContato(String nome) throws AgendaException {

		// M�todo "containsKey" verifica se uma chave existe no map.
		if (!contatosMap.containsKey(nome)) {
			throw new AgendaException("Contato " + nome + " n�o encontrado.");
		}
	}

	/**
	 * Obt�m uma lista de contatos que iniciam com determinada letra.
	 * 
	 * @param letra Letra a ser usada na busca pelos contatos.
	 * @return Lista de contatos que atende ao crit�rio estabelecido
	 */
	public List<Contato> listarContatosPorLetra(char letra) {
		List<Contato> contatos = contatosPorLetrasMap.get(Character
				.toUpperCase(letra));
		if (contatos == null) {
			return new ArrayList<Contato>();
		}

		return contatos;
	}

	/**
	 * Obt�m uma lista de contatos com base em parte do nome.
	 * 
	 * @param parteNome Parte do nome a ser procurada
	 * @return Lista de contatos que atende ao crit�rio especificado
	 */
	public List<Contato> listarContatosPorParteNome(String parteNome) {

		/*
		 * Procura os contatos que iniciem ou terminem com 0 ou mais caracteres
		 * e possuem, no meio, a string fornecida como par�metro.
		 */
		String regex = "\\w*" + parteNome + "\\w*";

		/*
		 * Com o "CASE_INSESITIVE" � garantido que a compara��o � feita
		 * desconsiderando mai�sculas e min�sculas.
		 */
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		/*
		 * Lista que ir� armazenar os contatos que atendem ao crit�rio
		 * estabelecido.
		 */
		List<Contato> contatosEncontrados = new ArrayList<Contato>();

		// Cole��o com os contatos existentes atualmente.
		Collection<Contato> contatosCadastrados = contatosMap.values();

		/*
		 * Verifica se o padr�o fornecido combina com os elementos da cole��o, e
		 * se combinarem os adiciona em uma outra lista.
		 */
		for (Contato contato : contatosCadastrados) {
			Matcher m = p.matcher(contato.getNome());
			if (m.matches()) {
				contatosEncontrados.add(contato);
			}
		}
		return contatosEncontrados;
	}
}
