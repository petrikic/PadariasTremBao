package View;

import java.util.Optional;

import Controller.FornecedorController;
import Controller.ValidationController;
import Model.Fornecedor;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
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

public class GestaoFornecedorController {
	
	private boolean editando = false;
	private Fornecedor selecionado;
	private Fornecedor fEditar;

	@FXML private TableView<Fornecedor> tvFornecedor;
	@FXML TextField tfPesquisarFornecedor;
	@FXML TextField tfNomeFornecedor;
	@FXML TextField tfEnderecoFornecedor;
	@FXML TextField tfCnpjFornecedor;
	@FXML TextField tfDescontoFornecedor;
	@FXML CheckBox cbRecorrenteFornecedor;
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
		btnEditar.setDisable(false);
		btnCancelar.setDisable(true);
		btnExcluir.setDisable(true);
		btnSalvar.setDisable(true);
		tfNomeFornecedor.setEditable(false);
		tfEnderecoFornecedor.setEditable(false);
		tfCnpjFornecedor.setEditable(false);
		tfDescontoFornecedor.setEditable(false);
		cbRecorrenteFornecedor.setDisable(true);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		editando = true;
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnSalvar.setDisable(false);
		tfNomeFornecedor.setEditable(true);
		tfEnderecoFornecedor.setEditable(true);
		tfCnpjFornecedor.setEditable(true);
		cbRecorrenteFornecedor.setDisable(false);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
		if(cbRecorrenteFornecedor.isSelected()) {
			tfDescontoFornecedor.setEditable(true);
		}
			
	}
	
	@FXML void editarDescontoFornecedor(){
		if(cbRecorrenteFornecedor.isSelected()) {
			tfDescontoFornecedor.setEditable(true);
			tfDescontoFornecedor.requestFocus();
		}
		else {
			tfDescontoFornecedor.setText("0.0");
			tfDescontoFornecedor.setEditable(false);
		}
	}
	
	@FXML void selecionarFornecedor() {
		if(!editando) {
			selecionado = tvFornecedor.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				tfNomeFornecedor.setText(selecionado.getNome());
				tfEnderecoFornecedor.setText(selecionado.getEndereco());
				tfCnpjFornecedor.setText(selecionado.getCnpj());
				tfDescontoFornecedor.setText(selecionado.getPorcentDesconto()+"");
				cbRecorrenteFornecedor.setSelected(selecionado.getIsRecorrente());
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void editarFornecedor() {
		if(selecionado != null)
			fEditar = selecionado;
		unlock();
	}
	
	@FXML void cancelarEdicaoFornecedor() {
		lock();
		selecionarFornecedor();
	}
	
	@FXML void excluirFornecedor() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir o fornecedor?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(FornecedorController.removerFornecedor(fEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Fornecedor excluido!");
    			aviso.showAndWait();
    			
    			tfNomeFornecedor.setText("");
    			tfEnderecoFornecedor.setText("");
    			tfCnpjFornecedor.setText("");
    			tfDescontoFornecedor.setText("");
    			cbRecorrenteFornecedor.setSelected(false);
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
        	}
        	else {
        		String msg = "Não Foi possível excluir o fornecedor, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void salvarFornecedor() {
		String nomeFornecedor = tfNomeFornecedor.getText();
		String enderecoFornecedor = tfEnderecoFornecedor.getText();
		String CNPJFornecedor = tfCnpjFornecedor.getText();	
		String descontoFornecedor = tfDescontoFornecedor.getText();
		boolean isRecorrenteFornecedor = cbRecorrenteFornecedor.isSelected();
		double desconto = 0;
		if(ValidationController.isStringDecimal(descontoFornecedor)) {
			desconto = Double.parseDouble(descontoFornecedor);
		}
		else {
			desconto = 0;
		}
		
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
		else if(desconto < 0 || desconto >100) {
			String msg = "Você digitou um valor para desconto inválido.";
			dlgInvalido(msg);
			tfDescontoFornecedor.setText("");
			tfDescontoFornecedor.requestFocus();
		}
		else {
			fEditar.setNome(nomeFornecedor);
			fEditar.setEndereco(enderecoFornecedor);
			fEditar.setCnpj(CNPJFornecedor);
			fEditar.setPorcentDesconto(desconto);
			fEditar.setRecorrente(isRecorrenteFornecedor);
			Alert aviso = new Alert(Alert.AlertType.INFORMATION);
			aviso.setHeaderText(null);
			aviso.setTitle("Aviso");
			aviso.setContentText("Fornecedor atualizado com sucesso!.");
			aviso.showAndWait();
			lock();
			selecionarFornecedor();
			updateTable();
		}
	}
	
	@FXML void updateTable() {
		Fornecedor f1[] = FornecedorController.buscarFornecedor(tfPesquisarFornecedor.getText());
		tvFornecedor.getItems().clear();
        
        for(int i = 0; i < f1.length; i++) {
        	if(f1[i] != null)
        		tvFornecedor.getItems().add(f1[i]);
        }
	}

    @FXML
    void initialize(){
    	lock();
    	btnEditar.setDisable(true);
    	
        TableColumn<Fornecedor,String> colFornecedor = new TableColumn("Fornecedor");
        colFornecedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Fornecedor,String> colEndereco = new TableColumn("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Fornecedor,String> colCnpj = new TableColumn("CNPJ");
        colCnpj.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCnpj()));
        
        TableColumn<Fornecedor,Boolean> colRecorrente = new TableColumn("Recorrente");
        colRecorrente.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().getIsRecorrente()));
        
        TableColumn<Fornecedor,Double> colDesconto = new TableColumn("Desconto");
        colDesconto.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPorcentDesconto()).asObject());
        
        colRecorrente.setCellFactory(CheckBoxTableCell.forTableColumn(colRecorrente));
		

        tvFornecedor.getColumns().addAll(colFornecedor, colEndereco, colCnpj, colRecorrente, colDesconto);
        
        updateTable();
        }


}
