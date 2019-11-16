package armax;

public class Database {
	/*classe que guarda todos os objetos que armazenam informacoes
	* para facilitar suas chamadas nas controllers.
	*
	*/
	private static DadosClientes dadosClientes = new DadosClientes();
	private static DadosEstoque dadosEstoque = new DadosEstoque();
	private static DadosFornecedores dadosFornecedores = new DadosFornecedores();
	private static DadosGerentes dadosGerentes = new DadosGerentes();
	private static DadosPadeiros dadosPadeiros = new DadosPadeiros();
	private static DadosProdutos dadosProdutos = new DadosProdutos();
	private static DadosVendas dadosVendas = new DadosVendas();
	private static DadosVendedores dadosVendedores = new DadosVendedores();
	private static DadosSistema dadosSistema = new DadosSistema();
	
	public static DadosClientes getDadosClientes() {
		return dadosClientes;
	}
	public static DadosEstoque getDadosEstoque() {
		return dadosEstoque;
	}
	public static DadosFornecedores getDadosFornecedores() {
		return dadosFornecedores;
	}
	public static DadosGerentes getDadosGerentes() {
		return dadosGerentes;
	}
	public static DadosPadeiros getDadosPadeiros() {
		return dadosPadeiros;
	}
	public static DadosProdutos getDadosProdutos() {
		return dadosProdutos;
	}
	public static DadosVendas getDadosVendas() {
		return dadosVendas;
	}
	public static DadosVendedores getDadosVendedores() {
		return dadosVendedores;
	}
	public static DadosSistema getDadosSistema() {
		return dadosSistema;
	}

}
