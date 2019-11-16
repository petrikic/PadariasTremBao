package armax;

import Model.Venda;

public class DadosVendas implements CRUD {

	private Venda[] venda = new Venda[200];
	private int size = 0;
	
	@Override
	public boolean inserir(Object obj) {
		boolean inserido = false;
		for (int i = 0; i < venda.length && !inserido; i++) {
			if (venda[i] == null) {
				venda[i] = (Venda)obj;
				inserido = true;
				size++;
			}
			

		}
		return inserido;
	}

	@Override
	public boolean buscar(Object obj) {
		Venda newVenda = (Venda) obj;
		for (int i = 0; i < venda.length; i++) {
			if (venda[i] != null) {
				if (venda[i].equals(newVenda))
					return true;
			}

		}
		return false;
	}


	public Venda[] buscar(String nome) {
		Venda newVenda[] = new Venda[venda.length];
		int cont = 0;
		
		for (int i = 0; i < venda.length; i++) {
			if (venda[i] != null) {
				if (venda[i].getCliente().getNome().toLowerCase().contains(nome.toLowerCase())) {
					newVenda[cont] = venda[i];
					cont++;
				}
			}

		}
		return newVenda;
	}

	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		for(int i = 0; i < venda.length && !atualizado; i++)
		{
			if( venda[i] != null)
			{
				if(venda[i].equals(obj) )
					venda[i] = (Venda) novoObj;
					atualizado = true;
			}
			
		}
		return atualizado;
	}

	@Override
	public boolean deletar(Object obj) {
		boolean deletado = false;
		Venda newVenda = (Venda) obj;
		for (int i = 0; i < venda.length && !deletado; i++) {
			if (venda[i] != null && venda[i].equals(newVenda)) {
				venda[i] = null;
				deletado = true;
				size--;
			}
		}
		return deletado;
	}
	
	public int size() {
		return size;
	}
	
	public Venda[] listaVendas() {
		return venda;
	}



	public void printaDados() {

		for (int i = 0; i < venda.length; i++) {
			if (venda[i] != null) {
				System.out.println(venda[i].toString());
				System.out.println();

			}

		}

	}
  

}
