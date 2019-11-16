package View;

import Controller.ProdutoController;
import Model.Produto;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;

public class AdicionarProdutoController {

	@FXML private TableView<Produto> tvProduto;
	@FXML TextField tfPesquisarProduto;
	@FXML Button btnSelecionarProduto;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void selecionarProduto() {
		Stage thisStage = (Stage) btnSelecionarProduto.getScene().getWindow();
		NovaVendaController.selectProduto = tvProduto.getSelectionModel().getSelectedItem();
		if(NovaVendaController.selectProduto == null) {
			String msg = "Você não selecionou nenhum produto!";
			dlgInvalido(msg);
		}
		else {
		thisStage.close();
		}
	}
	
	@FXML void updateTable() {
		Produto p1[] = ProdutoController.buscarProduto(tfPesquisarProduto.getText());
		tvProduto.getItems().clear();
        
        for(int i = 0; i < p1.length; i++) {
        	if(p1[i] != null)
        		tvProduto.getItems().add(p1[i]);
        }
	}

	@FXML
    void initialize(){
    	
		TableColumn<Produto, Integer> colId = new TableColumn<Produto, Integer>("ID");
		colId.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getId()).asObject());
		
        TableColumn<Produto,String> colNome = new TableColumn<Produto, String>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );

        TableColumn<Produto,String> colApelido = new TableColumn<Produto, String>("Apelido");
        colApelido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApelido()));

        TableColumn<Produto,String> colCodigo = new TableColumn<Produto, String>("Código");
        colCodigo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCodigo()));
        
        TableColumn<Produto,String> colFornecedor = new TableColumn<Produto, String>("Fornecedor");
        colFornecedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFornecedor().getNome()));
        
        TableColumn<Produto,Double> colPrecoCusto = new TableColumn<Produto, Double>("Preço de custo");
        colPrecoCusto.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecoDeCusto()).asObject());
        
        TableColumn<Produto,Double> colPrecoFinal = new TableColumn<Produto, Double>("Preço final");
        colPrecoFinal.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrecoFinal()).asObject());
        
        TableColumn<Produto,Integer> colValidadeEmDias = new TableColumn<Produto, Integer>("Validade (em dias)");
        colValidadeEmDias.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getValidade()).asObject());
        
        TableColumn<Produto,Boolean> colPerecivel = new TableColumn<Produto, Boolean>("Perecível");
        colPerecivel.setCellValueFactory(data -> new SimpleBooleanProperty(data.getValue().isPerecivel()));
        
        TableColumn<Produto, String> colVencimento = new TableColumn<Produto, String>("Vencimento");
        colVencimento.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVencimentoFormatado()));
        
        colPerecivel.setCellFactory(CheckBoxTableCell.forTableColumn(colPerecivel));
		

        tvProduto.getColumns().addAll(colId, colNome, colApelido, colCodigo, colFornecedor,
        				colPrecoCusto, colPrecoFinal, colValidadeEmDias, colVencimento, colPerecivel);
        
        updateTable();
        }
}
