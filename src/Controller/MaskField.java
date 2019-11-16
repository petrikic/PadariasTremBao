package Controller;

public class MaskField {
	
	private String mascara;

	public MaskField(String mascara) {
		setMascara(mascara);
	}
	
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}
	
	public String format(String formatar) {
		String tMascara = mascara;
		for(int i = 0; i < formatar.length();i++) {
			tMascara = tMascara.replaceFirst("#", formatar.charAt(i)+"");
		}
		tMascara = tMascara.replace("#", "");
		return tMascara;
	}

}
