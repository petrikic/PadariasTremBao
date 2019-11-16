package Controller;

import Model.Fornecedor;
import armax.DadosFornecedores;
import armax.Database;

public class FornecedorController {
	
	
	private static DadosFornecedores dadosFornecedores = Database.getDadosFornecedores();
	
	public static boolean cadastrarFornecedor(Fornecedor fornecedor) {
		return dadosFornecedores.inserir(fornecedor);		
	}
	
	public static boolean removerFornecedor(Fornecedor fornecedor) {
		return dadosFornecedores.deletar(fornecedor);
	}

	public static Fornecedor[] buscarFornecedor(String nome) {
		return dadosFornecedores.buscar(nome);
	}

	
}
