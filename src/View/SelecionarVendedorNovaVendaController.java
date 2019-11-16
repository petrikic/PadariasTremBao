package View;

import Controller.FuncionariosController;
import Model.Vendedor;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelecionarVendedorNovaVendaController {

	@FXML private TableView<Vendedor> tvVendedor;
	@FXML TextField tfPesquisarVendedor;
	@FXML Button btnSelecionarVendedor;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void selecionarVendedor() {
		Stage thisStage = (Stage) btnSelecionarVendedor.getScene().getWindow();
		NovaVendaController.selectVendedor = tvVendedor.getSelectionModel().getSelectedItem();
		if(NovaVendaController.selectCliente == null) {
			String msg = "Você não selecionou nenhum vendedor!";
			dlgInvalido(msg);
		}
		else {
		thisStage.close();
		}
	}
	
	@FXML void updateTable() {
		Vendedor v1[] = FuncionariosController.buscarVendedor(tfPesquisarVendedor.getText());
		tvVendedor.getItems().clear();
        
        for(int i = 0; i < v1.length; i++) {
        	if(v1[i] != null)
        		tvVendedor.getItems().add(v1[i]);
        }
	}
	
	@FXML
    void initialize(){
    	
        TableColumn<Vendedor,String> colNome = new TableColumn("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Vendedor,String> colEndereco = new TableColumn("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Vendedor,String> colTelefone = new TableColumn("Telefone");
        colTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));
        
        TableColumn<Vendedor,String> colCpf = new TableColumn("CPF");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        
		

        tvVendedor.getColumns().addAll(colNome, colEndereco, colTelefone, colCpf);
        
        updateTable();
        }

}
