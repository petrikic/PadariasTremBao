package Controller;

import Model.Produto;
import Model.Venda;
import armax.DadosVendas;
import armax.Database;

public class VendaController {

	private static DadosVendas dadosVendas = Database.getDadosVendas();
	
	public static boolean novaVenda(Venda venda) {
		Produto p1[] = venda.getSacola().getSacola();
		for(int i = 0; i < p1.length; i++) {
			ProdutoController.removeProduto(p1[i]);
		}
		return dadosVendas.inserir(venda);		
	}
	
	public static boolean deletarVenda(Venda venda) {
		return dadosVendas.deletar(venda);
	}

	public static Venda[] buscarVenda(String nome) {
		return dadosVendas.buscar(nome);
	}

}
