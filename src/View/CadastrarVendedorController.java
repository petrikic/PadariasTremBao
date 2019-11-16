package View;

import java.io.IOException;
import java.util.Optional;

import Controller.FuncionariosController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Vendedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarVendedorController {

	@FXML private TextField tfNomeVendedor;
	@FXML private TextField tfEnderecoVendedor;
	@FXML private TextField tfTelefoneVendedor;
	@FXML private TextField tfCpfVendedor;
	@FXML private TextField tfSalarioBaseVendedor;
	@FXML private TextField tfMetaVendedor;
	@FXML Button btnCadastrar;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfVendedor.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfVendedor.setText(maskCpf.format(novoCpf));
		tfCpfVendedor.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefoneVendedor.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefoneVendedor.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefoneVendedor.setText(maskFixo.format(novoTelefone));
		}
		tfTelefoneVendedor.positionCaret(novoTelefone.length()+4);
	}

	@FXML
	void btnCadastrarVendedor(ActionEvent event) throws IOException{
		Stage thisStage = (Stage) btnCadastrar.getScene().getWindow();
		String nomeVendedor = tfNomeVendedor.getText();
		String enderecoVendedor = tfEnderecoVendedor.getText();
		String telefoneVendedor = tfTelefoneVendedor.getText();
		String cpfVendedor = tfCpfVendedor.getText();
		String salarioBaseVendedor = tfSalarioBaseVendedor.getText();
		String metaVendedor = tfMetaVendedor.getText();
		
		
		
		if(!ValidationController.isNome(nomeVendedor)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do cliente.";
			dlgInvalido(msg);
			tfNomeVendedor.setText("");
			tfNomeVendedor.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoVendedor)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoVendedor.setText("");
			tfEnderecoVendedor.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneVendedor)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefoneVendedor.setText("");
			tfTelefoneVendedor.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfVendedor)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfVendedor.setText("");
			tfCpfVendedor.requestFocus();
		}
		else if(!ValidationController.isOnlyDigit(metaVendedor)) {
			String msg = "Você digitou uma meta inválida. Digite uma meta novamente.";
			dlgInvalido(msg);
			tfMetaVendedor.setText("");
			tfMetaVendedor.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(salarioBaseVendedor)) {
			String msg = "Você digitou um salario inválido. Digite novamente.";
			dlgInvalido(msg);
			tfSalarioBaseVendedor.setText("");
			tfSalarioBaseVendedor.requestFocus();
		}
		else {
			double salarioBase = Double.parseDouble(salarioBaseVendedor);
			int metaDeVenda = Integer.parseInt(metaVendedor);
			String codigo = FuncionariosController.getCodigoFuncionario();
			boolean adicionado;
			Vendedor v1 = new Vendedor(nomeVendedor, enderecoVendedor, telefoneVendedor, cpfVendedor,
																	salarioBase, codigo, metaDeVenda);
			adicionado = FuncionariosController.cadastrarVendedor(v1);
			if(adicionado) {
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
						
				Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
				dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
				dlgFechar.setHeaderText(null);
		        dlgFechar.setTitle("Sair");
		        dlgFechar.setContentText("Vendedor adicionado. Deseja cadastrar outro vendedor?");
		        Optional<ButtonType> result = dlgFechar.showAndWait();
		        
		        if(result.get() == btnNao) {
		        	thisStage.close();
		        }
		        else {
		        	tfNomeVendedor.setText("");
		        	tfEnderecoVendedor.setText("");
		        	tfTelefoneVendedor.setText("");
		        	tfCpfVendedor.setText("");
		        	tfSalarioBaseVendedor.setText("");
		        	tfMetaVendedor.setText("");
		        	tfNomeVendedor.requestFocus();
		        }
				
			}
			else {
				String msg = "Não foi possível adicionar! Vendedor ja existe no sistema, ou Database cheia.";
				dlgInvalido(msg);
			}
		}
	}

}
