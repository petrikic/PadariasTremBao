package Model;

public class Gerente extends Funcionario
{	
	public Gerente(String nome, String endereco, String telefone, String cpf, double salarioBase, String codigo) {

		super(nome, endereco, telefone, cpf, salarioBase, codigo);
	}




	@Override
	public double salarioBonificado() {
		//Adiciona a bonificacao ao salario do gerente e retorna o valor do salario bonificado
		double salarioBonificado;
		salarioBonificado = getSalarioBase()*0.20 + getSalarioBase();
		return salarioBonificado;
		
	}
	
	

	public String toString() {
		return super.toString();
	}


	

	
}