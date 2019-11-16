package armax;

import Model.Produto;

public class DadosEstoque implements CRUD {
	//matriz de unidades de produto
	/*
	*  Cada linha dessa matriz tem unidades de um mesmo
	*  tipo de produto.
	*  Por exemplo:
	*  a linha 4 guarda 30 unidades de cueca virada.
	*
	*/
	private Produto[][] produto = new Produto[50][30];
	private int size = 0;
	private int lastId = 635;
	
	
	public  Produto[][] getProduto() {
		return produto;
	}

	@Override
	public boolean inserir(Object obj) {
		/*usa o metodo buscar linha para definir a linha da matriz produto que esse
		* objeto deve ser inserido 
		*/
		boolean inserido = false;
		int linha = buscarLinha(obj);
		for(int i = 0; i < produto[linha].length && !inserido; i++) {
			if( produto[linha][i] == null) {
				produto[linha][i] = (Produto) obj;
				//inclui um id para essa nova unidade inserida.
				produto[linha][i].setId(lastId++);
				inserido = true;
				size++;
			}
		}
		return inserido;
	}
	

	
	public int buscarLinha(Object obj) {
		/*procura a linha da matriz que guarda unidades de um mesmo produto pelo codigo
		e a retorna */
		Produto p1 = (Produto)obj;
		int pos = -1;
		for(int i = 0; i < produto.length && pos <0; i++) {
			if(produto[i][0] == null || p1.getCodigo().equals(produto[i][0].getCodigo())) {
				pos = i;
			}
		}
		return pos;
	}

	@Override
	public boolean buscar(Object obj) {
		/*procura a linha da matriz esse produto e depois procura 
		  o produto pelas colunas dessa linha .
		*/
		boolean encontrado = false;
		int linha = buscarLinha(obj);
		Produto p1 = (Produto)obj;
		for(int i = 0; i < produto[linha].length &&!encontrado; i++) {
			if( produto[linha][i] != null) {
				if(produto[linha][i].getId() == p1.getId());
					encontrado = true;
			}	
		}
		return encontrado;
	}
	
	public Produto[] buscar(String nome) {
		/*retorna um vetor de produtos com mesmo nome
		*/
		Produto[] newProduto = new Produto[size];
		int cont = 0;
		for(int i = 0; i < produto.length; i++) {
			for(int j = 0; j < produto[i].length; j++) {
				if (produto[i][j] != null && produto[i][j].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newProduto[cont] = produto[i][j];
					cont++;
				}
			}
		}
		
		return newProduto;
	}
	

	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		int linha = buscarLinha(obj);
		Produto p1 = (Produto)obj;
		for(int i = 0; i < produto[linha].length && !atualizado; i++) {
			if( produto[linha][i] != null && produto[linha][i].equals(p1)) {
				produto[linha][i] = (Produto) novoObj;
				atualizado = true;
			}
		}
		return atualizado;		
	}

	@Override
	public boolean deletar(Object obj) {
		boolean deletado = false;
		Produto p1 = (Produto)obj;
		for(int i = 0; i < produto.length && !deletado; i++) {
			for(int j = 0; j < produto[i].length && !deletado; j++) {
				if(produto[i][j] != null) {
					if(produto[i][j].equals(p1)) {
						produto[i][j] = null;
						deletado = true;
						size--;
					}
				}
			}
		}
		return deletado;
	}
	
	public int size() {
		return size;
	}

	
}
	
	
	
	


