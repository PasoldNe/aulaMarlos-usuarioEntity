package com.senai.login.controller;

import com.senai.login.dtos.UsuarioDto;
import com.senai.login.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UsuarioController {
    //-- Declaro a variavel UsuarioService com o objeto serice para instanciar no construtuor do controller
    private final UsuarioService service;
    //-- No construtor do controller instancio um novo objeto do tipo UsuarioService
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //-- http://localhost:8080/api/usuario
    @PostMapping("/usuario")
    //TODO DTO de saida
    public ResponseEntity<String> cadastrarUsuario (@RequestBody UsuarioDto entrada){

        //chamar o service para cirar o usuario
        boolean retorno = service.cadastrar(entrada);
        //retorno do cadastro co  sucesso ou erro

        if (retorno){
            return ResponseEntity.ok().body("sucesso");
        } else {
            return ResponseEntity.badRequest().body("Fracasso");
        }

    }
}
