package br.csi.service;

public class LoginService {

    public boolean autenticar(String email, String senha) {

        if(email.equals("admin@admin") && senha.equals("admin")) {
            return true;
        }else {
            return false;
        }

    }

}
