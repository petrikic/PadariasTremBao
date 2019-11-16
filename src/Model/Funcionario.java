package Model;

public abstract class Funcionario extends PessoaFisica implements Imposto {


	private double salarioBase;
	private String codigo;
	

	public Funcionario(String nome, String endereco, String telefone, String cpf, double salarioBase, String codigo) {

		super(nome, endereco, telefone, cpf);
		this.salarioBase = salarioBase;
		this.codigo = codigo;
	}
	
	public abstract double salarioBonificado();
	

	//calcula o imposto que eh descontado no salario dos funcionarios
	@Override
	public double calculaImposto() {
		return salarioBonificado() *  0.18;
		
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
			
		
	public String toString() {
		return super.toString()+ "\n Salario: "+this.salarioBase+" Codigo: "+this.codigo;
	}
}