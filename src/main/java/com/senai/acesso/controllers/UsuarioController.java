package com.senai.acesso.controllers;

import com.senai.acesso.dtos.*;
import com.senai.acesso.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    //-- A injeção de dependencia sempre ocorre a nível de classe e nunca a nível de método.
    //--Declaro a classe UsuarioService com o objeto service para instanciar no contrutor do controller
    private final UsuarioService service;

    //--No construtur do controller instancio um novo objeto do tipo UsuarioService
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    //-- método que será executado quando POST em http://localhost:8080/api/usuario
    // --> body (json com os dados do usuário)
    @PostMapping("/usuario")
    public ResponseEntity<String> criarUsuario(@RequestBody UsuarioDto usuario){

        //-- chamar método para cadastrar usuário lá do service
        boolean retorno = service.cadastrar(usuario);

        //-- realizar o retorno de cadastro com sucesso ou erro ao cadastrar.
        if (retorno) {
            return ResponseEntity.ok().body("sucesso");
        } else {
            return ResponseEntity.badRequest().body("erro ao inserir usário");
        }

    }


    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioRespostaDto>> listarUsuarios(){

        List<UsuarioRespostaDto> lista = service.listarUsuarios();
        return ResponseEntity.ok().body(lista);

    }

    @PutMapping("/usuario/{cpf}")
    public ResponseEntity<String> atualizarUsuario(@RequestBody UsuarioAtualizaDto usuarioDto,
                                                   @PathVariable String cpf){
        if (usuarioDto.getNome().equals("")  || usuarioDto.getLogin().equals("") ){
            return ResponseEntity.badRequest().body("Todos os campos devem ser informados!");
        }

        boolean retorno = service.atualizarUsuario(usuarioDto, cpf);
        if (retorno){
            return ResponseEntity.ok().body("Usuário atualizado com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao atualizar o usuário");
        }
    }

    @DeleteMapping("/usuario/{cpf}")
    public ResponseEntity<String> deletarUsuario(@PathVariable String cpf){
        boolean retorno = service.removerUsuario(cpf);
        if (retorno){
            return ResponseEntity.ok().body("Usuário removido com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("Erro ao remover o usuário");
        }
    }

    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<?> obterUsuario(@PathVariable  String cpf){
        UsuarioRespostaDto usuarioDto = service.obterUsuario(cpf);
        if (usuarioDto.getCpf().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        if (loginDto.getLogin().equals("") || loginDto.getSenha().equals("") ||
                loginDto.getLogin() == null || loginDto.getSenha() == null) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email e senha são obrigatórios.");
        }

        //-- chamar o service para realizar a validação do login
        boolean retorno = service.login(loginDto);

        if (retorno) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Login realizado com sucesso.");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Erro ao realizar login: credenciais inválidas.");

    }

    @PutMapping("/usuario/senha/{cpf}")
    public ResponseEntity<String> trocarSenha(@PathVariable String cpf,
                                              @RequestBody UsuarioTrocaSenhaDto usuarioTrocaSenhaDto){

        if (usuarioTrocaSenhaDto.isValidacao()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Senha atual ou nova senha são obrigatórias.");

        }

        if (usuarioTrocaSenhaDto.getSenhaNova().length() < 8 ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao realizar a troca de senha: a nova senha deve ter pelo menos 8 caracteres.");
        }

        if (!usuarioTrocaSenhaDto.getSenhaNova().equals(usuarioTrocaSenhaDto.getSenhaNovaConfirmacao())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao realizar a troca de senha: a nova senha nova deve ser igual a confirmação de senha.");
        }

        //-- executa troca de senha no service
        boolean retorno = service.trocarSenha(usuarioTrocaSenhaDto, cpf);

        if (retorno){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Senha alterada com sucesso.");
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Erro ao realizar a troca de senha: senha atual ou usuário incorreto.");

    }

}
