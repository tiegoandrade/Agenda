package tiegoandrade.github.agenda;

public class Menu {

	/** Constantes relacionadas às opções de menu. */
	public static final int OPCAO_INSERIR = 1;
	public static final int OPCAO_ALTERAR = 2;
	public static final int OPCAO_EXCLUIR = 3;
	public static final int OPCAO_LISTAR_LETRA = 4;
	public static final int OPCAO_PROCURAR = 5;
	public static final int OPCAO_SAIR = 6;

	/**
	 * Exibe as opções do menu e aguarda a escolha do usuário.
	 * 
	 * @return Opção escolhida.
	 * @throws AgendaException
	 */
	public int exibirOpcoes() throws AgendaException {
		System.out.println("--- MENU DE OPÇÕES ---");
		System.out.println("# 1) Inserir contato");
		System.out.println("# 2) Alterar contato");
		System.out.println("# 3) Excluir contato");
		System.out.println("# 4) Listar contatos por letras");
		System.out.println("# 5) Procurar contatos.");
		System.out.println("# 6) Sair");
		System.out.println();
		System.out.println("Escolha uma opção: ");

		int opcao = Console.readInt();

		if (opcao > OPCAO_SAIR || opcao < OPCAO_INSERIR) {
			throw new AgendaException("Opção Inválida");
		}

		return opcao;
	}
}
