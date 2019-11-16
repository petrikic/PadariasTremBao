package Controller;


import armax.DadosEstoque;
import armax.DadosProdutos;
import armax.Database;
import Model.Fornecedor;
import Model.Produto;
import Model.ProdutoCatalogo;



public class ProdutoController {

	private static DadosProdutos dadosProdutos = Database.getDadosProdutos();
	private static DadosEstoque dadosEstoque = Database.getDadosEstoque();
	

	//referente ao catalogo de produtos de no maximo 50 tipos (armax.DadosProdutos)-------------
	public static boolean cadastraProduto(ProdutoCatalogo produto) {	
		return dadosProdutos.inserir(produto);
		
	}	

	public static ProdutoCatalogo[] buscarProdutoCatalogo(String nome) {
		return dadosProdutos.buscar(nome);
	}


	public static boolean removeProdutoCatalogo(ProdutoCatalogo produto) {
		return dadosProdutos.deletar(produto);
	}
	
	//-------------------------------------------------------------------------------------------
	
	//referente ao estoque de produtos (armax.DadosEstoque) -------------------------------------
	public static boolean adicionaProduto(ProdutoCatalogo produto) {
		String nome = produto.getNome();
		String apelido = produto.getApelido();
		String codigo = produto.getCodigo();
		Fornecedor forn = produto.getFornecedor();
		double precoCusto = produto.getPrecoDeCusto();
		boolean perecivel = produto.isPerecivel();
		int validade = produto.getValidade();
		
		Produto p1 = new Produto(nome, apelido, codigo, forn, precoCusto, perecivel, validade);
		
		return dadosEstoque.inserir(p1);
	}
	
	public static Produto[] buscarProduto(String nome) {
		return dadosEstoque.buscar(nome);
	}
	
	public static boolean removeProduto(Produto produto) {
		return dadosEstoque.deletar(produto);
	}
	
	public static int size() {
		return dadosEstoque.size();
	}
	
	
}
