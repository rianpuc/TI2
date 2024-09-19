package service;

import java.util.Scanner;
import java.io.File;
import java.util.List;
import dao.ProdutoDAO;
import model.Sorvete;
import spark.Request;
import spark.Response;


public class ProdutoService {

	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_SABOR = 1;
	private final int FORM_ORDERBY_PRECO = 2;
	private final int FORM_ORDERBY_QUANTIDADE = 3;
	
	
	public ProdutoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Sorvete(), FORM_ORDERBY_SABOR);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Sorvete(), orderBy);
	}

	
	public void makeForm(int tipo, Sorvete produto, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umProduto = "";
		if(tipo != FORM_INSERT) {
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/sorvetes/list/1\">Novo Produto</a></b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";
			umProduto += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/sorvetes/";
			String name, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Sorvete";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + produto.getSabor();
				name = "Atualizar Sorvete (Sabor " + produto.getSabor() + ")";
				buttonLabel = "Atualizar";
			}
			umProduto += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>Sabor: <input class=\"input--register\" type=\"text\" name=\"sabor\" value=\""+ produto.getSabor() +"\"></td>";
			umProduto += "\t\t\t<td>Preco: <input class=\"input--register\" type=\"text\" name=\"preco\" value=\""+ produto.getPreco() +"\"></td>";
			umProduto += "\t\t\t<td>Quantidade: <input class=\"input--register\" type=\"text\" name=\"quantidade\" value=\""+ produto.getQuantidade() +"\"></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";
			umProduto += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umProduto += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Sorvete (Sabor: " + produto.getSabor() + ")</b></font></td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>Preco: "+ produto.getPreco() +"</td>";
			umProduto += "\t\t\t<td>Quantidade: "+ produto.getQuantidade() +"</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t\t<tr>";
			umProduto += "\t\t\t<td>&nbsp;</td>";
			umProduto += "\t\t</tr>";
			umProduto += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-PRODUTO>", umProduto);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Sorvetes</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/sorvetes/list/" + FORM_ORDERBY_SABOR + "\"><b>Sabor</b></a></td>\n" +
        		"\t<td><a href=\"/sorvetes/list/" + FORM_ORDERBY_PRECO + "\"><b>Preço</b></a></td>\n" +
        		"\t<td><a href=\"/sorvetes/list/" + FORM_ORDERBY_QUANTIDADE + "\"><b>Quantidade</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Sorvete> produtos;
		if (orderBy == FORM_ORDERBY_SABOR) {                 	produtos = produtoDAO.getOrderBySabor();
		} else if (orderBy == FORM_ORDERBY_PRECO) {		produtos = produtoDAO.getOrderByPreco();
		} else if (orderBy == FORM_ORDERBY_QUANTIDADE) {			produtos = produtoDAO.getOrderByQuantidade();
		} else {											produtos = produtoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Sorvete p : produtos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getSabor() + "</td>\n" +
            		  "\t<td>" + p.getPreco() + "</td>\n" +
            		  "\t<td>" + p.getQuantidade() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/sorvetes/" + p.getSabor() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/sorvetes/update/" + p.getSabor() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteProduto('" + p.getSabor() + "', '" + p.getPreco() + "', '" + p.getQuantidade() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-PRODUTO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
		String sabor = request.queryParams("sabor");
		double preco = Float.parseFloat(request.queryParams("preco"));
		int quantidade = Integer.parseInt(request.queryParams("quantidade"));
		
		String resp = "";
		
		Sorvete produto = new Sorvete(sabor, preco, quantidade);
		
		if(produtoDAO.insert(produto) == true) {
            resp = "Sorvete de (" + sabor + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Sorvete de (" + sabor + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		String id = request.params(":sabor");		
		Sorvete produto = (Sorvete) produtoDAO.getSingle(id);
		
		if (produto != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, produto, FORM_ORDERBY_QUANTIDADE);
        } else {
            response.status(404); // 404 Not found
            String resp = "Produto " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		String id = request.params(":sabor");		
		Sorvete produto = produtoDAO.getSingle(id);
		
		if (produto != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, produto, FORM_ORDERBY_QUANTIDADE);
        } else {
            response.status(404); // 404 Not found
            String resp = "Produto " + id + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        String id = request.params("sabor");
		Sorvete produto = produtoDAO.getSingle(id);
        String resp = "";       

        if (produto != null) {
        	produto.setSabor(request.queryParams("sabor"));
        	produto.setPreco(Float.parseFloat(request.queryParams("preco")));
        	produto.setQuantidade(Integer.parseInt(request.queryParams("quantidade")));
        	produtoDAO.update(produto);
        	response.status(200); // success
            resp = "Produto (ID " + produto.getSabor() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Produto (ID \" + produto.getSabor() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        String id = request.params(":sabor");
        Sorvete produto = produtoDAO.getSingle(id);
        String resp = "";       
        if (produto != null) {
            produtoDAO.delete(id);
            response.status(200); // success
            resp = "Produto (" + id + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Produto (" + id + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}