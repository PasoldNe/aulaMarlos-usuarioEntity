package com.senai.acesso.controllers;

import com.senai.acesso.dtos.CategoriaCriarDto;
import com.senai.acesso.dtos.CategoriaDto;
import com.senai.acesso.services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriaController {

    //instanciar service
    private final CategoriaService service;
    public CategoriaController(CategoriaService categoria) { this.service = categoria;}

    //post /categoria cadastrar nova categoria
        //-- dto de entrada
        //-- validar integridade do dto
        //TODO -- uppercase para variacoes de escrita
        //-- validar se ja existe
        //-- encaminhar ao service dto de entrada caso esteja certo
        //-- retornar
    @PostMapping("/categoria")
    public ResponseEntity<String> cadastrarCategoria(@RequestBody CategoriaCriarDto entrada){
       if (entrada.getNome().equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A categoria deve conter um nome!!");
        }
        if (service.buscarCategoria(entrada.getNome())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Tem certeza? Categoria ja cadastrada no sistema!");
        }
        //service e retorno baixo
        return ResponseEntity.ok().body(service.cadastrarCategoria(entrada));
    }

    //-- get /categorias listar TODAS
        //-- encaminhar ao service a requisição
        //-- retornar uma lista de dto
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaDto>> listarCategorias(){
        return ResponseEntity.ok().body(service.listarCategorias());
    }
}
