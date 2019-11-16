package Controller;

import Model.Cliente;
import armax.DadosClientes;
import armax.Database;

public class ClienteController {


	/*classe que faz o vinculo das classe DadosClientes, Cliente e da interface
	  ela recebe informacoes da interface e utiliza metodos do pacote armax
	*/

	private static DadosClientes dadosClientes = Database.getDadosClientes();
	
	public static boolean cadastrarCliente(Cliente cliente) {
		return dadosClientes.inserir(cliente);		
	}
	
	public static boolean removerCliente(Cliente cliente) {
		return dadosClientes.deletar(cliente);
	}

	public static Cliente[] buscarCliente(String nome) {
		return dadosClientes.buscar(nome);
	}

}
