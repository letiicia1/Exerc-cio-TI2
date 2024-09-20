package app;

import service.UsuarioService;
import static spark.Spark.*;


public class AplicacaoUsuario {

    private static UsuarioService aplicacaousuario = new UsuarioService();

    public static void main(String[] args) {
        
        staticFileLocation("/public");//setando a pasta padrão do arquivo
        port(8081);

        //Redireciona para o arquivo HTML quando acessar a raiz
        get("/", (request, response) -> {
            response.redirect("/modules/TelaCadastroAnimalONG/teladecadastrodeanimaONG.html");
            return null;
        });


        post("/animal", (request, response) -> aplicacaousuario.add(request, response));

        get("/animal/:id", (request, response) -> aplicacaousuario.get(request, response));

        get("/animal/update/:id", (request, response) -> aplicacaousuario.update(request, response));

        post("/animal/delete/:id", (request, response) -> aplicacaousuario.remove(request, response));
    
        get("/animal", (request, response) -> aplicacaousuario.getAll(request, response));
       
        
    }
}

