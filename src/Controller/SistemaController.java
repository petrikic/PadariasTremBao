package Controller;

import armax.DadosSistema;
import armax.Database;

public class SistemaController {
	/*aqui sao inserido informacoes gerais do sistema*/
	private static DadosSistema dadosSistema = Database.getDadosSistema();
	
	public static double getLimiteGold() {
		return dadosSistema.getLimiteGold();
	}
	public static void setLimiteGold(double limiteGold) {
		dadosSistema.setLimiteGold(limiteGold);
	}
	public static double getLimitePlatinium() {
		return dadosSistema.getLimitePlatinium();
	}
	public static void setLimitePlatinium(double limitePlatinium) {
		dadosSistema.setLimitePlatinium(limitePlatinium);
	}

}
