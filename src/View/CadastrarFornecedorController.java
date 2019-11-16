package View;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import Model.Fornecedor;
import Controller.FornecedorController;
import Controller.MaskField;
import Controller.ValidationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CadastrarFornecedorController implements Initializable {
	
	@FXML private TextField tfNomeFornecedor;
	@FXML private TextField tfEnderecoFornecedor;
	@FXML private TextField tfCnpjFornecedor;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void limitaCnpj() {
		String novoCnpj = tfCnpjFornecedor.getText();
		novoCnpj = novoCnpj.replace(".", "");
		novoCnpj = novoCnpj.replace("-","");
		novoCnpj = novoCnpj.replace("/", "");
		if(novoCnpj.length() > 14) {
			novoCnpj = novoCnpj.substring(0,14);
		}
		MaskField maskCnpj = new MaskField("##.###.###/####-##");
		tfCnpjFornecedor.setText(maskCnpj.format(novoCnpj));
		tfCnpjFornecedor.positionCaret(novoCnpj.length()+4);
			
	}
	@FXML Button btnCadastrar;

	@FXML
	void btnCadastrarFornecedor(ActionEvent event) throws IOException{
		Stage thisStage = (Stage) btnCadastrar.getScene().getWindow();
		String nomeFornecedor = tfNomeFornecedor.getText();
		String enderecoFornecedor = tfEnderecoFornecedor.getText();
		String CNPJFornecedor = tfCnpjFornecedor.getText();
		
		
		if(!ValidationController.isNome(nomeFornecedor)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do fornecedor.";
			dlgInvalido(msg);
			tfNomeFornecedor.setText("");
			tfNomeFornecedor.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoFornecedor)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoFornecedor.setText("");
			tfEnderecoFornecedor.requestFocus();
		}
		else if(!ValidationController.isCNPJ(CNPJFornecedor)) {
			String msg = "Você digitou um CNPJ Inválido. Digite o CNPJ novamente.";
			dlgInvalido(msg);
			tfCnpjFornecedor.setText("");
			tfCnpjFornecedor.requestFocus();
		}
		else {
			boolean adicionado;
			Fornecedor f1 = new Fornecedor(nomeFornecedor, enderecoFornecedor, CNPJFornecedor);
			adicionado = FornecedorController.cadastrarFornecedor(f1);
			if(adicionado) {
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
						
				Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
				dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
				dlgFechar.setHeaderText(null);
		        dlgFechar.setTitle("Sair");
		        dlgFechar.setContentText("Fornecedor adicionado. Deseja cadastrar outro fornecedor?");
		        Optional<ButtonType> result = dlgFechar.showAndWait();
		        
		        if(result.get() == btnNao) {
		        	thisStage.close();
		        }
		        else {
		        	tfNomeFornecedor.setText("");
		        	tfEnderecoFornecedor.setText("");
		        	tfCnpjFornecedor.setText("");
		        	tfNomeFornecedor.requestFocus();
		        }
				
			}
			else {
				String msg = "Não foi possível adicionar! Fornecedor ja existe no sistema, ou Database cheia.";
				dlgInvalido(msg);
			}
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
