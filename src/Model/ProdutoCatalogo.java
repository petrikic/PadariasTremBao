package Model;

public class ProdutoCatalogo {

	private String nome;
	private String apelido;
	private String codigo;
	private Fornecedor fornecedor;
	private double precoDeCusto;
	private double precoFinal;
	private boolean perecivel;
	private int validade; 

	
	public ProdutoCatalogo(String nome, String apelido, String codigo, Fornecedor forn,
								double precoCusto, boolean perecivel, int validade) {
		
		setNome(nome);
		setApelido(apelido);
		setCodigo(codigo);
		setFornecedor(forn);
		setPrecoDeCusto(precoCusto);
		setPerecivel(perecivel);
		setValidade(validade);
		
	}
	

	// -Codigo-inicio--------------------------------------------------------------------
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	// -Preco--inicio---------------------------------------------------------------------------
	public double getPrecoDeCusto() {
		return precoDeCusto;
	}

	public void setPrecoDeCusto(double precoDeCusto) {
		this.precoDeCusto = precoDeCusto;
		calculaPrecoFinal();
	}
	
	public double getPrecoFinal() {
		return precoFinal;
	}
	
	//calcula o preco final a partir do preco de custo e de um possivel desconto do fornecedor
	private void calculaPrecoFinal() {
		
		this.precoFinal = precoDeCusto * (fornecedor.getPorcentDesconto()/100) + precoDeCusto; 
	
	}
	

	// -Preco--fim---------------------------------------------------------------------------

	public void setValidade(int validade) {
		this.validade = validade;
	}
	
	public int getValidade() {
		return validade;
	}
	
	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}
	
	public boolean isPerecivel() {
		return perecivel;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String toString() {
		return "Nome: " + this.nome + "\nApelido: " + this.apelido + "\nCodigo: " + "\npreco custo:"
				+ this.precoDeCusto + "R$" +"\nFornecedor: " +fornecedor.getNome()+"\nCNPJ: "+fornecedor.getCnpj();
	}

}
