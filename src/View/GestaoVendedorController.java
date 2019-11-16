package View;

import java.util.Optional;

import Controller.FuncionariosController;
import Controller.MaskField;
import Controller.ValidationController;
import Model.Vendedor;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

public class GestaoVendedorController {

	private boolean editando = false;
	private Vendedor selecionado;
	private Vendedor vEditar;

	@FXML private TableView<Vendedor> tvVendedor;
	@FXML TextField tfPesquisarVendedor;
	@FXML TextField tfNomeVendedor;
	@FXML TextField tfEnderecoVendedor;
	@FXML TextField tfTelefoneVendedor;
	@FXML TextField tfCpfVendedor;
	@FXML TextField tfSalarioBaseVendedor;
	@FXML TextField tfMetaVendedor;
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
		tfNomeVendedor.setEditable(false);
		tfNomeVendedor.setEditable(false);
		tfTelefoneVendedor.setEditable(false);
		tfCpfVendedor.setEditable(false);
		tfSalarioBaseVendedor.setEditable(false);
		tfMetaVendedor.setEditable(false);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		editando = true;
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		btnSalvar.setDisable(false);
		tfNomeVendedor.setEditable(true);
		tfEnderecoVendedor.setEditable(true);
		tfTelefoneVendedor.setEditable(true);
		tfCpfVendedor.setEditable(true);
		tfSalarioBaseVendedor.setEditable(false);
		tfMetaVendedor.setEditable(false);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
			
	}
	
	@FXML void limitaCpf() {
		String novoCpf = tfCpfVendedor.getText();
		novoCpf = novoCpf.replace(".", "");
		novoCpf = novoCpf.replace("-","");
		if(novoCpf.length() > 11) {
			novoCpf = novoCpf.substring(0,11);
		}
		MaskField maskCpf = new MaskField("###.###.###-##");
		tfCpfVendedor.setText(maskCpf.format(novoCpf));
		tfCpfVendedor.positionCaret(novoCpf.length()+4);
			
	}
	
	@FXML void limitaTelefone() {
		String novoTelefone = tfTelefoneVendedor.getText();
		novoTelefone = novoTelefone.replace("(", "");
		novoTelefone = novoTelefone.replace(")", "");
		novoTelefone = novoTelefone.replace("-","");
		
		MaskField maskFixo = new MaskField("(##)####-####");
		MaskField maskCelular = new MaskField("(##)#####-####");
		if(novoTelefone.length() > 11) {
			novoTelefone = novoTelefone.substring(0,11);
		}
		if(novoTelefone.length() == 11) {
			tfTelefoneVendedor.setText(maskCelular.format(novoTelefone));
		}
		else {
			tfTelefoneVendedor.setText(maskFixo.format(novoTelefone));
		}
		tfTelefoneVendedor.positionCaret(novoTelefone.length()+4);
	}
	
	@FXML void updateTable() {
		Vendedor v1[] = FuncionariosController.buscarVendedor(tfPesquisarVendedor.getText());
		tvVendedor.getItems().clear();
        
        for(int i = 0; i < v1.length; i++) {
        	if(v1[i] != null)
        		tvVendedor.getItems().add(v1[i]);
        }
	}
	
	@FXML void selecionarVendedor() {
		if(!editando) {
			selecionado = tvVendedor.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				tfNomeVendedor.setText(selecionado.getNome());
				tfEnderecoVendedor.setText(selecionado.getEndereco());
				tfTelefoneVendedor.setText(selecionado.getTelefone());
				tfCpfVendedor.setText(selecionado.getCpf());
				tfSalarioBaseVendedor.setText(selecionado.getSalarioBase()+"");
				tfMetaVendedor.setText(selecionado.getMetaDeVenda()+"");
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void excluirVendedor() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir o vendedor?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(FuncionariosController.removerVendedor(vEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Vendedor excluido!");
    			aviso.showAndWait();
    			
    			tfNomeVendedor.setText("");
    			tfEnderecoVendedor.setText("");
    			tfTelefoneVendedor.setText("");
    			tfCpfVendedor.setText("");
    			tfSalarioBaseVendedor.setText("");
    			tfMetaVendedor.setText("");
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
        	}
        	else {
        		String msg = "Não Foi possível excluir o vendedor, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void salvarVendedor() {
		String nomeVendedor = tfNomeVendedor.getText();
		String enderecoVendedor = tfEnderecoVendedor.getText();
		String telefoneVendedor = tfTelefoneVendedor.getText();
		String cpfVendedor = tfCpfVendedor.getText();
		String salarioBaseVendedor = tfSalarioBaseVendedor.getText();
		String metaVendedor = tfMetaVendedor.getText();
		
		if(!ValidationController.isNome(nomeVendedor)){
			String msg = "Você digitou um nome inválido. Digite novamente o nome do vendedor.";
			dlgInvalido(msg);
			tfNomeVendedor.setText("");
			tfNomeVendedor.requestFocus();
		}
		else if(!ValidationController.isEndereco(enderecoVendedor)) {
			String msg = "O endereço digitado é muito curto. Digite um endereço com mais de 10 letras.";
			dlgInvalido(msg);
			tfEnderecoVendedor.setText("");
			tfEnderecoVendedor.requestFocus();
		}
		else if(!ValidationController.isTelefone(telefoneVendedor)) {
			String msg = "O telefone digitado não é valido. Digite novamente o telefone.";
			dlgInvalido(msg);
			tfTelefoneVendedor.setText("");
			tfTelefoneVendedor.requestFocus();
		}
		else if(!ValidationController.isCpf(cpfVendedor)) {
			String msg = "Você digitou um CPF Inválido. Digite o CPF novamente.";
			dlgInvalido(msg);
			tfCpfVendedor.setText("");
			tfCpfVendedor.requestFocus();
		}
		else if(!ValidationController.isOnlyDigit(metaVendedor)) {
			String msg = "Você digitou uma meta inválida. Digite uma meta novamente.";
			dlgInvalido(msg);
			tfMetaVendedor.setText("");
			tfMetaVendedor.requestFocus();
		}
		else if(!ValidationController.isStringDecimal(salarioBaseVendedor)) {
			String msg = "Você digitou um salario inválido. Digite novamente.";
			dlgInvalido(msg);
			tfSalarioBaseVendedor.setText("");
			tfSalarioBaseVendedor.requestFocus();
		}
		else {
			double salarioBase = Double.parseDouble(salarioBaseVendedor);
			int metaDeVenda = Integer.parseInt(metaVendedor);
			
			vEditar.setNome(nomeVendedor);
			vEditar.setEndereco(enderecoVendedor);
			vEditar.setTelefone(telefoneVendedor);
			vEditar.setCpf(cpfVendedor);
			vEditar.setSalarioBase(salarioBase);
			vEditar.setMetaDeVenda(metaDeVenda);
			
			Alert aviso = new Alert(Alert.AlertType.INFORMATION);
			aviso.setHeaderText(null);
			aviso.setTitle("Aviso");
			aviso.setContentText("Vendedor atualizado com sucesso!.");
			aviso.showAndWait();
			lock();
			selecionarVendedor();
			updateTable();
		}
	}
	
	@FXML void editarVendedor() {
		unlock();
		if(selecionado != null)
			vEditar = selecionado;
	}
	
	@FXML void cancelarEdicaoVendedor() {
		lock();
		selecionarVendedor();
	}

	@FXML
    void initialize(){
    	lock();
    	btnEditar.setDisable(true);
    	
        TableColumn<Vendedor,String> colNome = new TableColumn("Nome");
        colNome.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()) );


        TableColumn<Vendedor,String> colEndereco = new TableColumn("Endereço");
        colEndereco.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndereco()));

        TableColumn<Vendedor,String> colTelefone = new TableColumn("Telefone");
        colTelefone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefone()));
        
        TableColumn<Vendedor,String> colCpf = new TableColumn("CPF");
        colCpf.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCpf()));
        
        TableColumn<Vendedor, Double> colSalarioBase = new TableColumn<Vendedor, Double>("Salário base");
        colSalarioBase.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getSalarioBase()).asObject());
        
        TableColumn<Vendedor, Integer> colMetaVendas = new TableColumn<Vendedor, Integer>("Meta de vendas");
        colMetaVendas.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMetaDeVenda()).asObject());
        
        
		

        tvVendedor.getColumns().addAll(colNome, colEndereco, colTelefone, colCpf, colSalarioBase, colMetaVendas);
        
        updateTable();
        }

}
