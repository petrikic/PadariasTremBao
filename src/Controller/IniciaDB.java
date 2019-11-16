package Controller;

import Model.Cliente;
import Model.Fornecedor;
import Model.Gerente;
import Model.Padeiro;
import Model.Produto;
import Model.ProdutoCatalogo;
import Model.Sacola;
import Model.Venda;
import Model.Vendedor;

public class IniciaDB {

	public static void iniciaDB() {	
		/*
			classe que possui infos que povoam os vetores de armazenamento
		*/

		SistemaController.setLimiteGold(2000);
		SistemaController.setLimitePlatinium(3500);
		Fornecedor f1 = new Fornecedor("Padarias Trem Bão", "Rua dos Limoeiros, 315 - Juazeiro, Toledo - PR", "12.412.779-0001-17"),
			  	   f2 = new Fornecedor("Frigosul Laticinios", "Rua dos Palmares, 1475 - Taquarussu, Navirai - MS", "12.412.774/0001-15"),
			  	   f3 = new Fornecedor("Doce vida Balas", "Rua dos Cafezais, 1177 - Piratininga, Campo Grande - MS", "12.412.478/0001-47"),
			  	   f4 = new Fornecedor("Walmart LTDA", "Av. Mato Grosso, 1959 - Cruzeiro, Campo Grande - MS", "78.954.612/3547-85"),
			  	   f5 = new Fornecedor("Supraleve Sorvetes", "Av. Mato Grosso, 1515 - Cruzeiro, Campo Grande - MS", "12.954.612/0001-85"),
			  	   f6 = new Fornecedor("Boolevard Beer", "Av. Noroeste, 110 - Centro, Campo Grande - MS", "75.558.612/1212-85"),
			  	   f7 = new Fornecedor("MasterFrig Frios", "Av. Nova Era, 1211 - Cruzeiro, Campo Grande - MS", "12.455.998/3547-85");
		
		FornecedorController.cadastrarFornecedor(f1);
		FornecedorController.cadastrarFornecedor(f2);
		FornecedorController.cadastrarFornecedor(f3);
		FornecedorController.cadastrarFornecedor(f4);
		FornecedorController.cadastrarFornecedor(f5);
		FornecedorController.cadastrarFornecedor(f6);
		FornecedorController.cadastrarFornecedor(f7);
		
		ProdutoCatalogo pb1 = new ProdutoCatalogo("Pão francês", "Cacetinho", "112778", f1, 0.5, true, 4),
						pb2 = new ProdutoCatalogo("Pão doce", "Cacetinho de mel", "123751", f1, 0.5, true, 4),
						pb3 = new ProdutoCatalogo("Pão de forma", "Cacetinho enformado", "287951", f1, 5, true, 7),
						pb4 = new ProdutoCatalogo("Pão de massa sovada", "Cacetinho surrado", "225842", f1, 5, true, 7),
						pb5 = new ProdutoCatalogo("Cueca virada", "Torce Torce", "654987", f1, 0.5, true, 4),
						pb6 = new ProdutoCatalogo("Bolo de chocolate", "Bolo de chocolate", "321456", f1, 10, true, 10),
						pb7 = new ProdutoCatalogo("Torta de banana", "Torta de banana", "127865", f1, 12, true, 10),
						pb8 = new ProdutoCatalogo("Brigadeiro", "Brigadeiro", "124578", f1, 3.5, true, 5),
						pb9 = new ProdutoCatalogo("Croissant de presunto", "Croissant de presunto", "126587", f1, 5, true, 3),
						pb10 = new ProdutoCatalogo("Leite desnatado", "Galeguinho", "321985", f2, 7.5, true, 7),
						pb11 = new ProdutoCatalogo("Iogurte", "Iogurte", "117454", f2, 8, true, 15),
						pb12 = new ProdutoCatalogo("Minas Frescal", "Queijo qualho", "325498", f2, 15, true, 20),
						pb13 = new ProdutoCatalogo("Requeijão", "Requeijão", "325498", f2, 8, true, 20),
						pb14 = new ProdutoCatalogo("Manteiga", "Manteiga", "325499", f2, 5, true, 30),
						pb15 = new ProdutoCatalogo("Cocada", "Cocada", "124579", f3, 5, false, 60),
						pb16 = new ProdutoCatalogo("Bala soft", "Bala soft", "124580", f3, 0.20, false, 365),
						pb17 = new ProdutoCatalogo("Barra de chocolate", "Barra de chocolate", "151010", f3, 5, false, 20),
						pb18 = new ProdutoCatalogo("Presunto fatiado", "Presunto fatiado", "151011", f7, 8, true, 7),
						pb19 = new ProdutoCatalogo("Salame", "Salame", "174568", f7, 8, true, 7),
						pb20 = new ProdutoCatalogo("Óleo", "Óleo", "174595", f4, 3.8, false, 90),
						pb21 = new ProdutoCatalogo("Açucar", "Açucar", "174569", f7, 6, false, 90),
						pb22 = new ProdutoCatalogo("Café", "Café", "174570", f7, 8, false, 90);
		
		ProdutoController.cadastraProduto(pb1);
		ProdutoController.cadastraProduto(pb2);
		ProdutoController.cadastraProduto(pb3);
		ProdutoController.cadastraProduto(pb4);
		ProdutoController.cadastraProduto(pb5);
		ProdutoController.cadastraProduto(pb6);
		ProdutoController.cadastraProduto(pb7);
		ProdutoController.cadastraProduto(pb8);
		ProdutoController.cadastraProduto(pb9);
		ProdutoController.cadastraProduto(pb10);
		ProdutoController.cadastraProduto(pb11);
		ProdutoController.cadastraProduto(pb12);
		ProdutoController.cadastraProduto(pb13);
		ProdutoController.cadastraProduto(pb14);
		ProdutoController.cadastraProduto(pb16);
		ProdutoController.cadastraProduto(pb17);
		ProdutoController.cadastraProduto(pb18);
		ProdutoController.cadastraProduto(pb19);
		ProdutoController.cadastraProduto(pb20);
		ProdutoController.cadastraProduto(pb21);
		ProdutoController.cadastraProduto(pb22);
		
		
		for(int i = 0; i < 12; i++) {
			ProdutoController.adicionaProduto(pb1);
			ProdutoController.adicionaProduto(pb2);
			ProdutoController.adicionaProduto(pb3);
			ProdutoController.adicionaProduto(pb4);
			ProdutoController.adicionaProduto(pb5);
			ProdutoController.adicionaProduto(pb6);
			ProdutoController.adicionaProduto(pb7);
			ProdutoController.adicionaProduto(pb8);
			ProdutoController.adicionaProduto(pb9);
			ProdutoController.adicionaProduto(pb10);
		}
		
		for(int i = 0; i < 8; i++) {
			ProdutoController.adicionaProduto(pb11);
			ProdutoController.adicionaProduto(pb12);
			ProdutoController.adicionaProduto(pb13);
			ProdutoController.adicionaProduto(pb14);
			ProdutoController.adicionaProduto(pb16);
			ProdutoController.adicionaProduto(pb17);
			ProdutoController.adicionaProduto(pb18);
			ProdutoController.adicionaProduto(pb19);
			ProdutoController.adicionaProduto(pb20);
			ProdutoController.adicionaProduto(pb21);
			ProdutoController.adicionaProduto(pb22);
		}
		
		
		Cliente c1 = new Cliente("Lucas Costa", "Rua da festa, 475", "(67)3325-7715", "779.557.443-20"),
				c2 = new Cliente("Lucas Silva", "Rua do arduino, 455", "(67)3325-1313", "779.557.222-20"),
				c3 = new Cliente("Matheus Lima", "Rua dos alfeneiros, 2", "(67)99917-7715", "222.222.222-20");
		ClienteController.cadastrarCliente(c1);
		ClienteController.cadastrarCliente(c2);
		ClienteController.cadastrarCliente(c3);
		
		Vendedor v1 = new Vendedor("Yudi Tamashiro", "Rua dos playstation, 777 - São Paulo - SP", "(11)4002-8922", "112.123.654-20", 2000, FuncionariosController.getCodigoFuncionario(), 20),
				v2 = new Vendedor("Joseph Thaliban", "Rua do assalto, 252", "(67)3317-4044", "177.488.222-20", 1200, FuncionariosController.getCodigoFuncionario(), 20),
				v3 = new Vendedor("Luan Lima", "Rua dos batatais, 1", "(67)99917-4451", "789.789.222-20", 1200, FuncionariosController.getCodigoFuncionario(), 20);
		FuncionariosController.cadastrarVendedor(v1);
		FuncionariosController.cadastrarVendedor(v2);
		FuncionariosController.cadastrarVendedor(v3);
		
		Gerente g1 = new Gerente("Luiz Barbosa", "Av. Afonso Pena, 1305 - Campo Grande - MS", "(67)99955-3308", "066.335.779-20", 3000, FuncionariosController.getCodigoFuncionario());
		FuncionariosController.cadastrarGerente(g1);
		
		Padeiro p1 = new Padeiro("Jonathan Fonseca", "Av. Afonso Pena, 1245 - Campo Grande - MS", "(67)99977-3399", "066.335.444-20", 1400, FuncionariosController.getCodigoFuncionario());
		p1.adicionaHorasExtras(10);
		Padeiro p2 = new Padeiro("Moisés da Cunha", "Rua dos Coqueiros, 245 - Campo Grande - MS", "(67)98477-3579", "555.335.444-20", 1400, FuncionariosController.getCodigoFuncionario());
		p2.adicionaHorasExtras(4);
		Padeiro p3 = new Padeiro("Luan Brito", "Rua nogueira silva, 445", "(67)3325-1245", "852.557.444-20", 1200, FuncionariosController.getCodigoFuncionario());
		
		FuncionariosController.cadastrarPadeiro(p1);
		FuncionariosController.cadastrarPadeiro(p2);
		FuncionariosController.cadastrarPadeiro(p3);
		
		Sacola s1 = new Sacola();
		Produto pdt1 = ProdutoController.buscarProduto("Cocada")[0];
		Produto pdt2 = ProdutoController.buscarProduto("Pão doce")[0];
		Produto pdt3 = ProdutoController.buscarProduto("Bala soft")[0];
		Produto pdt4 = ProdutoController.buscarProduto("Iogurte")[0];
		s1.addProduto(pdt1);
		s1.addProduto(pdt2);
		s1.addProduto(pdt3);
		s1.addProduto(pdt4);
		
		
		for(int i = 0; i < 10; i++) {
			Venda venda1 = new Venda("Dinheiro", 1, s1, 2000, v1, c1);
			VendaController.novaVenda(venda1);
		}
		
		c1.acumulaValorDasCompras(3000);
		for(int i = 0; i < 30; i++) {
			v1.efetuaVenda();
		}
		
		
		
	}
}
