package tiegoandrade.github.agenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que manipula o arquivo onde os registros da agenda serão armazenados.
 * 
 * @author Tiego
 *
 */
public class ArquivoAgenda {

	/** Constanto com o nome do arquivo da agenda. */
	private static final String ARQUIVO_AGENDA = "agenda.txt";

	/**
	 * Grava os contatos no arquivo que representa a agenda.
	 * 
	 * @param contatos
	 *            É uma coleção dos contatos que serão gravados no arquivo que
	 *            representa a agenda.
	 * @throws IOException
	 */
	public void gravar(Collection<Contato> contatos) throws IOException {

		try (PrintWriter writer = new PrintWriter(ARQUIVO_AGENDA)) {
			for (Contato contato : contatos) {
				writer.print(contato.getNome());
				writer.print(", ");
				writer.println(contato.getTelefone());
			}
		}
	}

	/**
	 * Lê os contatos que se encontra no arquivo.
	 * 
	 * @return
	 */
	public List<Contato> ler() {

		List<Contato> contatos = new ArrayList<Contato>();

		try (Scanner scanner = new Scanner(new File(ARQUIVO_AGENDA))) {
			while (scanner.hasNextLine()) {
				String contatoStr = scanner.nextLine();
				String[] tokens = contatoStr.split(",");
				Contato contato = new Contato(tokens[0], tokens[1]);
				contatos.add(contato);
			}

			return contatos;
		} catch (FileNotFoundException e) {
			return contatos;
		}
	}
}
