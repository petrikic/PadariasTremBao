package Model;

import Controller.SistemaController;

public class Cliente extends PessoaFisica {
	
	private double valorDeComprasAcumulado;
	private CartaoFidelidade cartao;

	public Cliente(String nome, String endereco, String telefone, String cpf) {

		super(nome, endereco, telefone, cpf);
		this.valorDeComprasAcumulado = 0;
		cartao = new CartaoFidelidade();
	}
	
	/*retorna a porcentagem de desconto que o cliente tem direito.
	*e seta o valor que o cliente tem que alcancar para conseguir tais descontos
	*para caso esse valor tenha mudado desde a instanciacao do cliente e do cartao fidelidade 
	*esse valor se atualize na compra
	*/
	public double desconto() {
		CartaoFidelidade.setLimiteGold(SistemaController.getLimiteGold());
		CartaoFidelidade.setLimitePlatinum(SistemaController.getLimitePlatinium());
		cartao.isFiel(valorDeComprasAcumulado);
		return cartao.desconto();
	}

	public void acumulaValorDasCompras(double preco) {
		// Adiciona o valor em compras a uma variavel que sera utilizada para fazer a
		// verificação do cartao de gold/platinum
		valorDeComprasAcumulado += preco;
	}

	
	public CartaoFidelidade getCf() {
		return cartao;
	}

	public void setCf(CartaoFidelidade cf) {
		this.cartao = cf;
	}


  	public String toString() {
      if(cartao.isFiel(valorDeComprasAcumulado) == true) {
		    return super.toString() + "\nValor acumulado: "+this.valorDeComprasAcumulado+"\nCartao Fidelidade: "+cartao.vinculo();
      }
      else
        return super.toString()+"\nValor acumulado: "+this.valorDeComprasAcumulado; 
	}


}