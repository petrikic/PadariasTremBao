package View;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import Controller.VendaController;
import Model.Cliente;
import Model.Produto;
import Model.Sacola;
import Model.Venda;
import Model.Vendedor;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NovaVendaController {
	Sacola sacola = new Sacola();
	static Cliente selectCliente;
	static Vendedor selectVendedor;
	static Produto selectProduto;
	double descontoCliente  = 0;
	double acrecimoVenda = 0;
	double SomaVenda = 0;
	double totalVenda = 0;

	@FXML private TableView<Produto> tvSacola;
	@FXML TextField tfNomeCliente;
	@FXML TextField tfNomeVendedor;
	@FXML ChoiceBox<Integer> chbNumeroParcelas;
	@FXML ChoiceBox<String> chbFormaPagamento;
	@FXML Button btnAddProduto;
	@FXML Button btnFinalizarVenda;
	@FXML Label lblTotal;
		
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	void calcTotalVenda() {
		totalVenda = (descontoCliente + acrecimoVenda)*SomaVenda + SomaVenda;
	}
	
	@FXML void updateTable() {
		Produto p1[] = sacola.getSacola();
		tvSacola.getItems().clear();
		SomaVenda = 0;
        
        for(int i = 0; i < p1.length; i++) {
        	if(p1[i] != null) {
        		tvSacola.getItems().add(p1[i]);
        		SomaVenda += p1[i].getPrecoFinal();
        	}
        }
        calcTotalVenda();
        lblTotal.setText("RS " + totalVenda);
        
	}
	
	@FXML void selecionarCliente() throws IOException {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgSelecionarCliente = new Alert(Alert.AlertType.CONFIRMATION);
		dlgSelecionarCliente.getButtonTypes().setAll(btnSim, btnNao);
		dlgSelecionarCliente.setHeaderText(null);
		dlgSelecionarCliente.setTitle("Selecionar cliente");
		dlgSelecionarCliente.setContentText("Deseja selecionar um cliente?");
        Optional<ButtonType> result = dlgSelecionarCliente.showAndWait();
        
        if(result.get() == btnSim) {
		
			Stage scSelecionarFornecedor = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("FXMLSelecionarClienteNovaVenda.fxml"));
	        Scene scene = new Scene(root);
	        
	        scSelecionarFornecedor.initModality(Modality.APPLICATION_MODAL);
	        
	        scSelecionarFornecedor.setTitle("Selecionar cliente");
	        scSelecionarFornecedor.setScene(scene);
	        scSelecionarFornecedor.showAndWait();
	        if(selectCliente != null) {
	        	tfNomeCliente.setText(selectCliente.getNome());
	        	descontoCliente = selectCliente.desconto();
	        	updateTable();
	        }
        }
	}
	
	@FXML void selecionarVendedor() throws IOException {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgSelecionarVendedor = new Alert(Alert.AlertType.CONFIRMATION);
		dlgSelecionarVendedor.getButtonTypes().setAll(btnSim, btnNao);
		dlgSelecionarVendedor.setHeaderText(null);
		dlgSelecionarVendedor.setTitle("Selecionar vendedor");
		dlgSelecionarVendedor.setContentText("Deseja selecionar um vendedor?");
        Optional<ButtonType> result = dlgSelecionarVendedor.showAndWait();
        
        if(result.get() == btnSim) {
		
			Stage scSelecionarFornecedor = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("FXMLSelecionarVendedorNovaVenda.fxml"));
	        Scene scene = new Scene(root);
	        
	        scSelecionarFornecedor.initModality(Modality.APPLICATION_MODAL);
	        
	        scSelecionarFornecedor.setTitle("Selecionar vendedor");
	        scSelecionarFornecedor.setScene(scene);
	        scSelecionarFornecedor.showAndWait();
	        if(selectVendedor != null) {
	        	tfNomeVendedor.setText(selectVendedor.getNome());
	        }
        }
	}
	
	@FXML void selecionarProduto() throws IOException{
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgSelecionarVendedor = new Alert(Alert.AlertType.CONFIRMATION);
		dlgSelecionarVendedor.getButtonTypes().setAll(btnSim, btnNao);
		dlgSelecionarVendedor.setHeaderText(null);
		dlgSelecionarVendedor.setTitle("Selecionar produto");
		dlgSelecionarVendedor.setContentText("Deseja selecionar um produto?");
        Optional<ButtonType> result = dlgSelecionarVendedor.showAndWait();
        
        if(result.get() == btnSim) {
		
			Stage scSelecionarProduto = new Stage();
	        Parent root = FXMLLoader.load(getClass().getResource("FXMLAdicionarProduto.fxml"));
	        Scene scene = new Scene(root);
	        
	        scSelecionarProduto.initModality(Modality.APPLICATION_MODAL);
	        
	        scSelecionarProduto.setTitle("Selecionar produto");
	        scSelecionarProduto.setScene(scene);
	        scSelecionarProduto.showAndWait();
			if(selectProduto != null) {
				boolean adicionado = sacola.addProduto(selectProduto);
				if(adicionado) {
					totalVenda += selectProduto.getPrecoFinal();
					updateTable();
				}
				else {
					String msg = "Produto ja adicionado, ou sacola cheia!";
					dlgInvalido(msg);
				}
			}
			
        }
	}
	
	@FXML void finalizarVenda() throws IOException{
		Stage thisStage = (Stage) btnFinalizarVenda.getScene().getWindow();
		
		String formaPagamento = chbFormaPagamento.getValue();
		int parcelas = chbNumeroParcelas.getValue();

		if(selectCliente == null) {
			String msg = "Você deve selecionar um cliente para efetuar uma venda!";
			dlgInvalido(msg);
		}
		else if(selectVendedor == null) {
			String msg = "Você deve selecionar um vendedor para efetuar uma venda!";
			dlgInvalido(msg);
		}
		else if(totalVenda == 0) {
			String msg = "Você deve selecionar ao menos um produto para efetuar uma venda!";
			dlgInvalido(msg);
		}
		else {
			ButtonType btnSim = new ButtonType("Sim");
			ButtonType btnNao = new ButtonType("Não");
					
			Alert dlgFechar = new Alert(Alert.AlertType.CONFIRMATION);
			dlgFechar.getButtonTypes().setAll(btnSim, btnNao);
			dlgFechar.setHeaderText(null);
	        dlgFechar.setTitle("Finalizar venda");
	        dlgFechar.setContentText("Deseja finalizar a venda?");
	        Optional<ButtonType> result = dlgFechar.showAndWait();
	        
	        if(result.get() == btnSim) {
	        	Venda novaVenda = new Venda(formaPagamento, parcelas, sacola,
	        						totalVenda, selectVendedor, selectCliente);
	        	VendaController.novaVenda(novaVenda);
	        	selectVendedor.efetuaVenda();
	        	selectCliente.acumulaValorDasCompras(totalVenda);
	        	String msg = "Venda efetuada.";
	        	dlgInvalido(msg);
	        	thisStage.close();
	        }
		}
		        
			
	}

	
	@FXML void selecionaFormaPagamento(){
		if(chbFormaPagamento.getValue().equals("Crédito")) {
			int valor = chbNumeroParcelas.getValue();
			chbNumeroParcelas.getItems().clear();
			chbNumeroParcelas.setValue(valor);
			chbNumeroParcelas.getItems().add(1);
			chbNumeroParcelas.getItems().add(2);
			chbNumeroParcelas.getItems().add(3);
			chbNumeroParcelas.getItems().add(4);
			chbNumeroParcelas.getItems().add(5);
			chbNumeroParcelas.getItems().add(6);
			acrecimoVenda = 0.02;
		}
		else {
			chbNumeroParcelas.getItems().clear();
			chbNumeroParcelas.getItems().add(1);
			chbNumeroParcelas.setValue(1);
			acrecimoVenda = 0;
		}
		updateTable();
	}

	@FXML
    void initialize(){
		
		
		tfNomeCliente.setEditable(false);
		tfNomeVendedor.setEditable(false);
		chbNumeroParcelas.getItems().add(1);
		chbNumeroParcelas.setValue(1);
		chbFormaPagamento.getItems().add("Dinheiro");
		chbFormaPagamento.getItems().add("Débito");
		chbFormaPagamento.getItems().add("Crédito");
		chbFormaPagamento.setValue("Dinheiro");
		lblTotal.setText("R$ 0.0");
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	
		TableColumn< Produto, Integer> colId = new TableColumn<Produto, Integer>("Id");
		colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
		
		TableColumn<Produto, String> nomeProduto = new TableColumn<Produto, String>("Nome");
		nomeProduto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));
		
		TableColumn<Produto, String> apelidoProduto = new TableColumn<Produto, String>("Apelido");
		apelidoProduto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApelido()));
		
		TableColumn<Produto, Double> precoProduto = new TableColumn<Produto, Double>("Preço");
		precoProduto.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecoFinal()).asObject());
		
		TableColumn<Produto, String> dataVencimentoProduto = new TableColumn<Produto, String>("Vencimento");
		dataVencimentoProduto.setCellValueFactory(data -> new SimpleStringProperty(df.format(data.getValue().getVencimento())));
        		

        tvSacola.getColumns().addAll(colId, nomeProduto, apelidoProduto, precoProduto, dataVencimentoProduto);
        
        updateTable();
        }

}
