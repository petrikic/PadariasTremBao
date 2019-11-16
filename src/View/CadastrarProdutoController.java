package View;

import java.io.IOException;
import java.util.Optional;

import Controller.ProdutoController;
import Controller.ValidationController;
import Model.ProdutoCatalogo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadastrarProdutoController {
	
	@FXML TextField tfNomeProduto;
	@FXML TextField tfApelidoProduto;
	@FXML TextField tfCodigoProduto;
	@FXML TextField tfFornecedorProduto;
	@FXML TextField tfPrecoCusto;
	@FXML TextField tfValidadeEmDias;
	@FXML Button btnCadastrar;
	@FXML CheckBox cbPerecivelProduto;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void selecionarFornecedor() throws IOException {
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
					
			Alert dlgSelecionarForncedor = new Alert(Alert.AlertType.CONFIRMATION);
			dlgSelecionarForncedor.getButtonTypes().setAll(btnSim, btnNao);
			dlgSelecionarForncedor.setHeaderText(null);
			dlgSelecionarForncedor.setTitle("Selecionar fornecedor");
			dlgSelecionarForncedor.setContentText("Deseja selecionar um fornecedor?");
	        Optional<ButtonType> result = dlgSelecionarForncedor.showAndWait();
	        
	        if(result.get() == btnSim) {
			
				Stage scSelecionarFornecedor = new Stage();
		        Parent root = FXMLLoader.load(getClass().getResource("FXMLSelecionarFornecedorGestaoProduto.fxml"));
		        Scene scene = new Scene(root);
		        
		        scSelecionarFornecedor.initModality(Modality.APPLICATION_MODAL);
		        
		        scSelecionarFornecedor.setTitle("Selecionar fornecedor");
		        scSelecionarFornecedor.setScene(scene);
		        scSelecionarFornecedor.showAndWait();
		        tfFornecedorProduto.setText(GestaoProdutoController.fSelecionado.getNome());
	        }
		
	}
	
	@FXML void salvarProduto() {
		Stage thisStage = (Stage) btnCadastrar.getScene().getWindow();
		String nomeProduto = tfNomeProduto.getText();
		String apelidoProduto = tfApelidoProduto.getText();
		String codigoProduto = tfCodigoProduto.getText();
		String precoCusto = tfPrecoCusto.getText();
		String validadeDias = tfValidadeEmDias.getText();
		boolean isPerecivel = cbPerecivelProduto.isSelected();
				
		if(!ValidationController.isNome(nomeProduto)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do produto.";
			dlgInvalido(msg);
			tfNomeProduto.setText("");
			tfNomeProduto.requestFocus();
		}
		else if(!ValidationController.isNome(apelidoProduto)) {
			String msg = "Você digitou um apelido inválido. Digite novamente o apelido do produto.";
			dlgInvalido(msg);
			tfApelidoProduto.setText("");
			tfApelidoProduto.requestFocus();
		}
		else if(!ValidationController.isOnlyDigit(codigoProduto) || codigoProduto.length() != 6) {
			String msg = "Você digitou um código inválido. Digite novamente o código do produto.";
			dlgInvalido(msg);
			tfCodigoProduto.setText("");
			tfCodigoProduto.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(precoCusto)) {
			String msg = "Você digitou um preço inválido. Digite novamente o preço do produto.";
			dlgInvalido(msg);
			tfPrecoCusto.setText("");
			tfPrecoCusto.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(validadeDias)) {
			String msg = "Você digitou uma validade inválida. Digite novamente a validade do produto.";
			dlgInvalido(msg);
			tfValidadeEmDias.setText("");
			tfValidadeEmDias.requestFocus();
		}
		
		else {
			
			double dbPrecoCusto = Double.parseDouble(precoCusto);
			int validadeEmDias = Integer.parseInt(validadeDias);
			ProdutoCatalogo pc1 = new ProdutoCatalogo(nomeProduto, apelidoProduto, codigoProduto,
					GestaoProdutoController.fSelecionado, dbPrecoCusto, isPerecivel, validadeEmDias);
			
			boolean cadastrado = ProdutoController.cadastraProduto(pc1);
			if(cadastrado) {
				
				ButtonType btnSim = new ButtonType("Sim");
				ButtonType btnNao = new ButtonType("Não");
						
				Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
				dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
				dlgFechar.setHeaderText(null);
		        dlgFechar.setTitle("Sair");
		        dlgFechar.setContentText("Produto cadastrado. Deseja cadastrar outro produto?");
		        Optional<ButtonType> result = dlgFechar.showAndWait();
		        
		        if(result.get() == btnNao) {
		        	thisStage.close();
		        }
		        else {
		        	tfNomeProduto.setText("");
		        	tfApelidoProduto.setText("");
		        	tfCodigoProduto.setText("");
		        	tfFornecedorProduto.setText("");
		        	tfPrecoCusto.setText("");
		        	tfValidadeEmDias.setText("");
		        	cbPerecivelProduto.setSelected(false);
		        	tfNomeProduto.requestFocus();
		        }
			}
	        else {
	        	Alert aviso = new Alert(Alert.AlertType.INFORMATION);
	        	aviso.setHeaderText(null);
	        	aviso.setTitle("Aviso");
	        	aviso.setContentText("Não foi possível cadastrar! Produto ja existe no sistema, ou Database cheia.");
	        	aviso.showAndWait();
	        }
		}
	}

	@FXML
    void initialize(){
		tfFornecedorProduto.setEditable(false);
	}

}
