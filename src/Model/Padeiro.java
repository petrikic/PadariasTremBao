package Model;

public class Padeiro extends Funcionario {
private int horasExtras = 0;

	public Padeiro(String nome, String endereco, String telefone, String cpf, double salarioBase, String codigo) {

		super(nome, endereco, telefone, cpf, salarioBase, codigo);
	}


	
	@Override
	public double salarioBonificado() {
		return getSalarioBase() + (getSalarioBase()*0.25*horasExtras); //calcula o salario bonificado com base nas horas extras;
	}
	
	
	
	
	public void adicionaHorasExtras(int horasExtras)
	{
		this.horasExtras += horasExtras; //Soma a quantidade de horas trabalhadas no horario alternativo
	}
	
	public void zeraHorasExtras() {
		this.horasExtras = 0;	// Zera a quantidade de horas extras, ao efetuar o pagamento.
	}
	
	public int getHorasExtras()
	{
		return horasExtras; // Retorna a quantidade de horas trabalhadas no horario alternativo
	}
	
	public String toString() {
		return super.toString()+"\nHoras Extras: "+horasExtras;
	}


	
}