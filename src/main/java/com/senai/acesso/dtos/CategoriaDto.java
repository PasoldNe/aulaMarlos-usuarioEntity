package com.senai.acesso.dtos;

public class CategoriaDto {
    private Long Id;
    private String nome;

    public CategoriaDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
