package Controller;

public class ValidationController {
	/*valida infos*/


	public static boolean isOnlyDigit(String texto) {
		String caracteresValidos = "^[0-9]*$";
		return texto.matches(caracteresValidos);
	}
	
	public static boolean isCNPJ(String cnpj) {
		//verifica se o cnpj tem tamanho e caracteres validos.
		boolean valido = false;
		cnpj = cnpj.replace(".", "");
		cnpj = cnpj.replace("-","");
		cnpj = cnpj.replace("/", "");
		
		System.out.println(cnpj);

		if(cnpj.matches(".*[0-9]") && cnpj.length() == 14){
			valido = true;
		}
			
		return valido;
	}
	
	public static boolean isNome(String nome) {
		// verifica se o nome possui algum caractere fora do padrao de caracteres validos
		String caracteresValidos = "^[a-zA-Z¡¬√¿«… Õ”‘’⁄‹·‚„‡ÁÈÍÌÛÙı˙¸ ]*$";
		if(nome.length() < 4) {
			return false;
		}
		if(!nome.matches(caracteresValidos)) {
			return false;
		}
		return true;
	}
	
	public static boolean isStringDecimal(String nome) {
		String caracteresValidos = "^[0-9.]*$";
		return nome.matches(caracteresValidos);
	}
	
	public static boolean isEndereco(String endereco) {
		return endereco.length() > 10;
	}
	
	public static boolean isTelefone(String telefone) {
		//verifica se o telefone tem tamanho e caracteres validos.
		boolean valido = true;

		telefone = telefone.replace("-", "");
		telefone = telefone.replace("(", "");
		telefone = telefone.replace(")", "");

		if (!telefone.matches("[0-9]*")) {
			valido = false;
		}
		else if (telefone.length() <10 || telefone.length() > 11) {
			valido = false;
		}

		else if (telefone.length() == 11 && telefone.charAt(2) != '9') {
			valido = false;
		}
		return valido;

	}
	
	public static boolean isCpf(String cpf) {
		//verifica se o cpf tem tamanho e caracteres validos.
		boolean valido = false;
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-","");

		if(cpf.matches(".*[0-9]") && cpf.length() == 11){
			valido = true;
		}
			
		return valido;

	}
	
	

}
