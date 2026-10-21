package com.senai.acesso.dtos;

public class UsuarioAtualizaDto {

    private String nome;
    private String login;


    public UsuarioAtualizaDto() {
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
}
