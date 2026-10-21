package com.senai.acesso.dtos;

public class UsuarioTrocaSenhaDto {
     private String senhaAtual;
     private String senhaNova;
     private String senhaNovaConfirmacao;

    public UsuarioTrocaSenhaDto() {
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

    public boolean isValidacao(){
        return this.getSenhaNova().isEmpty() || this.getSenhaNova() == null ||
                this.getSenhaAtual().equals("") || this.getSenhaAtual() == null ||
                this.getSenhaNovaConfirmacao().equals("") || this.getSenhaNovaConfirmacao() == null;
    }
}
