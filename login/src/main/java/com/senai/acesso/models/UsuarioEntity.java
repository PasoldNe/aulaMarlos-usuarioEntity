package com.senai.acesso.models;

public class UsuarioEntity {

    //--Atributos do usuário
    private String cpf;
    private String nome;
    private String login;
    private String senha;

    //--Construtor vazio
    public UsuarioEntity() { }

    //--getters e setters
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
