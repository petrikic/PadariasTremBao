package Model;

public abstract class Pessoa {
	
	private String nome;
	private String endereco;
	

	public Pessoa(String nome, String endereco) {
		setNome(nome);
		setEndereco(endereco);
	}	

	
	public void setNome(String nome) {
			this.nome = nome;

	}
	
	public String getNome() {
		return nome;
	}

	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEndereco() {
		return endereco;
	}

	
	public String toString(){
		return "Nome: "+ this.nome +"\nEndereço: "+ this.endereco + "\n";
	}


	
	


}