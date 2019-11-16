package armax;


import Model.Vendedor;

public class DadosVendedores implements CRUD {
	
	private Vendedor[] vendedor = new Vendedor[100];
	private int size = 0;
	
	@Override
	public boolean inserir(Object obj) {
		boolean inserido = false;
		for(int i = 0; i < vendedor.length && !buscar(obj) && !inserido; i++){
			if( vendedor[i] == null){
				vendedor[i] = (Vendedor) obj;
				inserido = true;
				size++;
			}	
		}
		return inserido;
	}

	@Override
	public boolean buscar(Object obj) {
		Vendedor v1 = (Vendedor)obj;
		boolean encontrado = false;
		for(int i = 0; i < vendedor.length && !encontrado; i++){
			if( vendedor[i] != null && vendedor[i].equals(v1)){
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public Vendedor[] buscar(String nome) {
		Vendedor newVendedor[] = new Vendedor[size];
		int cont = 0;
		
		for (int i = 0; i < vendedor.length; i++) {
			if (vendedor[i] != null) {
				if (vendedor[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newVendedor[cont] = vendedor[i];
					cont++;
				}
			}

		}
		return newVendedor;
	}


	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		Vendedor newVendedor = (Vendedor) obj;
		for(int i = 0; i < vendedor.length && !atualizado; i++){
			if( vendedor[i] != null && vendedor[i].equals(newVendedor)){
				vendedor[i] = (Vendedor) novoObj;
				atualizado = true;
			}
		}
		return atualizado;
	}

	@Override
	public boolean deletar(Object obj) {
		Vendedor newVendedor = (Vendedor) obj;
		boolean deletado = false;
		for(int i = 0; i < vendedor.length && !deletado; i++){
			if(vendedor[i] != null && vendedor[i].equals(newVendedor)){
				vendedor[i] = null;
				deletado = true;
				size--;
			}	
		}
		return deletado;
	}
		
	public void printaDados() {
		
		for(int i = 0; i < vendedor.length; i++) {
			
			if(vendedor[i] != null) {
				System.out.println(vendedor[i]+"\n");
			}
		}
	}

}
