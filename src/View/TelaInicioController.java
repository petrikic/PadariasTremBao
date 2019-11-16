package View;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaInicioController implements Initializable {
		
	
	
	@FXML
	void btnMenuNovaVenda(ActionEvent event) throws IOException{
		Stage scNovaVenda = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLNovaVenda.fxml"));
        Scene scene = new Scene(root);
        
        scNovaVenda.initModality(Modality.APPLICATION_MODAL);
        
        scNovaVenda.setTitle("Nova Venda");
        scNovaVenda.setScene(scene);
        scNovaVenda.show();
	}
	
	@FXML
	void btnMenuConsultarVenda(ActionEvent event) throws IOException{
		Stage scConsultarVenda = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoVenda.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarVenda.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarVenda.setTitle("Gestão de vendas");
        scConsultarVenda.setScene(scene);
        scConsultarVenda.show();
	}
	
	@FXML
	void btnMenuCadastrarProduto(ActionEvent event) throws IOException{
		Stage scCadastrarProduto = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarProduto.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarProduto.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarProduto.setTitle("Cadastro de Produtos");
        scCadastrarProduto.setScene(scene);
        scCadastrarProduto.show();
	}
	
	@FXML
	void btnMenuConsultarProduto(ActionEvent event) throws IOException{
		Stage scConsultarProduto = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoProduto.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarProduto.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarProduto.setTitle("Gestão de produtos");
        scConsultarProduto.setScene(scene);
        scConsultarProduto.show();
	}
	
	@FXML
	void btnMenuCadastrarCliente(ActionEvent event) throws IOException{
		Stage scCadastrarCliente = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarCliente.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarCliente.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarCliente.setTitle("Cadastro de clientes");
        scCadastrarCliente.setScene(scene);
        scCadastrarCliente.show();
	}
	
	@FXML
	void btnMenuConsultarCliente(ActionEvent event) throws IOException{
		Stage scConsultarCliente = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoCliente.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarCliente.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarCliente.setTitle("Gestão de clientes");
        scConsultarCliente.setScene(scene);
        scConsultarCliente.show();
	}
	
	@FXML
	void btnMenuCadastrarFornecedor(ActionEvent event) throws IOException{
		Stage scCadastrarFornecedor = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarFornecedor.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarFornecedor.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarFornecedor.setTitle("Cadastro de fornecedores");
        scCadastrarFornecedor.setScene(scene);
        scCadastrarFornecedor.show();
	}
	
	@FXML
	void btnMenuConsultarFornecedor(ActionEvent event) throws IOException{
		Stage scConsultarFornecedor = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoFornecedor.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarFornecedor.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarFornecedor.setTitle("Gestão de fornecedores");
        scConsultarFornecedor.setScene(scene);
        scConsultarFornecedor.show();
	}
	
	@FXML
	void btnMenuCadastrarVendedor(ActionEvent event) throws IOException{
		Stage scCadastrarVendedor = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarVendedor.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarVendedor.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarVendedor.setTitle("Cadastro de vendedores");
        scCadastrarVendedor.setScene(scene);
        scCadastrarVendedor.show();
	}
	
	@FXML
	void btnMenuConsultarVendedor(ActionEvent event) throws IOException{
		Stage scConsultarVendedor = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoVendedor.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarVendedor.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarVendedor.setTitle("Gestão de vendedores");
        scConsultarVendedor.setScene(scene);
        scConsultarVendedor.show();
	}
	
	@FXML
	void btnMenuCadastrarGerente(ActionEvent event) throws IOException{
		Stage scCadastrarGerentes = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarGerente.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarGerentes.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarGerentes.setTitle("Cadastro de gerentes");
        scCadastrarGerentes.setScene(scene);
        scCadastrarGerentes.show();
	}
	
	@FXML
	void btnMenuConsultarGerente(ActionEvent event) throws IOException{
		Stage scConsultarGerente = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoGerente.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarGerente.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarGerente.setTitle("Gestão de gerentes");
        scConsultarGerente.setScene(scene);
        scConsultarGerente.show();
	}
	
	@FXML
	void btnMenuCadastrarPadeiro(ActionEvent event) throws IOException{
		Stage scCadastrarPadeiro = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarPadeiro.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarPadeiro.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarPadeiro.setTitle("Cadastro de padeiros");
        scCadastrarPadeiro.setScene(scene);
        scCadastrarPadeiro.show();
	}
	
	@FXML
	void btnMenuConsultarPadeiro(ActionEvent event) throws IOException{
		Stage scConsultarPadeiro = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoPadeiro.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarPadeiro.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarPadeiro.setTitle("Gestão de padeiros");
        scConsultarPadeiro.setScene(scene);
        scConsultarPadeiro.show();
	}
	
	@FXML
	void btnMenuCadastrarFuncionario(ActionEvent event) throws IOException{
		Stage scCadastrarFuncionario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCadastrarFuncionario.fxml"));
        Scene scene = new Scene(root);
        
        scCadastrarFuncionario.initModality(Modality.APPLICATION_MODAL);
        
        scCadastrarFuncionario.setTitle("Cadastro de funcionarios");
        scCadastrarFuncionario.setScene(scene);
        scCadastrarFuncionario.show();
	}
	
	@FXML
	void btnMenuConsultarFuncionario(ActionEvent event) throws IOException{
		Stage scConsultarFuncionario = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoFuncionario.fxml"));
        Scene scene = new Scene(root);
        
        scConsultarFuncionario.initModality(Modality.APPLICATION_MODAL);
        
        scConsultarFuncionario.setTitle("Gestão de funcionarios");
        scConsultarFuncionario.setScene(scene);
        scConsultarFuncionario.show();
	}
	
	@FXML
	void btnMenuGestaoPagamento(ActionEvent event) throws IOException{
		Stage scGestaoPagamento = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoPagamento.fxml"));
        Scene scene = new Scene(root);
        
        scGestaoPagamento.initModality(Modality.APPLICATION_MODAL);
        
        scGestaoPagamento.setTitle("Pagamento de funcionários");
        scGestaoPagamento.setScene(scene);
        scGestaoPagamento.show();
	}
	
	@FXML
	void btnMenuGestaoImposto(ActionEvent event) throws IOException{
		Stage scGestaoImposto = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGestaoImposto.fxml"));
        Scene scene = new Scene(root);
        
        scGestaoImposto.initModality(Modality.APPLICATION_MODAL);
        
        scGestaoImposto.setTitle("Impostos a pagar");
        scGestaoImposto.setScene(scene);
        scGestaoImposto.show();
	}
	
	@FXML
	void btnSair(ActionEvent event) {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
		dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
		dlgFechar.setHeaderText(null);
        dlgFechar.setTitle("Sair");
        dlgFechar.setContentText("Deseja realmente fechar o sistema?");
        Optional<ButtonType> result = dlgFechar.showAndWait();
        
        if(result.get() == btnSim) {
        	System.exit(0);
        }
	}
	
	@FXML
	void btnMenuAbout(ActionEvent event) throws IOException{
		Stage scAbout = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAbout.fxml"));
        Scene scene = new Scene(root);
        
        scAbout.initModality(Modality.APPLICATION_MODAL);
        
        scAbout.setTitle("Sobre");
        scAbout.setScene(scene);
        scAbout.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}


}