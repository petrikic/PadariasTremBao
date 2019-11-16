package View;

import java.io.IOException;
import java.util.Optional;

import Controller.ClienteController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarClienteController {

	@FXML private TextField tfNomeCliente;
	@FXML private TextField tfEnderecoCliente;
	@FXML private TextField tfTelefoneCliente;
	@FXML private TextField tfCpfCliente;
	@FXML Button btnCadastrar;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfCliente.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfCliente.setText(maskCpf.format(novoCpf));
		tfCpfCliente.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefoneCliente.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefoneCliente.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefoneCliente.setText(maskFixo.format(novoTelefone));
		}
		tfTelefoneCliente.positionCaret(novoTelefone.length()+4);
	}

	@FXML
	void btnCadastrarCliente(ActionEvent event) throws IOException{
		Stage thisStage = (Stage) btnCadastrar.getScene().getWindow();
		String nomeCliente = tfNomeCliente.getText();
		String enderecoCliente = tfEnderecoCliente.getText();
		String telefoneCliente = tfTelefoneCliente.getText();
		String cpfCliente = tfCpfCliente.getText();	
		
		
		if(!ValidationController.isNome(nomeCliente)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do cliente.";
			dlgInvalido(msg);
			tfNomeCliente.setText("");
			tfNomeCliente.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoCliente)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoCliente.setText("");
			tfEnderecoCliente.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneCliente)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefoneCliente.setText("");
			tfTelefoneCliente.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfCliente)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfCliente.setText("");
			tfCpfCliente.requestFocus();
		}
		else {
			boolean adicionado;
			Cliente c1 = new Cliente(nomeCliente, enderecoCliente, telefoneCliente, cpfCliente);
			adicionado = ClienteController.cadastrarCliente(c1);
			if(adicionado) {
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
						
				Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
				dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
				dlgFechar.setHeaderText(null);
		        dlgFechar.setTitle("Sair");
		        dlgFechar.setContentText("Cliente adicionado. Deseja cadastrar outro cliente?");
		        Optional<ButtonType> result = dlgFechar.showAndWait();
		        
		        if(result.get() == btnNao) {
		        	thisStage.close();
		        }
		        else {
		        	tfNomeCliente.setText("");
		        	tfEnderecoCliente.setText("");
		        	tfTelefoneCliente.setText("");
		        	tfCpfCliente.requestFocus();
		        }
				
			}
			else {
				String msg = "Não foi possível adicionar! Cliente ja existe no sistema, ou Database cheia.";
				dlgInvalido(msg);
			}
		}
	}
}
