package View;

import java.io.IOException;
import java.util.Optional;

import Controller.ProdutoController;
import Controller.ValidationController;
import Model.Fornecedor;
import Model.ProdutoCatalogo;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestaoProdutoController {

	private boolean editando = false;
	private ProdutoCatalogo selecionado;
	private ProdutoCatalogo pEditar;
	static Fornecedor fSelecionado;

	@FXML private TableView<ProdutoCatalogo> tvProduto;
	@FXML TextField tfPesquisarProduto;
	@FXML TextField tfNomeProduto;
	@FXML TextField tfApelidoProduto;
	@FXML TextField tfCodigoProduto;
	@FXML TextField tfFornecedorProduto;
	@FXML TextField tfPrecoCusto;
	@FXML TextField tfPrecoFinal;
	@FXML TextField tfValidadeEmDias;
	@FXML CheckBox cbPerecivelProduto;
	@FXML Button btnAddUnidade;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
	@FXML Button btnSalvar;
	@FXML Button btnCancelar;
	@FXML ImageView ivLock;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	void lock() {
		editando = false;
		btnAddUnidade.setDisable(true);
		btnEditar.setDisable(false);
		btnCancelar.setDisable(true);
		btnExcluir.setDisable(true);
		btnSalvar.setDisable(true);
		tfNomeProduto.setEditable(false);
		tfApelidoProduto.setEditable(false);
		tfCodigoProduto.setEditable(false);
		tfFornecedorProduto.setEditable(false);
		tfPrecoCusto.setEditable(false);
		tfPrecoFinal.setEditable(false);
		tfValidadeEmDias.setEditable(false);
		cbPerecivelProduto.setDisable(true);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		editando = true;
		btnAddUnidade.setDisable(true);
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnSalvar.setDisable(false);
		tfNomeProduto.setEditable(true);
		tfApelidoProduto.setEditable(true);
		tfCodigoProduto.setEditable(true);
		tfPrecoCusto.setEditable(true);
		tfValidadeEmDias.setEditable(true);
		cbPerecivelProduto.setDisable(false);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
			
	}
	
	@FXML void selecionarFornecedor() throws IOException {
		if(editando) {
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
		        tfFornecedorProduto.setText(fSelecionado.getNome());
	        }
		}
	}
	
	@FXML void adicionarUnidadeProduto() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgAdicionar = new Alert(Alert.AlertType.CONFIRMATION);
		dlgAdicionar.getButtonTypes().setAll(btnSim, btnNao);
		dlgAdicionar.setHeaderText(null);
		dlgAdicionar.setTitle("Excluir");
		dlgAdicionar.setContentText("Deseja realmente adicionar uma unidade ao estoque?");
        Optional<ButtonType> result = dlgAdicionar.showAndWait();
        
        if(result.get() == btnSim) {
        	if(ProdutoController.adicionaProduto(selecionado)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Produto adicionado ao estoque!");
    			aviso.showAndWait();

        	}
        	else {
        		String msg = "Não Foi possível adicionar ao estoque, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}

	@FXML void selecionarProduto() {
		if(!editando) {
			selecionado = tvProduto.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				fSelecionado = selecionado.getFornecedor();
				tfNomeProduto.setText(selecionado.getNome());
				tfApelidoProduto.setText(selecionado.getApelido());
				tfCodigoProduto.setText(selecionado.getCodigo());
				tfFornecedorProduto.setText(selecionado.getFornecedor().getNome());
				tfPrecoCusto.setText(selecionado.getPrecoDeCusto()+"");
				tfPrecoFinal.setText(selecionado.getPrecoFinal()+"");
				tfValidadeEmDias.setText(selecionado.getValidade()+"");
				cbPerecivelProduto.setSelected(selecionado.isPerecivel());
				btnAddUnidade.setDisable(false);
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void editarProduto() {
		unlock();
		if(selecionado != null)
			pEditar = selecionado;
	}
	
	@FXML void cancelarEdicaoProduto() {
		lock();
		selecionarProduto();
	}
	
	@FXML void excluirProduto() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir o produto?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(ProdutoController.removeProdutoCatalogo(pEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Produto excluido!");
    			aviso.showAndWait();
    			
    			tfNomeProduto.setText("");
    			tfApelidoProduto.setText("");
    			tfCodigoProduto.setText("");
    			tfFornecedorProduto.setText("");
    			tfPrecoCusto.setText("");
    			tfPrecoFinal.setText("");
    			tfValidadeEmDias.setText("");
    			cbPerecivelProduto.setSelected(false);
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
        	}
        	else {
        		String msg = "Não Foi possível excluir o produto, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void salvarProduto() {
		String nomeProduto = tfNomeProduto.getText();
		String apelidoProduto = tfApelidoProduto.getText();
		String codigoProduto = tfCodigoProduto.getText();
		String precoCusto = tfPrecoCusto.getText();
		String precoFinal = tfPrecoFinal.getText();
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
		else if(!ValidationController.isStringDecimal(precoFinal)) {
			String msg = "Você digitou um preço inválido. Digite novamente o preço do produto.";
			dlgInvalido(msg);
			tfPrecoFinal.setText("");
			tfPrecoFinal.requestFocus();
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
			pEditar.setNome(nomeProduto);
			pEditar.setApelido(apelidoProduto);
			pEditar.setCodigo(codigoProduto);
			pEditar.setPrecoDeCusto(dbPrecoCusto);
			pEditar.setValidade(validadeEmDias);
			pEditar.setPerecivel(isPerecivel);
			pEditar.setFornecedor(fSelecionado);
			
			Alert aviso = new Alert(Alert.AlertType.INFORMATION);
			aviso.setHeaderText(null);
			aviso.setTitle("Aviso");
			aviso.setContentText("Produto atualizado com sucesso!.");
			aviso.showAndWait();
			lock();
			selecionarProduto();
			updateTable();
		}
	}
	
	@FXML void updateTable() {
		ProdutoCatalogo pb1[] = ProdutoController.buscarProdutoCatalogo(tfPesquisarProduto.getText());
		tvProduto.getItems().clear();
        
        for(int i = 0; i < pb1.length; i++) {
        	if(pb1[i] != null)
        		tvProduto.getItems().add(pb1[i]);
        }
	}

    @FXML
    void initialize(){
    	lock();
    	btnAddUnidade.setDisable(true);
    	btnEditar.setDisable(true);
    	
        TableColumn<ProdutoCatalogo,String> colNome = new TableColumn("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<ProdutoCatalogo,String> colApelido = new TableColumn("Apelido");
        colApelido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApelido()));

        TableColumn<ProdutoCatalogo,String> colCodigo = new TableColumn("Código");
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        
        TableColumn<ProdutoCatalogo,String> colFornecedor = new TableColumn("Fornecedor");
        colFornecedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFornecedor().getNome()));
        
        TableColumn<ProdutoCatalogo,Double> colPrecoCusto = new TableColumn("Preço de custo");
        colPrecoCusto.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecoDeCusto()).asObject());
        
        TableColumn<ProdutoCatalogo,Double> colPrecoFinal = new TableColumn("Preço final");
        colPrecoFinal.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecoFinal()).asObject());
        
        TableColumn<ProdutoCatalogo,Integer> colValidade = new TableColumn("Validade");
        colValidade.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getValidade()).asObject());
        
        TableColumn<ProdutoCatalogo,Boolean> colPerecivel = new TableColumn("Perecível");
        colPerecivel.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isPerecivel()));
        
        
        colPerecivel.setCellFactory(CheckBoxTableCell.forTableColumn(colPerecivel));
		

        tvProduto.getColumns().addAll(colNome, colApelido, colCodigo, colFornecedor, colPrecoCusto, colPrecoFinal, colValidade, colPerecivel);
        
        updateTable();
        }

}
