package Model;

import java.util.Date;

public class Venda implements  Imposto {
	
	private Date data;
	private String formaPagamento;
	private int parcelas;
	private Sacola sacola;
	private double total;
	private Vendedor vendedor;
	private Cliente cliente;
	
	
	
	
	public Venda(String formaPagamento, int parcelas, Sacola sacola, double total,
												Vendedor vendedor, Cliente cliente) {
		data = new Date();
		this.formaPagamento = formaPagamento;
		this.parcelas = parcelas;
		this.sacola = sacola;
		this.total = total;
		this.vendedor = vendedor;
		this.cliente = cliente;
		
        
	}
	
	
	public Vendedor getVendedor() {
		return vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Sacola getSacola() {
		return sacola;
	}

	public double getTotalVenda() {
		return total;
	}

	public Date getData() {
		return data;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}
	
	public int getParcelas() {
		return parcelas;
	}

	//adiciona o valor de imposto da compra 
	@Override
	public double calculaImposto() {
		return total*0.15 + total;
	}
	       

  	public String toString(){
    return "Vendedor: "+ vendedor.getNome()+"\n Cliente: "+cliente.getNome()+ "\nCpf:"+cliente.getCpf()+"\n Forma de pagamento: "+ formaPagamento +"\n produtos:"+sacola.toString()+"\n total: "+total;
  }
}
