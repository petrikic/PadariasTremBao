package Model;

public class Sacola {

	/*conjunto de produtos de cada venda*/

	private Produto[] sacola = new Produto[20];

	//verifica se essa undidade do produto ja esta inserida na sacola
	//percorre o vetor sacola procurando uma posicao null e adiciona um produto
	public boolean addProduto(Produto produto) {
		boolean inserido = false;
		boolean encontrado = busca(produto);
		for (int i = 0; i < this.sacola.length && !encontrado && !inserido; i++) {
			if (this.sacola[i] == null) {
				this.sacola[i] = produto;
				inserido = true;

			}

		}
		return inserido;
	}
	//recebe um produto como parametro e percorre o vetor sacola para verificar se ele existe
	//se sim, retorna true, caso contrario ,false.
	public boolean busca(Produto produto) {
		boolean encontrado = false;
		for(int i = 0; i < sacola.length && !encontrado; i++) {
			if(sacola [i] != null && sacola[i].equals(produto)) 
				encontrado = true;
		}
		return encontrado;
	}
	//
	public void removeProtudo(Produto produto) {

		for (int i = 0; i < this.sacola.length; i++) {
			if (this.sacola[i] != null) {
				if (this.sacola[i].equals(produto))
					this.sacola[i] = null;
			}
		}
	}

	//"esvazia" a sacola criando um novo objeto e apontando essa variavel sacola para esse vetor vazio
	public void zera() {

		sacola = new Produto[20];
	}
	
	public Produto[] getSacola() {
		return sacola;
	}


  public String toString() {
    String strProduto = "";
    for(int i = 0; i < sacola.length; i++)
      if(sacola[i]!= null)
        strProduto = strProduto +"\n"+sacola[i].getNome()+ "Codigo "+sacola[i].getCodigo();
   
    return strProduto; 
  }

}
