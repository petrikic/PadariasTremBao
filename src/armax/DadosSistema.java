package armax;

public class DadosSistema {
	// infos que sao gerais para todos os objetos.
	/*
	*	Aqui eh definido o limite de todos os cartoes fidelidade que sao gerados
	*/
	private double limiteGold;
	private double limitePlatinium;
	
	
	public double getLimiteGold() {
		return limiteGold;
	}
	public void setLimiteGold(double limiteGold) {
		this.limiteGold = limiteGold;
	}
	public double getLimitePlatinium() {
		return limitePlatinium;
	}
	public void setLimitePlatinium(double limitePlatinium) {
		this.limitePlatinium = limitePlatinium;
	}

}
