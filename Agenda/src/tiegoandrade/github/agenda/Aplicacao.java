package tiegoandrade.github.agenda;

import java.io.IOException;
import java.util.List;

/**
 * Classe que possibilita a interação do usuário com a agenda.
 * 
 * @author Tiego
 *
 */
public class Aplicacao {

	/** Menu de opções */
	private Menu menu;

	/** Agenda de contatos */
	private Agenda agenda;

	/**
	 * Inicia a aplicação
	 * 
	 * @throws IOException
	 */
	public void iniciar() throws IOException {

		menu = new Menu();
		agenda = new Agenda();
		int opcao = 0;

		// Fica em loop enquanto o usuário não escolher a opção de sair.
		while (opcao != Menu.OPCAO_SAIR) {
			try {

				// Exibe o menu.
				opcao = menu.exibirOpcoes();

				// Verifica a opção escolhida pelo usuário.
				switch (opcao) {
				case Menu.OPCAO_INSERIR:
					inserir();
					break;
				case Menu.OPCAO_ALTERAR:
					alterar();
					break;
				case Menu.OPCAO_EXCLUIR:
					excluir();
					break;
				case Menu.OPCAO_LISTAR_LETRA:
					listarPorLetra();
					break;
				case Menu.OPCAO_PROCURAR:
					procurar();
					break;
				}
			} catch (AgendaException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println();
			}
		}
		System.out.println("Fim da aplicação!");
	}

	/**
	 * Insere os dados de um contato.
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void inserir() throws AgendaException, IOException {

		// Solicita os dados.
		System.out.print("Nome: ");
		String nome = Console.readString();
		System.out.print("Telefone: ");
		String telefone = Console.readString();

		// Cria o contato e o insere na coleção.
		Contato contato = new Contato(nome, telefone);
		agenda.inserir(contato);

		System.out.println("Contato Inserido!");
		System.out.println();
	}

	/**
	 * Altera os dados de um contato.
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void alterar() throws AgendaException, IOException {

		// Solicita os dados.
		System.out.print("Nome: ");
		String nome = Console.readString();
		System.out.print("Telefone: ");
		String telefone = Console.readString();

		// Cria o Contato e o altera.
		Contato contato = new Contato(nome, telefone);
		agenda.alterar(contato);

		System.out.println("Contato alterado!");
		System.out.println();
	}

	/**
	 * Exclui os dados de um contato.
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void excluir() throws AgendaException, IOException {

		// Solicita os dados.
		System.out.print("Nome: ");
		String nome = Console.readString();

		// Exclui o contato.
		agenda.excluir(nome);

		System.out.println("Contato excluído!");
		System.out.println();
	}

	/**
	 * Lista os dados de um contato que começa com determinada letra.
	 * 
	 * @throws AgendaException
	 */
	private void listarPorLetra() throws AgendaException {

		// Solicita os dados
		System.out.println("Letra: ");
		char letra = Console.readChar();

		// Obtém os contatos com base na letra.
		List<Contato> contatos = agenda.listarContatosPorLetra(letra);
		System.out.println("Contatos com a letra ' "
				+ Character.toUpperCase(letra) + " ': ");

		/*
		 * Exibe os contatos se houver algum que supra os requisitos
		 * solicitados.
		 */
		if (contatos.isEmpty()) {
			System.out.println("Nenhum contato encontrado!");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}

		System.out.println();
	}

	/**
	 * Procura um contato de acordo com uma parte do seu nome.
	 * 
	 * @throws AgendaException
	 */
	private void procurar() throws AgendaException {
		
		// Solicita uma parte do nome para busca.
		System.out.println("Parte do nome: ");
		String parteNome = Console.readString();
		
		// Obtém os contatos com base na parte do nome.
		List<Contato> contatos = agenda.listarContatosPorParteNome(parteNome);

		System.out.println("Contatos encontrados com a parte do nome ' "
				+ parteNome + " ': ");
		
		/*
		 * Exibe os contatos se houver algum que supra os requisitos
		 * solicitados.
		 */
		if (contatos.isEmpty()) {
			System.out.println("Nenhum contato encontrado!");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}

		System.out.println();
	}
}
