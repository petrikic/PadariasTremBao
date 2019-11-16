package armax;

public interface CRUD {

	/*
	*
	*	interface com metodos que 
	*	representam as principais
	*	funcoes do banco de dados,
	*	para implementa-los nas 
	*	classes com vetores que 
	*	guardam informacoes do 
	*	sistema.
	*	
	*/
	public boolean inserir(Object obj);
	public boolean buscar(Object obj);
	public boolean atualizar(Object obj, Object novoObj);
	public boolean deletar(Object obj);
}
