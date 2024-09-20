package service;

import com.google.gson.Gson;

import dao.UsuarioDAO;
import model.Usuario;
import java.io.IOException;
import spark.Request;
import spark.Response;

public class UsuarioService {
    
    private UsuarioDAO usuarioDAO;

    public UsuarioService (){
        try{
            this.usuarioDAO = new UsuarioDAO();
        }catch(Exception e){
            System.out.println("Erro ao criar o objeto DAO: " + e.getMessage());
        }
    }

    public Object add(Request request, Response response) {
        Gson gson = new Gson();

        Usuario registro = gson.fromJson(request.body(), Usuario.class);
       
        int id = this.usuarioDAO.getMaxId() + 1;  // Corrigido o getMaxId
        
        registro.setId(id);
        System.out.println(registro);

        usuarioDAO.inserirUsuario(registro);

        response.status(201); // 201 Created
        return id;
    }

    public Object get(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        Usuario usuario = usuarioDAO.get(id);

        if (usuario != null){
            Gson gson = new Gson();
            response.header("Content-Type", "application/json");
            response.header("Content-Encoding", "UTF-8");

            // Retorna o objeto Animal como JSON
            return gson.toJson(usuario);
        } else{
            response.status(404); // 404 Not Found
            return "Animal " + id + " não encontrado";
        }
    }

    public Object update(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));

        Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null){
            
            Gson gson = new Gson();

            Usuario registroAtualizado = gson.fromJson(request.body(), Usuario.class);

            // Atualizando os campos do objeto usuario
           
            usuario.setNome(registroAtualizado.getNome());
            usuario.setEmail(registroAtualizado.getEmail());
            usuario.setSenha(registroAtualizado.getSenha());
            usuario.setTags(registroAtualizado.getTags());
           

            usuarioDAO.atualizarUsuario(usuario);  // Usando o objeto `usuario` atualizado

            return id;

        } else{
            response.status(404); // 404 Not Found
            return "usuario não encontrado.";
        }
    }

    public Object remove(Request request, Response response){
        int id = Integer.parseInt(request.params(":id"));
        
        Usuario usuario = usuarioDAO.get(id);
        
        if (usuario != null){
            usuarioDAO.excluirUsuario(id);
            response.status(200); // Sucesso
            return id;
        }else{
            response.status(404); // 404 Not Found
            return "usuario não encontrado.";
        }
    }

    public Object getAll(Request request, Response response){
        Gson gson = new Gson();

        response.header("Content-type", "application/json");
        response.header("Content-Encoding", "UTF-8");

        // Retorna a lista de animais como JSON
        return gson.toJson(usuarioDAO.getUsuarios());
    }
    }