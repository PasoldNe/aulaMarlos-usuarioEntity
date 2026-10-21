package com.senai.acesso.dtos;

public class ProdutoRetornoDto {
    private Boolean statusBuscaCategoria = false;
    private Long produtoId;
    private String nomeCategoria;

    public ProdutoRetornoDto() {
    }

    public Boolean getStatusBuscaCategoria() {
        return statusBuscaCategoria;
    }

    public void setStatusBuscaCategoria(Boolean statusBuscaCategoria) {
        this.statusBuscaCategoria = statusBuscaCategoria;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }
}
