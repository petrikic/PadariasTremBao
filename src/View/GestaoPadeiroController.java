package View;

import java.util.Optional;
import Controller.FuncionariosController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Padeiro;
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

public class GestaoPadeiroController {

	private boolean editando = false;
	private Padeiro selecionado;
	private Padeiro pEditar;

	@FXML private TableView<Padeiro> tvPadeiro;
	@FXML TextField tfPesquisarPadeiro;
	@FXML TextField tfNomePadeiro;
	@FXML TextField tfEnderecoPadeiro;
	@FXML TextField tfTelefonePadeiro;
	@FXML TextField tfCpfPadeiro;
	@FXML TextField tfSalarioBasePadeiro;
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
		tfNomePadeiro.setEditable(false);
		tfNomePadeiro.setEditable(false);
		tfTelefonePadeiro.setEditable(false);
		tfCpfPadeiro.setEditable(false);
		tfSalarioBasePadeiro.setEditable(false);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		editando = true;
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnSalvar.setDisable(false);
		tfNomePadeiro.setEditable(true);
		tfEnderecoPadeiro.setEditable(true);
		tfTelefonePadeiro.setEditable(true);
		tfCpfPadeiro.setEditable(true);
		tfSalarioBasePadeiro.setEditable(false);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
			
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfPadeiro.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfPadeiro.setText(maskCpf.format(novoCpf));
		tfCpfPadeiro.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefonePadeiro.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefonePadeiro.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefonePadeiro.setText(maskFixo.format(novoTelefone));
		}
		tfTelefonePadeiro.positionCaret(novoTelefone.length()+4);
	}
	
	@FXML void updateTable() {
		Padeiro p1[] = FuncionariosController.buscarPadeiro(tfPesquisarPadeiro.getText());
		tvPadeiro.getItems().clear();
        
        for(int i = 0; i < p1.length; i++) {
        	if(p1[i] != null)
        		tvPadeiro.getItems().add(p1[i]);
        }
	}
	
	@FXML void selecionarPadeiro() {
		if(!editando) {
			selecionado = tvPadeiro.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				tfNomePadeiro.setText(selecionado.getNome());
				tfEnderecoPadeiro.setText(selecionado.getEndereco());
				tfTelefonePadeiro.setText(selecionado.getTelefone());
				tfCpfPadeiro.setText(selecionado.getCpf());
				tfSalarioBasePadeiro.setText(selecionado.getSalarioBase()+"");
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void excluirPadeiro() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir o padeiro?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(FuncionariosController.removerPadeiro(pEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Padeiro excluido!");
    			aviso.showAndWait();
    			
    			tfNomePadeiro.setText("");
    			tfEnderecoPadeiro.setText("");
    			tfTelefonePadeiro.setText("");
    			tfCpfPadeiro.setText("");
    			tfSalarioBasePadeiro.setText("");
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
        	}
        	else {
        		String msg = "Não Foi possível excluir o padeiro, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void salvarPadeiro() {
		String nomeGerente = tfNomePadeiro.getText();
		String enderecoGerente = tfEnderecoPadeiro.getText();
		String telefoneGerente = tfTelefonePadeiro.getText();
		String cpfGerente = tfCpfPadeiro.getText();
		String salarioBaseGerente = tfSalarioBasePadeiro.getText();
		
		if(!ValidationController.isNome(nomeGerente)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do padeiro.";
			dlgInvalido(msg);
			tfNomePadeiro.setText("");
			tfNomePadeiro.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoGerente)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoPadeiro.setText("");
			tfEnderecoPadeiro.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneGerente)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefonePadeiro.setText("");
			tfTelefonePadeiro.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfGerente)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfPadeiro.setText("");
			tfCpfPadeiro.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(salarioBaseGerente)) {
			String msg = "Você digitou um salario inválido. Digite novamente.";
			dlgInvalido(msg);
			tfSalarioBasePadeiro.setText("");
			tfSalarioBasePadeiro.requestFocus();
		}
		else {
			double salarioBase = Double.parseDouble(salarioBaseGerente);
			
			pEditar.setNome(nomeGerente);
			pEditar.setEndereco(enderecoGerente);
			pEditar.setTelefone(telefoneGerente);
			pEditar.setCpf(cpfGerente);
			pEditar.setSalarioBase(salarioBase);
			
			Alert aviso = new Alert(Alert.AlertType.INFORMATION);
			aviso.setHeaderText(null);
			aviso.setTitle("Aviso");
			aviso.setContentText("Padeiro atualizado com sucesso!.");
			aviso.showAndWait();
			lock();
			selecionarPadeiro();
			updateTable();
		}
	}
	
	@FXML void editarPadeiro() {
		unlock();
		if(selecionado != null)
			pEditar = selecionado;
	}
	
	@FXML void cancelarEdicaoPadeiro() {
		lock();
		selecionarPadeiro();
	}

	@FXML
    void initialize(){
    	lock();
    	btnEditar.setDisable(true);
    	
        TableColumn<Padeiro,String> colNome = new TableColumn<Padeiro, String>("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Padeiro,String> colEndereco = new TableColumn<Padeiro, String>("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Padeiro,String> colTelefone = new TableColumn<Padeiro, String>("Telefone");
        colTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));
        
        TableColumn<Padeiro,String> colCpf = new TableColumn<Padeiro, String>("CPF");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        TableColumn<Padeiro, Double> colSalarioBase = new TableColumn<Padeiro, Double>("Salário base");
        colSalarioBase.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSalarioBase()).asObject());
        
		

        tvPadeiro.getColumns().addAll(colNome, colEndereco, colTelefone, colCpf, colSalarioBase);
        
        updateTable();
        }

}
