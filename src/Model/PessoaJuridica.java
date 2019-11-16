package Model;

public abstract class PessoaJuridica extends Pessoa {

	protected String cnpj;
	
	public PessoaJuridica(String nome, String endereco, String cnpj) {
		
		super(nome, endereco);
		setCnpj(cnpj);
		
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String toString() {
		
		return super.toString()+"CNPJ: "+this.cnpj + "\n";
	}
}
