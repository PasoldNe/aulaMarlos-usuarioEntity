package com.senai.acesso.dtos;

public class UsuarioLogin {
    /*
    {
    "login": "usuario123",
    "senha": "senhaSegura"
    }
     */


    private String cpf;
    private String senha;

    public UsuarioLogin() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String login) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
