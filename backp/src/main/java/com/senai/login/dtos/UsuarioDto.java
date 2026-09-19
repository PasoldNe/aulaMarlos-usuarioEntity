package com.senai.login.dtos;

public class UsuarioDto {

    //dto de entrada de usuario

    /*ex:
    {
        "cpf" : "000.111.222-33",
        "nome" : "Eu",
        "login" : "Admin",
        "senha" : "123admin"
    }
     */
    private String cpf;
    private String nome;
    private String login;
    private String senha;


    public UsuarioDto() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
