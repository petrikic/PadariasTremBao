package View;

import Controller.FuncionariosController;
import Controller.VendaController;
import Model.Gerente;
import Model.Padeiro;
import Model.Venda;
import Model.Vendedor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GestaoImpostoController {
	
	double totalImposto;
	double salarioImposto = 0;
	double vendaImposto = 0;
	
	@FXML Label lblTotaImposto;
	@FXML Label lblImpostoFuncionario;
	@FXML Label lblImpostoVenda;

	@FXML
    void initialize(){
		Vendedor v1[] = FuncionariosController.buscarVendedor("");
		Gerente g1[] = FuncionariosController.buscarGerente("");
		Padeiro p1[] = FuncionariosController.buscarPadeiro("");
		Venda vd1[] = VendaController.buscarVenda("");
		
		for(int i = 0; i < v1.length; i++) {
			if(v1[i] != null)
				salarioImposto += v1[i].calculaImposto();
		}

		for(int i = 0; i < g1.length; i++) {
        	if(g1[i] != null)
        		salarioImposto += g1[i].calculaImposto();
        }
        
        for(int i = 0; i < p1.length; i++) {
        	if(p1[i] != null)
        		salarioImposto += p1[i].calculaImposto();
        }
        
        for(int i = 0; i < vd1.length; i++) {
        	if(vd1[i] != null)
        		vendaImposto += vd1[i].calculaImposto();
        }
        
        totalImposto = vendaImposto + salarioImposto;
        
        lblTotaImposto.setText("Este mês, você deve pagar um montante de "+totalImposto);
        lblImpostoFuncionario.setText("Sendo " + salarioImposto+" sobre o salário dos funcionários,");
        lblImpostoVenda.setText("e " +vendaImposto+" sobre as vendas realizadas.");
        
		
		
	}

}
