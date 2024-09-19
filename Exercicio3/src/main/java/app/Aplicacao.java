package app;

import static spark.Spark.*;
import service.ProdutoService;


public class Aplicacao {
	
	private static ProdutoService produtoService = new ProdutoService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/sorvetes/insert", (request, response) -> produtoService.insert(request, response));

        get("/sorvetes/:sabor", (request, response) -> produtoService.get(request, response));
        
        get("/sorvetes/list/:orderby", (request, response) -> produtoService.getAll(request, response));

        get("/sorvetes/update/:sabor", (request, response) -> produtoService.getToUpdate(request, response));
        
        post("/sorvetes/update/:sabor", (request, response) -> produtoService.update(request, response));
           
        get("/sorvetes/delete/:sabor", (request, response) -> produtoService.delete(request, response));

             
    }
}