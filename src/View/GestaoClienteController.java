package View;

import java.util.Optional;

import Controller.ClienteController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GestaoClienteController {
	
	private boolean editando = false;
	private Cliente selecionado;
	private Cliente cEditar;

	@FXML private TableView<Cliente> tvCliente;
	@FXML TextField tfPesquisarCliente;
	@FXML TextField tfNomeCliente;
	@FXML TextField tfEnderecoCliente;
	@FXML TextField tfTelefoneCliente;
	@FXML TextField tfCpfCliente;
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
		tfNomeCliente.setEditable(false);
		tfEnderecoCliente.setEditable(false);
		tfTelefoneCliente.setEditable(false);
		tfCpfCliente.setEditable(false);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		editando = true;
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnSalvar.setDisable(false);
		tfNomeCliente.setEditable(true);
		tfEnderecoCliente.setEditable(true);
		tfTelefoneCliente.setEditable(true);
		tfCpfCliente.setEditable(true);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
			
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfCliente.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfCliente.setText(maskCpf.format(novoCpf));
		tfCpfCliente.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefoneCliente.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefoneCliente.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefoneCliente.setText(maskFixo.format(novoTelefone));
		}
		tfTelefoneCliente.positionCaret(novoTelefone.length()+4);
	}
	
	@FXML void updateTable() {
		Cliente c1[] = ClienteController.buscarCliente(tfPesquisarCliente.getText());
		tvCliente.getItems().clear();
        
        for(int i = 0; i < c1.length; i++) {
        	if(c1[i] != null)
        		tvCliente.getItems().add(c1[i]);
        }
	}
	
	@FXML void selecionarCliente() {
		if(!editando) {
			selecionado = tvCliente.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				tfNomeCliente.setText(selecionado.getNome());
				tfEnderecoCliente.setText(selecionado.getEndereco());
				tfTelefoneCliente.setText(selecionado.getTelefone());
				tfCpfCliente.setText(selecionado.getCpf());
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void excluirCliente() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir o produto?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(ClienteController.removerCliente(cEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Cliente excluido!");
    			aviso.showAndWait();
    			
    			tfNomeCliente.setText("");
    			tfEnderecoCliente.setText("");
    			tfTelefoneCliente.setText("");
    			tfCpfCliente.setText("");
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
        	}
        	else {
        		String msg = "Não Foi possível excluir o cliente, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void salvarCliente() {
		String nomeCliente = tfNomeCliente.getText();
		String enderecoCliente = tfEnderecoCliente.getText();
		String telefoneCliente = tfTelefoneCliente.getText();
		String cpfCliente = tfCpfCliente.getText();
		
		if(!ValidationController.isNome(nomeCliente)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do cliente.";
			dlgInvalido(msg);
			tfNomeCliente.setText("");
			tfNomeCliente.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoCliente)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoCliente.setText("");
			tfEnderecoCliente.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneCliente)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefoneCliente.setText("");
			tfTelefoneCliente.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfCliente)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfCliente.setText("");
			tfCpfCliente.requestFocus();
		}
				
		else {
			
			cEditar.setNome(nomeCliente);
			cEditar.setEndereco(enderecoCliente);
			cEditar.setTelefone(telefoneCliente);
			cEditar.setCpf(cpfCliente);
			
			Alert aviso = new Alert(Alert.AlertType.INFORMATION);
			aviso.setHeaderText(null);
			aviso.setTitle("Aviso");
			aviso.setContentText("Cliente atualizado com sucesso!.");
			aviso.showAndWait();
			lock();
			selecionarCliente();
			updateTable();
		}
	}
	
	@FXML void editarCliente() {
		unlock();
		if(selecionado != null)
			cEditar = selecionado;
	}
	
	@FXML void cancelarEdicaoCliente() {
		lock();
		selecionarCliente();
	}

	@FXML
    void initialize(){
    	lock();
    	btnEditar.setDisable(true);
    	
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
