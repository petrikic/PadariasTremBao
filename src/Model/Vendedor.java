package Model;

public class Vendedor extends Funcionario {
	
	public int metaDeVenda;
	public int qtdeVendas;

	public Vendedor(String nome, String endereco, String telefone, String cpf, double salarioBase, String codigo, int metaDeVenda) {

		super(nome, endereco, telefone, cpf, salarioBase, codigo);
		setMetaDeVenda(metaDeVenda);
	}
	
	@Override
	public double salarioBonificado() {
		if (qtdeVendas >= metaDeVenda) // Verifica se o vendedor bateu a meta de vendas para receber a bonificacao e
		{								// realiza o calculo do salario bonificado e retorna o valor do salario
										// bonificado.
			double salarioBonificado;
			salarioBonificado = (getSalarioBase() * 0.10) + getSalarioBase();
			return salarioBonificado;
		} else {
			return super.getSalarioBase();
		}
	}


	public void setMetaDeVenda(int metaDeVenda) {
		this.metaDeVenda = metaDeVenda;
	}


	public int getMetaDeVenda() {
		return metaDeVenda;
	}

	public void efetuaVenda() {
		qtdeVendas++;		//incrementa a cada venda;
	}
	
	public void zeraQtdVendas() {
		qtdeVendas = 0;		//zera a quantidade de vendas ao ser efetuado o pagamento;
	}
	
	public int getQtDeVendas() {
		return qtdeVendas;
	}
}