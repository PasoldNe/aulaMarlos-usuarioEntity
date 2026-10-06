package com.senai.acesso.dtos;

public class RetornoAlterarSenha {
    private Boolean usuarioEncontrado = false;
    private Boolean senhaAtual;
    private Boolean senhaRequisitos;
    private Boolean senhAlterada;

    public RetornoAlterarSenha() {
    }

    public Boolean getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public void setUsuarioEncontrado(Boolean usuarioEncontrado) {
        this.usuarioEncontrado = usuarioEncontrado;
    }

    public Boolean getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(Boolean senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public Boolean getSenhaRequisitos() {
        return senhaRequisitos;
    }

    public void setSenhaRequisitos(Boolean senhaRequisitos) {
        this.senhaRequisitos = senhaRequisitos;
    }

    public Boolean getSenhAlterada() {
        return senhAlterada;
    }

    public void setSenhAlterada(Boolean senhAlterada) {
        this.senhAlterada = senhAlterada;
    }
}
