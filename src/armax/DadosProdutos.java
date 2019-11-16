package armax;

import Model.Produto;
import Model.ProdutoCatalogo;
public class DadosProdutos implements CRUD {
	
	private ProdutoCatalogo[] produto = new ProdutoCatalogo[50];
	private int size = 0;
	
  
	
	
	@Override
	public boolean inserir(Object obj) {
		boolean inserido = false;

		if(!buscar(obj) && size < 50) {
			for(int i = 0; i < produto.length && !inserido; i++) {
				if( produto[i] == null) {
					produto[i] = (ProdutoCatalogo) obj;
					inserido = true;
					size++;
				}
			}
		}
		return inserido;
	}

	
	@Override
	public boolean buscar(Object obj) {
		boolean encontrado = false;
		ProdutoCatalogo pc1 = (ProdutoCatalogo)obj;
		for(int i = 0; i < produto.length &&!encontrado; i++) {
			if( produto[i] != null && produto[i].getCodigo().equals(pc1.getCodigo())) {
				encontrado = true;	
			}
		}
		return encontrado;
	}
	
	public ProdutoCatalogo[] buscar(String nome) {
		
		ProdutoCatalogo newProduto[] = new ProdutoCatalogo[size];
		int cont = 0;
		
		for (int i = 0; i < produto.length; i++) {
			if (produto[i] != null) {
				if (produto[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newProduto[cont] = produto[i];
					cont++;
				}
			}

		}
		return newProduto;
	}

	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		for(int i = 0; i < produto.length && !atualizado; i++)
		{
			if( produto[i] != null)
			{
				if(produto[i].equals(obj) )
					produto[i] = (Produto) novoObj;
					atualizado = true;
			}
			
		}
		return atualizado;	
	}

	@Override
	public boolean deletar(Object obj) {
		boolean deletado = false;
		ProdutoCatalogo pc1 = (ProdutoCatalogo) obj;
		for(int i = 0; i < produto.length && !deletado; i++) {
			if(produto[i] != null && produto[i].equals(pc1)) {
				produto[i] = null;
				deletado = true;
				size--;
			}							
		}
		return deletado;
	}
	
	
	public ProdutoCatalogo[] getVetorProduto() {
		return produto;
	}
	
	
	
	public void printaDados() { 
		
		for(int i = 0; i < produto.length; i++) {
			if(produto[i] != null) {
				System.out.println(produto[i] + "\n");
			}
		}
	}
}
