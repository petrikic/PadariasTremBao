package Controller;

import Model.Gerente;
import Model.Padeiro;
import Model.Vendedor;
import armax.DadosGerentes;
import armax.DadosPadeiros;
import armax.DadosVendedores;
import armax.Database;

public class FuncionariosController {

	private static  DadosVendedores dadosVendedores = Database.getDadosVendedores();
	private static  DadosGerentes dadosGerentes = Database.getDadosGerentes();
	private static  DadosPadeiros dadosPadeiros = Database.getDadosPadeiros();
	private static int codigoFuncionario = 768;
	
	public static boolean cadastrarVendedor(Vendedor vendedor) {
		return dadosVendedores.inserir(vendedor);		
	}
	
	public static boolean cadastrarGerente(Gerente gerente) {
		return dadosGerentes.inserir(gerente);
	}
	
	public static boolean cadastrarPadeiro(Padeiro padeiro) {
		return dadosPadeiros.inserir(padeiro);
	}
	
	public static boolean removerVendedor(Vendedor vendedor) {
		return dadosVendedores.deletar(vendedor);
	}
	
	public static boolean removerGerente(Gerente gerente) {
		return dadosGerentes.deletar(gerente);
	}
	
	public static boolean removerPadeiro(Padeiro padeiro) {
		return dadosPadeiros.deletar(padeiro);
	}
	
	public static Vendedor[] buscarVendedor(String nome) {
		return dadosVendedores.buscar(nome);
	}
	
	public static Gerente[] buscarGerente(String nome) {
		return dadosGerentes.buscar(nome);
	}
	
	public static Padeiro[] buscarPadeiro(String nome) {
		return dadosPadeiros.buscar(nome);
	}

	
	public static String getCodigoFuncionario() {
		return codigoFuncionario++ + "";
	}

			
	
}
