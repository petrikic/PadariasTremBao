package Model;

public abstract class PessoaFisica extends Pessoa {
	
	protected String cpf;
	protected String telefone;
	
	public PessoaFisica(String nome, String endereco, String telefone, String cpf) {
		
		super(nome, endereco);
		setCpf(cpf);
		setTelefone(telefone);
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}
	
	
	

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	public String getCpf() {
		return cpf;
	}

	
	public String toString(){
		return super.toString()+"\n CPF:"+this.cpf+" Telefone: "+this.telefone;
	}
	
}
