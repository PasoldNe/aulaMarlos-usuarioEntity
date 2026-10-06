package com.senai.acesso.controllers;

import com.senai.acesso.dtos.*;
import com.senai.acesso.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //http://localhost:8080/api/usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioRespostaDto>> listarUsuarios(){

        List<UsuarioRespostaDto> lista = service.listarUsuarios();
        return ResponseEntity.ok().body(lista);

    }
    //http://localhost:8080/api/usuarios
    @PutMapping("/usuario/{cpf}")
    public ResponseEntity<String> atualizar(@RequestBody UsuarioAtualizadoDto atualizadoDto,
                                            @PathVariable String cpf){
        if (atualizadoDto.getLogin().equals("") ||
                atualizadoDto.getSenha().equals("") ||
                atualizadoDto.getNome().equals("") ){
            return ResponseEntity.badRequest().body("Todos os campos precisam ser preenchidos com dados de informações de dados");
        }

        boolean retorno = service.atualizarUsuario(atualizadoDto , cpf);

        if (retorno){
            return ResponseEntity.ok().body("Sucesso");
        } else {
            return ResponseEntity.badRequest().body("Falso, erro com o servidor, sla tb sabosta");
        }
    }

    //http://localhost:8080/api/usuario/
    @DeleteMapping("/usuario/{cpf}")
    public ResponseEntity<String> deletarUsuario(@PathVariable String cpf){
        boolean sucesso;
        
        sucesso = service.deletarUsuario(cpf);
        
        if(sucesso){
            return ResponseEntity.ok().body("Sucesso");    
        } else {
            return ResponseEntity.ok().body("Fracasso");
        }
    }
    //http://localhost:8080/api/usuario/
    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<UsuarioRespostaDto> buscaUnitaria(@PathVariable String cpf){
        //receber cpf F
        //encaminhar pro service F
        //chamar o metodo informando o cpf F
        //mostrar o resultado F
        UsuarioRespostaDto retorno = service.buscaUnitaria(cpf);

        return ResponseEntity.ok().body(retorno);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLogin entradaLogin){
        if (service.login(entradaLogin)){
            return ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail/senha nao encontrados");
        }
    }

    @PutMapping("usuario/senha/{cpf}")
    public ResponseEntity<String> trocarSenha(@RequestBody UsuarioAlterarSenha alterarSenha, @PathVariable String cpf){
        //instanciar dto para info
        //usuario encontrado
        //senha condizente
        //senha requisitos
        //senha alterada

        RetornoAlterarSenha retorno = service.trocarSenha(alterarSenha, cpf);

        if (retorno.getUsuarioEncontrado()) {
            if (!retorno.getSenhaAtual()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A senha atual está incorreta.");
            }
            if (!retorno.getSenhaRequisitos()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A nova senha deve ter no mínimo 8 caracteres e as senhas digitadas devem coincidir.");
            }
            if (retorno.getSenhAlterada()) {
                return ResponseEntity.status(HttpStatus.OK).body("Senha alterada com sucesso!");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao atualizar a senha.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
    }
}















