package View;

import Controller.ClienteController;
import Model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelecionarClienteNovaVendaController {

	@FXML private TableView<Cliente> tvCliente;
	@FXML TextField tfPesquisarCliente;
	@FXML Button btnSelecionarCliente;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	@FXML void selecionarCliente() {
		Stage thisStage = (Stage) btnSelecionarCliente.getScene().getWindow();
		NovaVendaController.selectCliente = tvCliente.getSelectionModel().getSelectedItem();
		if(NovaVendaController.selectCliente == null) {
			String msg = "Você não selecionou nenhum cliente!";
			dlgInvalido(msg);
		}
		else {
		thisStage.close();
		}
	}
	
	@FXML void updateTable() {
		Cliente c1[] = ClienteController.buscarCliente(tfPesquisarCliente.getText());
		tvCliente.getItems().clear();
        
        for(int i = 0; i < c1.length; i++) {
        	if(c1[i] != null)
        		tvCliente.getItems().add(c1[i]);
        }
	}
	
	@FXML
    void initialize(){
    	
        TableColumn<Cliente,String> colNome = new TableColumn("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Cliente,String> colEndereco = new TableColumn("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Cliente,String> colTelefone = new TableColumn("Telefone");
        colTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));
        
        TableColumn<Cliente,String> colCpf = new TableColumn("CPF");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        
		

        tvCliente.getColumns().addAll(colNome, colEndereco, colTelefone, colCpf);
        
        updateTable();
        }

}
