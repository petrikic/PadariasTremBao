package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Produto extends ProdutoCatalogo{
	private int id;
	private Date dataVencimento;

	public Produto(String nome, String apelido, String codigo, Fornecedor forn,
			double precoCusto, boolean perecivel, int validade) {
		
		super(nome, apelido, codigo, forn, precoCusto, perecivel, validade);
		setVencimento(validade);
	}

	public int getId() {
		return id;
	}
	// recebe como parametro um id pois cada unidade do produto tem um id diferente.
	public void setId(int id) {
		this.id = id;
	}

	public Date getVencimento() {
		return dataVencimento;
	}

	//recebe a validade do produto e soma com a data atual para para gerar a data de vencimento.
	private void setVencimento(int validade) {
		Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, validade);
	    Date novaData = calendar.getTime();
	    this.dataVencimento = novaData;
	}
	
	//retorna a data de vencimento formatada conforme o modelo generico dd/MM/yyyy
	public String getVencimentoFormatado() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String novaData = df.format(dataVencimento);
		return novaData;
	}
	
	

}