package View;

import java.io.IOException;
import java.util.Optional;

import Controller.FuncionariosController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Padeiro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarPadeiroController {

	@FXML private TextField tfNomePadeiro;
	@FXML private TextField tfEnderecoPadeiro;
	@FXML private TextField tfTelefonePadeiro;
	@FXML private TextField tfCpfPadeiro;
	@FXML private TextField tfSalarioBasePadeiro;
	@FXML Button btnCadastrar;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfPadeiro.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfPadeiro.setText(maskCpf.format(novoCpf));
		tfCpfPadeiro.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefonePadeiro.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefonePadeiro.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefonePadeiro.setText(maskFixo.format(novoTelefone));
		}
		tfTelefonePadeiro.positionCaret(novoTelefone.length()+4);
	}

	@FXML
	void btnCadastrarPadeiro(ActionEvent event) throws IOException{
		Stage thisStage = (Stage) btnCadastrar.getScene().getWindow();
		String nomePadeiro = tfNomePadeiro.getText();
		String enderecoPadeiro = tfEnderecoPadeiro.getText();
		String telefonePadeiro = tfTelefonePadeiro.getText();
		String cpfPadeiro = tfCpfPadeiro.getText();	
		String salarioBasePadeiro = tfSalarioBasePadeiro.getText();
		
		
		if(!ValidationController.isNome(nomePadeiro)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do padeiro.";
			dlgInvalido(msg);
			tfNomePadeiro.setText("");
			tfNomePadeiro.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoPadeiro)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoPadeiro.setText("");
			tfEnderecoPadeiro.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefonePadeiro)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefonePadeiro.setText("");
			tfTelefonePadeiro.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfPadeiro)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfPadeiro.setText("");
			tfCpfPadeiro.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(salarioBasePadeiro)) {
			String msg = "Você digitou um salario inválido. Digite novamente.";
			dlgInvalido(msg);
			tfSalarioBasePadeiro.setText("");
			tfSalarioBasePadeiro.requestFocus();
		}
		else {
			double salarioBase = Double.parseDouble(salarioBasePadeiro);
			String codigo = FuncionariosController.getCodigoFuncionario();
			boolean adicionado;
			Padeiro p1 = new Padeiro(nomePadeiro, enderecoPadeiro, telefonePadeiro,
													cpfPadeiro,	salarioBase, codigo);
			adicionado = FuncionariosController.cadastrarPadeiro(p1);
			if(adicionado) {
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
						
				Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
				dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
				dlgFechar.setHeaderText(null);
		        dlgFechar.setTitle("Sair");
		        dlgFechar.setContentText("Padeiro adicionado. Deseja cadastrar outro padeiro?");
		        Optional<ButtonType> result = dlgFechar.showAndWait();
		        
		        if(result.get() == btnNao) {
		        	thisStage.close();
		        }
		        else {
		        	tfNomePadeiro.setText("");
		        	tfEnderecoPadeiro.setText("");
		        	tfTelefonePadeiro.setText("");
		        	tfCpfPadeiro.setText("");
		        	tfSalarioBasePadeiro.setText("");
		        	tfNomePadeiro.requestFocus();
		        }
				
			}
			else {
				String msg = "Não foi possível adicionar! Padeiro ja existe no sistema, ou Database cheia.";
				dlgInvalido(msg);
			}
		}
	}

}
