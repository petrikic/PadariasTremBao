package armax;

import Model.Padeiro;

public class DadosPadeiros implements CRUD{
	
	private Padeiro[] padeiro = new Padeiro[100];
	private int size = 0;
	
	@Override
	public boolean inserir(Object obj) {
		boolean inserido = false;
		for(int i = 0; i < padeiro.length && !buscar(obj) && !inserido; i++){
			if( padeiro[i] == null){
				padeiro[i] = (Padeiro) obj;
				inserido = true;
				size++;
			}	
		}
		return inserido;
	}

	@Override
	public boolean buscar(Object obj) {
		Padeiro p1 = (Padeiro)obj;
		boolean encontrado = false;
		for(int i = 0; i < padeiro.length && !encontrado; i++){
			if( padeiro[i] != null && padeiro[i].equals(p1)){
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public Padeiro[] buscar(String nome) {
		Padeiro newPadeiro[] = new Padeiro[size];
		int cont = 0;
		
		for (int i = 0; i < padeiro.length; i++) {
			if (padeiro[i] != null) {
				if (padeiro[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newPadeiro[cont] = padeiro[i];
					cont++;
				}
			}

		}
		return newPadeiro;
	}


	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		Padeiro newPadeiro = (Padeiro) obj;
		for(int i = 0; i < padeiro.length && !atualizado; i++){
			if( padeiro[i] != null && padeiro[i].equals(newPadeiro)){
				padeiro[i] = (Padeiro) novoObj;
				atualizado = true;
			}
		}
		return atualizado;
	}

	@Override
	public boolean deletar(Object obj) {
		Padeiro newPadeiro = (Padeiro) obj;
		boolean deletado = false;
		for(int i = 0; i < padeiro.length && !deletado; i++){
			if(padeiro[i] != null && padeiro[i].equals(newPadeiro)){
				padeiro[i] = null;
				deletado = true;
				size--;
			}	
		}
		return deletado;
	}
	
	public void  printaDados() {
		
		for(int i = 0; i < padeiro.length; i++) {
			
			if(padeiro[i] != null) {
				System.out.print(padeiro[i].toString());
				System.out.println();
			}
		}
		
	}
	
	
}
