package View;

import Controller.FuncionariosController;
import Model.Funcionario;
import Model.Gerente;
import Model.Padeiro;
import Model.Vendedor;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GestaoPagamentoController {

	private Funcionario selecionado;

	@FXML private TableView<Funcionario> tvFuncionario;
	@FXML TextField tfPesquisarFuncionario;
	@FXML TextField tfNomeFuncionario;
	@FXML TextField tfEnderecoFuncionario;
	@FXML TextField tfTelefoneFuncionario;
	@FXML TextField tfCpfFuncionario;
	@FXML TextField tfValorPagar;
	@FXML ImageView ivLock;
	
	void dlgInvalido(String msg) {
		Alert dlgInvalido = new Alert(Alert.AlertType.WARNING);
		dlgInvalido.setHeaderText(null);
		dlgInvalido.setTitle("Aviso");
		dlgInvalido.setContentText(msg);
		dlgInvalido.showAndWait();
	}
	
	void lock() {
		tfNomeFuncionario.setEditable(false);
		tfNomeFuncionario.setEditable(false);
		tfEnderecoFuncionario.setEditable(false);
		tfTelefoneFuncionario.setEditable(false);
		tfCpfFuncionario.setEditable(false);
		tfValorPagar.setEditable(false);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	@FXML void selecionarFuncionario() {
		selecionado = tvFuncionario.getSelectionModel().getSelectedItem();
		if(selecionado != null) {
			tfNomeFuncionario.setText(selecionado.getNome());
			tfEnderecoFuncionario.setText(selecionado.getEndereco());
			tfTelefoneFuncionario.setText(selecionado.getTelefone());
			tfCpfFuncionario.setText(selecionado.getCpf());
			tfValorPagar.setText((selecionado.salarioBonificado()-selecionado.calculaImposto())+"");
		}
	}
	
	
	@FXML void updateTable() {
		Gerente g1[] = FuncionariosController.buscarGerente(tfPesquisarFuncionario.getText());
		Vendedor v1[] = FuncionariosController.buscarVendedor(tfPesquisarFuncionario.getText());
		Padeiro p1[] = FuncionariosController.buscarPadeiro(tfPesquisarFuncionario.getText());
		
		tvFuncionario.getItems().clear();
        
        for(int i = 0; i < g1.length; i++) {
        	if(g1[i] != null)
        		tvFuncionario.getItems().add(g1[i]);
        }
        
        for(int i = 0; i < v1.length; i++) {
        	if(v1[i] != null)
        		tvFuncionario.getItems().add(v1[i]);
        }
        
        for(int i = 0; i < p1.length; i++) {
        	if(p1[i] != null)
        		tvFuncionario.getItems().add(p1[i]);
        }
	}
	
	@FXML
    void initialize(){
    	lock();
    	
        TableColumn<Funcionario,String> colNome = new TableColumn<Funcionario, String>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Funcionario,String> colEndereco = new TableColumn<Funcionario, String>("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Funcionario,String> colTelefone = new TableColumn<Funcionario, String>("Telefone");
        colTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));
        
        TableColumn<Funcionario,String> colCpf = new TableColumn<Funcionario, String>("CPF");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        TableColumn<Funcionario, String> colCargo = new TableColumn<Funcionario, String>("Cargo");
        colCargo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getClass().getSimpleName()));
        
        TableColumn<Funcionario, Double> colSalarioBase = new TableColumn<Funcionario, Double>("Valor a pagar");
        colSalarioBase.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().salarioBonificado()-data.getValue().calculaImposto()).asObject());
        
		

        tvFuncionario.getColumns().addAll(colNome, colEndereco, colTelefone, colCpf, colCargo, colSalarioBase);
        
        updateTable();
        }

}
