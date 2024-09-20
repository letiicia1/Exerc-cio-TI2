
// Source code is decompiled from a .class file using FernFlower decompiler.
package dao;

import model.*;

import java.sql.*;

public class UsuarioDAO extends DAO {
    

    public UsuarioDAO() {
        this.conectar();
     }
  
     public void finalize() {
        this.close();
     }

    private int maxId = 0;

    //Retorna o id do ultimo usuario inserido no banco de dados
    public int getMaxId() {
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT MAX(id) as maxId FROM Usuario"; // Alias para facilitar a leitura do valor
    
            ResultSet rs = st.executeQuery(sql); // Usa executeQuery para comandos SELECT
            if (rs.next()) { // Verifica se o resultado contém ao menos uma linha
                maxId = rs.getInt("maxId"); // Obtem o valor usando o alias
            }

            rs.close(); // Fecha o ResultSet
            st.close(); // Fecha o Statement
        } catch (SQLException u) {
            u.printStackTrace();
        }
        
        return maxId; // Retorna o valor de maxId
    }

    

    
     
    //Inseri um usuario no banco de dados
    public boolean inserirUsuario(Usuario usuario){
        boolean status = false;
        try {
            this.maxId = (usuario.getId() > this.maxId) ? usuario.getId() : this.maxId;
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO usuario (id, url, nome, sexo, idade, raca, vacinas, cadastrado, historia, tags, porte, especie, cidade) " +
                           "VALUES (" +
                           usuario.getId() + ", '" +
                           usuario.getNome() + "', '" +
                           usuario.getEmail() + "', '" +
                           usuario.getSenha() + "', '" +
                           usuario.getTags() + "')";

            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            u.printStackTrace();
        }
        return status;
    }   

    //Atualiza o usuario pertencente ao id do seu objeto
    public boolean atualizarUsuario(Usuario usuario) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE usuario SET " +
                         
                         "nome = '" + usuario.getNome() + "', " +
                         "email = '" + usuario.getEmail() + "', " +
                         "senha = '" + usuario.getEmail() + "', " +
                         "tags = '" + usuario.getTags() + "', " +
                         "WHERE id = " + usuario.getId();

            //Executa o update com a variavel String query               
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
    
    //Exclui o usuario pertencente ao id informado
    public boolean excluirUsuario(int id){
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM usuario WHERE id = " + id);
            st.close();
            status = true;
        } catch(SQLException u){
            throw new RuntimeException(u);
        }
        return status;
    }

    //Retorna o usuario pertencente ao id informado
    public Usuario get(int id) {
        Usuario usuario = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuario WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()){
                usuario = new Usuario(
                        
                		rs.getInt("id"),
                        rs.getString("nome"), 
                        rs.getString("email"), 
                        rs.getString("senha"), 
                        rs.getString("tags")
                        
                    );
            }
            st.close();
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    //Retorna todos os animais presentes no banco de dados
    public Usuario[] getUsuarios() {
        Usuario[] usuarios = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM usuario");
    
            if (rs.next()) {
                rs.last();
                usuarios = new Usuario[rs.getRow()];
                rs.beforeFirst();
            }
    
            for (int i = 0; rs.next(); i++) {
                usuarios[i] = new Usuario(
                		rs.getInt("id"),
                        rs.getString("nome"), 
                        rs.getString("email"), 
                        rs.getString("senha"), 
                        rs.getString("tags")
                );
            }
    
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    
}