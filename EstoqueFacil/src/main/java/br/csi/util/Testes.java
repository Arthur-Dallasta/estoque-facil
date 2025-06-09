package br.csi.util;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

import java.util.ArrayList;

public class Testes {

    public static void main(String[] args) {
        testesUsuarioDAO();
    }

    public static void testesUsuarioDAO() {
        UsuarioDAO dao = new UsuarioDAO();

        //Criar usuario
        Usuario usuario = new Usuario();
        usuario.setNome("Maran");
        usuario.setEmail("maran@teste");
        usuario.setSenha("123");
        usuario.setTelefone("1234567890");
        usuario.setNivelAcesso("FUNCIONARIO");
        usuario.setAtivo(true);

        dao.inserir(usuario);

        //Listar usuario
        imprimirUsuarios(dao.listar());

        //Alterar usuario
        usuario.setId(1);
        usuario.setNome("Maran Editado");
        usuario.setEmail("maran@teste.editado");
        usuario.setTelefone("9876543210");

        dao.atualizar(usuario);

        //Listar usuario
        imprimirUsuarios(dao.listar());
    }

    public static void imprimirUsuario(Usuario usuario) {
        System.out.println(
            "Id: " + usuario.getId()
            + " Nome: " + usuario.getNome()
            + " Email: " + usuario.getEmail()
            + " Telefone: " + usuario.getTelefone()
            + " NivelAcesso: " + usuario.getNivelAcesso()
            + " Ativo: " + usuario.isAtivo()
        );
    }

    public static void imprimirUsuarios(ArrayList<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            imprimirUsuario(usuario);
        }
    }
}
