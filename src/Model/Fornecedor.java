package Model;

public class Fornecedor extends PessoaJuridica {
	
	private boolean isRecorrente;
	private double desconto = 0;
	
	public Fornecedor(String nome, String endereco, String cnpj) 
	{
		super(nome, endereco, cnpj);
	}
	public boolean getIsRecorrente() {
		return isRecorrente;
	}
	
	//seta valor boolean que decide se o fornecedor eh recorrente ou nao.
	public void setRecorrente(boolean isRecorrente) {
		this.isRecorrente = isRecorrente;
	}
	public void setPorcentDesconto(double porcent) {
			desconto = porcent;
	}
	//retorna desconto do fornecedor(se fornecedor for recorrente) em cima do preco de custo do produto
	public double getPorcentDesconto() {
		if(isRecorrente == true)
			return desconto;
		else
			return 0;
	}
	
	public String toString() { 
		
		return super.toString() + "Recorrente: "+ this.isRecorrente + "\nDesconto: " + this.desconto + "%\n";
	}

}
