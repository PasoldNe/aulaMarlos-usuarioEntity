package com.senai.acesso.dtos;

public class UsuarioAlterarSenha {

    /*

    {
    "senhaAtual": "usuario123",
    "senhaNova": "senhaSegura"
    "SenhaNovaConfirmacao": "senhaSegura"
    }

     */
    private String senhaAtual;
    private String senhaNova;
    private String senhaNovaConfirmacao;

    public UsuarioAlterarSenha() {
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public String getSenhaNovaConfirmacao() {
        return senhaNovaConfirmacao;
    }

    public void setSenhaNovaConfirmacao(String senhaNovaConfirmacao) {
        this.senhaNovaConfirmacao = senhaNovaConfirmacao;
    }
}
