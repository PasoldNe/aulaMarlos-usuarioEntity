package com.senai.acesso.controllers;

import com.senai.acesso.dtos.ProdutoCriarDto;
import com.senai.acesso.dtos.ProdutoDto;
import com.senai.acesso.dtos.ProdutoRetornoDto;
import com.senai.acesso.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    /*receber os dados
    tratar campos vazios
    encaminhas ao service
    retornar SUCESSO
     */
    //http://localhost:8080/produto
    @PostMapping("/produto")
    public ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoCriarDto entrada){
        ProdutoRetornoDto dto = service.cadastrarProduto(entrada);
        if (entrada.getCategoriaId().equals("") || entrada.getNome().equals("") || entrada.getPreco().equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos os campos devem estar preenchidos!");
        }
        if (dto.getStatusBuscaCategoria()){
            return ResponseEntity.ok().body("SUCESSO, produto CADASTRADO com o ID: '" + dto.getProdutoId() + "' na categoria: " + dto.getNomeCategoria());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada. ID categoria: " + entrada.getCategoriaId());
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoDto>> listarProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarProdutos());
    }

    @GetMapping("/produtos/categoria/{id}")
    public ResponseEntity<ProdutoDto> buscarProduto(@PathVariable Long id){
        //dto novo
        //executar service
        //boolean cheio ou vazio
        //service
        //verificação se existe algum
        //retorno completo ProdutoDto
        ProdutoDto produto = service.consultarProduto(id);
        if (produto.getNome().equals("")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(produto);
        }
    }
}
