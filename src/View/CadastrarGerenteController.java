package View;

import java.io.IOException;
import java.util.Optional;
import Controller.FuncionariosController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Gerente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarGerenteController {

	@FXML private TextField tfNomeGerente;
	@FXML private TextField tfEnderecoGerente;
	@FXML private TextField tfTelefoneGerente;
	@FXML private TextField tfCpfGerente;
	@FXML private TextField tfSalarioBaseGerente;
	@FXML Button btnCadastrar;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfGerente.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfGerente.setText(maskCpf.format(novoCpf));
		tfCpfGerente.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefoneGerente.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefoneGerente.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefoneGerente.setText(maskFixo.format(novoTelefone));
		}
		tfTelefoneGerente.positionCaret(novoTelefone.length()+4);
	}

	@FXML
	void btnCadastrarGerente(ActionEvent event) throws IOException{
		Stage thisStage = (Stage) btnCadastrar.getScene().getWindow();
		String nomeGerente = tfNomeGerente.getText();
		String enderecoGerente = tfEnderecoGerente.getText();
		String telefoneGerente = tfTelefoneGerente.getText();
		String cpfGerente = tfCpfGerente.getText();	
		String salarioBaseGerente = tfSalarioBaseGerente.getText();
		
		
		if(!ValidationController.isNome(nomeGerente)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do gerente.";
			dlgInvalido(msg);
			tfNomeGerente.setText("");
			tfNomeGerente.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoGerente)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoGerente.setText("");
			tfEnderecoGerente.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneGerente)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefoneGerente.setText("");
			tfTelefoneGerente.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfGerente)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfGerente.setText("");
			tfCpfGerente.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(salarioBaseGerente)) {
			String msg = "Você digitou um salario inválido. Digite novamente.";
			dlgInvalido(msg);
			tfSalarioBaseGerente.setText("");
			tfSalarioBaseGerente.requestFocus();
		}
		else {
			double salarioBase = Double.parseDouble(salarioBaseGerente);
			String codigo = FuncionariosController.getCodigoFuncionario();
			boolean adicionado;
			Gerente g1 = new Gerente(nomeGerente, enderecoGerente, telefoneGerente,
													cpfGerente,	salarioBase, codigo);
			adicionado = FuncionariosController.cadastrarGerente(g1);
			if(adicionado) {
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
						
				Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
				dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
				dlgFechar.setHeaderText(null);
		        dlgFechar.setTitle("Sair");
		        dlgFechar.setContentText("Gerente adicionado. Deseja cadastrar outro gerente?");
		        Optional<ButtonType> result = dlgFechar.showAndWait();
		        
		        if(result.get() == btnNao) {
		        	thisStage.close();
		        }
		        else {
		        	tfNomeGerente.setText("");
		        	tfEnderecoGerente.setText("");
		        	tfTelefoneGerente.setText("");
		        	tfCpfGerente.setText("");
		        	tfSalarioBaseGerente.setText("");
		        	tfNomeGerente.requestFocus();
		        }
				
			}
			else {
				String msg = "Não foi possível adicionar! Gerente ja existe no sistema, ou Database cheia.";
				dlgInvalido(msg);
			}
		}
	}

}
