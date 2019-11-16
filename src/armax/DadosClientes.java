package armax;

import Model.Cliente;



public class DadosClientes implements CRUD {
	//essa descricao serve para a maioria das classes do pacote armax
	

	//Cliente[] cliente: vetor que guarda  objetos do tipo Cliente pelo metodo inserir(obj) 
	private Cliente[] cliente = new Cliente[50];
	
	/*size: atributo que define quantos clientes realmente estao cadastrados
	*  ele eh icrementado quando um Cliente eh inserido e decrementado quando
	*  um cliente eh deletado.
	*/
	private int size = 0;
	


	@Override
	public boolean inserir(Object obj) {
		// busca se o obj existe no vetor e percorre o vetor procurando uma posicao null para inseri-lo
		boolean inserido = false;
		for(int i = 0; i < cliente.length && !buscar(obj) && !inserido && size < 50; i++){
			if( cliente[i] == null){
				cliente[i] = (Cliente) obj;
				inserido = true;
				size++;
				
			}	
				
		}
		return inserido;
	}
	@Override
	public boolean buscar(Object obj) {
		//percorre o vetor procurando se existe um cliente com mesmo cpf. Retorna um boolean referente a busca.
		Cliente c1 = (Cliente) obj;
		boolean encontrado = false;
		for(int i = 0; i < cliente.length; i++){
			if( cliente[i] != null && cliente[i].getCpf().equals(c1.getCpf() )){
				encontrado = true;	
			}
	
		}
		return encontrado;
	}

	public Cliente[] buscar(String nome) {
		//busca que retorna o cliente encontrdado.
		Cliente newCliente[] = new Cliente[size];
		int cont = 0;
		
		for (int i = 0; i < cliente.length; i++) {
			if (cliente[i] != null) {
				if (cliente[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newCliente[cont] = cliente[i];
					cont++;
				}
			}

		}
		return newCliente;
	}


	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		Cliente newCliente = (Cliente) obj;
		for(int i = 0; i < cliente.length && !atualizado; i++){
			if( cliente[i] != null && cliente[i].equals(newCliente)){
				cliente[i] = (Cliente) novoObj;
				atualizado = true;
			}
		}
		return atualizado;
	}
	
	@Override
	public boolean deletar(Object obj) {
		//faz uma busca procurando um Cliente com mesmo endereco que o obj e substitui o valor dele por null.
		Cliente newCliente = (Cliente) obj;
		boolean deletado = false;
		for(int i = 0; i < cliente.length && !deletado; i++){
			if(cliente[i] != null && cliente[i].equals(newCliente)){
				cliente[i] = null;
				deletado = true;
				size--;
			}	
		}
		return deletado;
	}
	
	public void printaDados() { 
		
		for(int i = 0; i < cliente.length; i++) {
			if(cliente[i] != null) {
				
				System.out.println(cliente[i]);
				System.out.println();
			}
			
		}
	}
	
	}
