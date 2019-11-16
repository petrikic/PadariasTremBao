package View;

import java.util.Optional;

import Controller.FuncionariosController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Gerente;
import javafx.beans.property.SimpleDoubleProperty;
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

public class GestaoGerenteController {

	private boolean editando = false;
	private Gerente selecionado;
	private Gerente gEditar;

	@FXML private TableView<Gerente> tvGerente;
	@FXML TextField tfPesquisarGerente;
	@FXML TextField tfNomeGerente;
	@FXML TextField tfEnderecoGerente;
	@FXML TextField tfTelefoneGerente;
	@FXML TextField tfCpfGerente;
	@FXML TextField tfSalarioBaseGerente;
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
		tfNomeGerente.setEditable(false);
		tfNomeGerente.setEditable(false);
		tfTelefoneGerente.setEditable(false);
		tfCpfGerente.setEditable(false);
		tfSalarioBaseGerente.setEditable(false);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		editando = true;
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnSalvar.setDisable(false);
		tfNomeGerente.setEditable(true);
		tfEnderecoGerente.setEditable(true);
		tfTelefoneGerente.setEditable(true);
		tfCpfGerente.setEditable(true);
		tfSalarioBaseGerente.setEditable(false);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
			
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfGerente.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfGerente.setText(maskCpf.format(novoCpf));
		tfCpfGerente.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefoneGerente.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefoneGerente.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefoneGerente.setText(maskFixo.format(novoTelefone));
		}
		tfTelefoneGerente.positionCaret(novoTelefone.length()+4);
	}
	
	@FXML void updateTable() {
		Gerente g1[] = FuncionariosController.buscarGerente(tfPesquisarGerente.getText());
		tvGerente.getItems().clear();
        
        for(int i = 0; i < g1.length; i++) {
        	if(g1[i] != null)
        		tvGerente.getItems().add(g1[i]);
        }
	}
	
	@FXML void selecionarGerente() {
		if(!editando) {
			selecionado = tvGerente.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				tfNomeGerente.setText(selecionado.getNome());
				tfEnderecoGerente.setText(selecionado.getEndereco());
				tfTelefoneGerente.setText(selecionado.getTelefone());
				tfCpfGerente.setText(selecionado.getCpf());
				tfSalarioBaseGerente.setText(selecionado.getSalarioBase()+"");
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void excluirGerente() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir o gerente?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(FuncionariosController.removerGerente(gEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Gerente excluido!");
    			aviso.showAndWait();
    			
    			tfNomeGerente.setText("");
    			tfEnderecoGerente.setText("");
    			tfTelefoneGerente.setText("");
    			tfCpfGerente.setText("");
    			tfSalarioBaseGerente.setText("");
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
        	}
        	else {
        		String msg = "Não Foi possível excluir o gerente, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void salvarGerente() {
		String nomeGerente = tfNomeGerente.getText();
		String enderecoGerente = tfEnderecoGerente.getText();
		String telefoneGerente = tfTelefoneGerente.getText();
		String cpfGerente = tfCpfGerente.getText();
		String salarioBaseGerente = tfSalarioBaseGerente.getText();
		
		if(!ValidationController.isNome(nomeGerente)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do vendedor.";
			dlgInvalido(msg);
			tfNomeGerente.setText("");
			tfNomeGerente.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoGerente)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoGerente.setText("");
			tfEnderecoGerente.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneGerente)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefoneGerente.setText("");
			tfTelefoneGerente.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfGerente)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfGerente.setText("");
			tfCpfGerente.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(salarioBaseGerente)) {
			String msg = "Você digitou um salario inválido. Digite novamente.";
			dlgInvalido(msg);
			tfSalarioBaseGerente.setText("");
			tfSalarioBaseGerente.requestFocus();
		}
		else {
			double salarioBase = Double.parseDouble(salarioBaseGerente);
			
			gEditar.setNome(nomeGerente);
			gEditar.setEndereco(enderecoGerente);
			gEditar.setTelefone(telefoneGerente);
			gEditar.setCpf(cpfGerente);
			gEditar.setSalarioBase(salarioBase);
			
			Alert aviso = new Alert(Alert.AlertType.INFORMATION);
			aviso.setHeaderText(null);
			aviso.setTitle("Aviso");
			aviso.setContentText("Vendedor atualizado com sucesso!.");
			aviso.showAndWait();
			lock();
			selecionarGerente();
			updateTable();
		}
	}
	
	@FXML void editarGerente() {
		unlock();
		if(selecionado != null)
			gEditar = selecionado;
	}
	
	@FXML void cancelarEdicaoGerente() {
		lock();
		selecionarGerente();
	}

	@FXML
    void initialize(){
    	lock();
    	btnEditar.setDisable(true);
    	
        TableColumn<Gerente,String> colNome = new TableColumn<Gerente, String>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Gerente,String> colEndereco = new TableColumn<Gerente, String>("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Gerente,String> colTelefone = new TableColumn<Gerente, String>("Telefone");
        colTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));
        
        TableColumn<Gerente,String> colCpf = new TableColumn<Gerente, String>("CPF");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        TableColumn<Gerente, Double> colSalarioBase = new TableColumn<Gerente, Double>("Salário base");
        colSalarioBase.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSalarioBase()).asObject());
        
		

        tvGerente.getColumns().addAll(colNome, colEndereco, colTelefone, colCpf, colSalarioBase);
        
        updateTable();
        }

}
