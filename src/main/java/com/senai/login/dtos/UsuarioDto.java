package com.senai.acesso.dtos;

public class UsuarioDto {

    //--DTO de Entrada de dados
    /* Exemplo

    {
        "cpf" : "111.111.111-11",
        "nome": "Aluno 1",
        "login" : "login1",
        "senha" : "senha123"
    }
    {
        "cpf" : "676.767.676-67",
        "nome" : "TungTungSahur",
        "login" : "broxa",
        "senha" : "comecu"
    }
    {
        "cpf" : "333.333.333-33",
        "nome": "Aluno 3",
        "login" : "login3",
        "senha" : "senha345"
    }

    */


    private String cpf;
    private String nome;
    private String login;
    private String senha;

    public UsuarioDto() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
