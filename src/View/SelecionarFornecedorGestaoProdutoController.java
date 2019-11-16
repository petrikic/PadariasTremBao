package View;

import Controller.FornecedorController;
import Model.Fornecedor;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;

public class SelecionarFornecedorGestaoProdutoController {
	
	@FXML private TableView<Fornecedor> tvFornecedor;
	@FXML TextField tfPesquisarFornecedor;
	@FXML Button btnSelecionarFornecedor;
	
	@FXML void selecionarFornecedor() {
		Stage thisStage = (Stage) btnSelecionarFornecedor.getScene().getWindow();
		GestaoProdutoController.fSelecionado = tvFornecedor.getSelectionModel().getSelectedItem();
		thisStage.close();
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
