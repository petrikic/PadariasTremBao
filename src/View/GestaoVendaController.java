package View;

import java.text.SimpleDateFormat;
import java.util.Optional;

import Controller.VendaController;
import Model.Venda;
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

public class GestaoVendaController {

	private boolean editando = false;
	private Venda selecionado;
	private Venda vEditar;
   	private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@FXML private TableView<Venda> tvVenda;
	@FXML TextField tfPesquisarVenda;
	@FXML TextField tfNomeCliente;
	@FXML TextField tfNomeVendedor;
	@FXML TextField tfFormaPagamento;
	@FXML TextField tfNumeroParcelas;
	@FXML TextField tfTotalCompra;
	@FXML TextField tfDataVenda;
	@FXML Button btnEditar;
	@FXML Button btnExcluir;
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
		tfNomeCliente.setEditable(false);
		tfNomeVendedor.setEditable(false);
		tfFormaPagamento.setEditable(false);
		tfNumeroParcelas.setEditable(false);
		tfDataVenda.setEditable(false);
		tfTotalCompra.setEditable(false);
		Image img = new Image("Img/lock.png");
		ivLock.setImage(img);
	}
	
	void unlock() {
		btnEditar.setDisable(true);
		btnCancelar.setDisable(false);
		btnExcluir.setDisable(false);
		Image img = new Image("Img/unlock.png");
		ivLock.setImage(img);
			
	}
	
	@FXML void editarVenda() {
		if(selecionado != null)
			unlock();
			vEditar = selecionado;
	}
	
	@FXML void cancelarEdicaoVenda() {
		lock();
		selecionarVenda();
	}
	
	@FXML void excluirVenda() {
		ButtonType btnSim = new ButtonType("Sim");
		ButtonType btnNao = new ButtonType("Não");
				
		Alert dlgExcluir = new Alert(Alert.AlertType.CONFIRMATION);
		dlgExcluir.getButtonTypes().setAll(btnSim, btnNao);
		dlgExcluir.setHeaderText(null);
		dlgExcluir.setTitle("Excluir");
		dlgExcluir.setContentText("Deseja realmente excluir a venda?");
        Optional<ButtonType> result = dlgExcluir.showAndWait();
        
        if(result.get() == btnSim) {
        	if(VendaController.deletarVenda(vEditar)) {
        		Alert aviso = new Alert(Alert.AlertType.INFORMATION);
    			aviso.setHeaderText(null);
    			aviso.setTitle("Aviso");
    			aviso.setContentText("Venda excluida!");
    			aviso.showAndWait();
    			
    			tfNomeCliente.setText("");
    			tfNomeVendedor.setText("");
    			tfFormaPagamento.setText("");
    			tfNumeroParcelas.setText("");
    			tfDataVenda.setText("");
    			tfTotalCompra.setText("");
    			lock();
    			updateTable();
    			btnEditar.setDisable(true);
    			tfNomeCliente.requestFocus();
        	}
        	else {
        		String msg = "Não Foi possível excluir a venda, tente novamente.";
        		dlgInvalido(msg);
        	}
        	
        }
	}
	
	@FXML void selecionarVenda() {
		if(!editando) {
			selecionado = tvVenda.getSelectionModel().getSelectedItem();
			if(selecionado != null) {
				tfNomeCliente.setText(selecionado.getCliente().getNome());
				tfNomeVendedor.setText(selecionado.getVendedor().getNome());
				tfFormaPagamento.setText(selecionado.getFormaPagamento());
				tfNumeroParcelas.setText(selecionado.getParcelas()+"");
				tfDataVenda.setText(df.format(selecionado.getData()));
				tfTotalCompra.setText(selecionado.getTotalVenda()+"");
				btnEditar.setDisable(false);
			}
		}
	}
	
	@FXML void updateTable() {
		Venda vd1[] = VendaController.buscarVenda(tfPesquisarVenda.getText());
		tvVenda.getItems().clear();
        
        for(int i = 0; i < vd1.length; i++) {
        	if(vd1[i] != null)
        		tvVenda.getItems().add(vd1[i]);
        }
	}

    @FXML
    void initialize(){
    	lock();
    	btnEditar.setDisable(true);
    	
        TableColumn<Venda,String> colNomeCliente = new TableColumn("Cliente");
        colNomeCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCliente().getNome()) );


        TableColumn<Venda,String> colNomeVendedor = new TableColumn("Vendedor");
        colNomeVendedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVendedor().getNome()));

        TableColumn<Venda,String> colFormaPagamento = new TableColumn("Forma de pagamento");
        colFormaPagamento.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFormaPagamento()));
        
        TableColumn<Venda,Integer> colNumeroParcelas = new TableColumn("Numero de parcelas");
        colNumeroParcelas.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getParcelas()).asObject());
        
        TableColumn<Venda, String> colData = new TableColumn<Venda, String>("Data da venda");
        colData.setCellValueFactory(data -> new SimpleStringProperty(df.format(data.getValue().getData())));
        
        TableColumn<Venda,Double> colTotalCompra = new TableColumn("Total");
        colTotalCompra.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getTotalVenda()).asObject());
        



        tvVenda.getColumns().addAll(colNomeCliente, colNomeVendedor, colFormaPagamento, colNumeroParcelas, colData, colTotalCompra);
        
        updateTable();
        }


}
