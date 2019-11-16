package Model;

import Controller.SistemaController;

public class CartaoFidelidade {
	
	private static double limiteGold;
	private static double limitePlatinum;
	private boolean isGold;
	private boolean isPlatinum;
	
	public CartaoFidelidade() {

		/*adiciona o valor de compra que o cliente tem que alcancar
		para criar um vinculo gold ou platinum e conseguir desconto.
		valor que ja foi definido no sistemaController.
		*/
		limiteGold = SistemaController.getLimiteGold();
		limitePlatinum = SistemaController.getLimitePlatinium();
		
	}
	
	public static double getLimiteGold() {
		return limiteGold; 
	}
	public static void setLimiteGold(double limiteGold) {
		CartaoFidelidade.limiteGold = limiteGold;
	}
	public static double getLimitePlatinum() {
		return limitePlatinum;
	}
	public static void setLimitePlatinum(double limitePlatinum) {
		CartaoFidelidade.limitePlatinum = limitePlatinum;
	}

	//retorna o tipo de vinculo do cliente com a loja referente ao cartao.
  	public String vinculo() {
    if(isGold == true)
      return "Gold";
    else if(isPlatinum == true)
      return "Platinum";
    else
      return "nenhum vinculo";
  	}


  	/*
  	*	Retorna true se o valor acumulado de compras do cliente eh o suficiente 
  	*	para ele ter um vinculo com a loja (ter o desconto do cartao fidelidade) , seja gold ou platinum.
  	*	E adiciona um valo booleano nas variaveis isPlatinum e IsGold de acordo com o valo acumulado do cliente
  	*/
	public boolean isFiel(double valorAcumulado) {

		if(valorAcumulado >= limitePlatinum) {
			this.isPlatinum = true;
			this.isGold = false;
			return true;
		}
		else if(valorAcumulado >= limiteGold) {
			this.isGold = true;
			return true;
		}
		return false;
	}

	/*retorna o desconto do cliente de acordo com seu vinculo com a loja. 
	*  nenhum: 0, Gold : 0.05 ou Platinum: 0.1
	*/
	public double desconto() {
		if(isGold == true)
			return -0.05;
		else if(isPlatinum == true)
			return -0.1;
		else 
			return 0;

	}


}

