package armax;

import Model.Fornecedor;


public class DadosFornecedores implements CRUD {
	
	private Fornecedor[] fornecedor = new Fornecedor[50];
	private int size = 0;
	
	@Override
	public boolean inserir(Object obj) {
		boolean inserido = false;
		Fornecedor f1 = (Fornecedor) obj;
		if(buscarCnpj(f1.getCnpj())) {
			return false;
		}
		for (int i = 0; i < fornecedor.length && !inserido; i++) {
			if (fornecedor[i] == null) {
				fornecedor[i] = f1;
				inserido = true;
				size++;
			}
			

		}
		return inserido;
	}

	@Override
	public boolean buscar(Object obj) {
		for (int i = 0; i < fornecedor.length; i++) {
			if (fornecedor[i] != null) {
				if (fornecedor[i].equals(obj))
					return true;
			}

		}
		return false;
	}


	public Fornecedor[] buscar(String nome) {
		Fornecedor newFornecedor[] = new Fornecedor[fornecedor.length];
		int cont = 0;
		
		for (int i = 0; i < fornecedor.length; i++) {
			if (fornecedor[i] != null) {
				if (fornecedor[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
					newFornecedor[cont] = fornecedor[i];
					cont++;
				}
			}

		}
		return newFornecedor;
	}
	
	public boolean buscarCnpj(String cnpj) {
		for (int i = 0; i < fornecedor.length; i++) {
			if (fornecedor[i] != null) {
				if ((fornecedor[i].getCnpj()).equals(cnpj)) {
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public boolean atualizar(Object obj, Object novoObj) {
		boolean atualizado = false;
		for(int i = 0; i < fornecedor.length && !atualizado; i++)
		{
			if( fornecedor[i] != null)
			{
				if(fornecedor[i].equals(obj) )
					fornecedor[i] = (Fornecedor) novoObj;
					atualizado = true;
			}
			
		}
		return atualizado;
	}

	@Override
	public boolean deletar(Object obj) {
		boolean deletado = false;
		for (int i = 0; i < fornecedor.length && !deletado; i++) {
			if (fornecedor[i] != null) {
				if (fornecedor[i].equals(obj)) {
					fornecedor[i] = null;
					deletado = true;
					size--;
				}
			}
		}
		return deletado;
	}
	
	public int size() {
		return size;
	}
	
	public Fornecedor[] listar() {
		return fornecedor;
	}

  
	
	public void printaDados() { 
		
		for(int i = 0; i < fornecedor.length; i++) {
			if(fornecedor[i] != null) {
				
				System.out.println(fornecedor[i] + "\n");
			}
			
		}
	}

}
