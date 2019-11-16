package armax;

import Model.Gerente;

public class DadosGerentes implements CRUD {
	
	private Gerente[] gerente = new Gerente[100];
	private int size = 0;
	
	@Override
	public boolean inserir(Object obj) {
		boolean inserido = false;
		for(int i = 0; i < gerente.length && !buscar(obj) && !inserido; i++){
			if( gerente[i] == null){
				gerente[i] = (Gerente) obj;
				inserido = true;
				size++;
			}	
		}
		return inserido;
	}

	@Override
	public boolean buscar(Object obj) {
		Gerente g1 = (Gerente)obj;
		boolean encontrado = false;
		for(int i = 0; i < gerente.length && !encontrado; i++){
			if( gerente[i] != null && gerente[i].equals(g1)){
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public Gerente[] buscar(String nome) {
		Gerente newGerente[] = new Gerente[size];
		int cont = 0;
		
		for (int i = 0; i < gerente.length; i++) {
			if (gerente[i] != null) {
				if (gerente[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newGerente[cont] = gerente[i];
					cont++;
				}
			}

		}
		return newGerente;
	}


	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		Gerente newGerente = (Gerente) obj;
		for(int i = 0; i < gerente.length && !atualizado; i++){
			if( gerente[i] != null && gerente[i].equals(newGerente)){
				gerente[i] = (Gerente) novoObj;
				atualizado = true;
			}
		}
		return atualizado;
	}

	@Override
	public boolean deletar(Object obj) {
		Gerente newGerente = (Gerente) obj;
		boolean deletado = false;
		for(int i = 0; i < gerente.length && !deletado; i++){
			if(gerente[i] != null && gerente[i].equals(newGerente)){
				gerente[i] = null;
				deletado = true;
				size--;
			}	
		}
		return deletado;
	}
	
	public void printaDados() { 
		for(int i = 0; i < gerente.length; i++)		
		  if(gerente[i] != null) {
        System.out.println(gerente[i].toString());
		    System.out.println();
      }
  
			
		
	}

}
