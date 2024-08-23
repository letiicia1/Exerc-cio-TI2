package app;

import java.util.List;
import java.util.Scanner;

import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {

    public static Usuario inserir(Scanner scanner) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int codigo;
        String login;
        String senha;
        String reserva;
        char sexo;

        System.out.println("\n\n==== Inserir usuário ===");

        System.out.print("Inserir código: ");
        codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir o \n deixado pelo nextInt()

        System.out.print("Inserir login: ");
        login = scanner.nextLine();

        System.out.print("Inserir senha: ");
        senha = scanner.nextLine();

        System.out.print("Inserir sexo (M/F): ");
        reserva = scanner.nextLine();
        sexo = reserva.charAt(0);

        Usuario usuario = new Usuario(codigo, login, senha, sexo);
        if (usuarioDAO.insert(usuario)) {
            System.out.println("Inserção com sucesso -> " + usuario.toString());
        }

        return usuario;
    }

    public static void listar() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("\n\n==== Mostrar usuários ordenados por código ===");
        List<Usuario> usuarios = usuarioDAO.getOrderByCodigo();
        for (Usuario u : usuarios) {
            System.out.println(u.toString());
        }
    }

    public static void excluir(Usuario usuario) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("\n\n==== Excluir usuário (código " + usuario.getCodigo() + ") ===");
        usuarioDAO.delete(usuario.getCodigo());
    }

    public static Usuario atualizar(Usuario usuario, Scanner scanner) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.print("Inserir nova senha: ");
        String senha = scanner.nextLine();

        System.out.println("\n\n==== Atualizar senha (código " + usuario.getCodigo() + ") ===");
        usuario.setSenha(senha);
        usuarioDAO.update(usuario);

        return usuario;
    }

    public static void main(String[] args) throws Exception {
        Usuario usuario = null;
        int opcao;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Opções:");
            System.out.println("1) Listar");
            System.out.println("2) Inserir");
            System.out.println("3) Atualizar");
            System.out.println("4) Excluir");
            System.out.println("5) Sair");

            System.out.print("Entrar com opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o \n deixado pelo nextInt()

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    usuario = inserir(scanner);
                    break;
                case 3:
                    if (usuario != null) {
                        usuario = atualizar(usuario, scanner);
                    } else {
                        System.out.println("Nenhum usuário foi inserido para atualizar.");
                    }
                    break;
                case 4:
                    if (usuario != null) {
                        excluir(usuario);
                        usuario = null; // Remover referência após exclusão
                    } else {
                        System.out.println("Nenhum usuário foi inserido para excluir.");
                    }
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 5);

        scanner.close();
    }
}
