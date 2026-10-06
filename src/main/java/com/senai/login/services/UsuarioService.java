package com.senai.acesso.services;

import com.senai.acesso.dtos.*;
import com.senai.acesso.models.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    //--Declaração da lista de usuários na classe (escopo global da classe UsuarioService
    private List<UsuarioEntity> lista = new ArrayList<UsuarioEntity>();

    //--Metodo para cadastrar o usuário que recebe os dados do usuário no DTO!!!
    public boolean cadastrar(UsuarioDto usuarioDto) {

        //--Preciso verificar se o usuário já foi cadastrado
        //--Estrutura de repetição para lista de objetos
        for (UsuarioEntity usuario : lista) {
            //--Para cada item da lista verifica se o cpf do item da lista é igual ao cpf do usuário do DTO
            if (usuario.getCpf().equals(usuarioDto.getCpf())) {
                //--Se for igual, significa que o cpf já foi cadastrado na lista
                return false;
            }
        }

        //-Valida dados preenchidos ( campos obrigatórios )
        if (usuarioDto.getCpf().equals("")) {
            //--Retorna para o controller se o cpf não foi informado
            return false;
        }
        if (usuarioDto.getNome().equals("")){
            //--Retorna para o controller se o nome não foi informado
            return false;
        }
        if (usuarioDto.getLogin().equals("")){
            //--Retorna para o controller se o login não foi informado
            return false;
        }

        //--Converter os dados do DTO para o ENTITY
        UsuarioEntity usuarioEntity = new UsuarioEntity();

        //-- ou seja obtém o valor dos dados do DTO e atribui para um novo objeto do tipo Entity
        usuarioEntity.setCpf(usuarioDto.getCpf());
        usuarioEntity.setNome(usuarioDto.getNome());
        usuarioEntity.setLogin(usuarioDto.getLogin());
        usuarioEntity.setSenha(usuarioDto.getSenha());

        //---Adiciona na lista o usuário entity com os dados vindos do DTO
        lista.add(usuarioEntity);

        //--Se deu certo cadastrar o usuário eu retrorno verdadeiro (true)
        return true;
    }

    public List<UsuarioRespostaDto> listarUsuarios(){

        List<UsuarioRespostaDto> listaResposta = new ArrayList<UsuarioRespostaDto>();

        for (UsuarioEntity usuario : lista) {
            UsuarioRespostaDto respostaDto = new UsuarioRespostaDto();
            respostaDto.setCpf(usuario.getCpf());
            respostaDto.setLogin(usuario.getLogin());
            respostaDto.setNome(usuario.getNome());
            listaResposta.add(respostaDto);
        }

        return listaResposta;
    }


    public boolean atualizarUsuario( UsuarioAtualizadoDto usuarioDto, String cpf){
        //percorre pelos entitys
        for (UsuarioEntity dto : lista){
            //determinar o portador do cpf solicitante
            if (dto.getCpf().equals(cpf)){
                //atualizar os dados
                dto.setNome(usuarioDto.getNome());
                dto.setLogin(usuarioDto.getLogin());
                dto.setSenha(usuarioDto.getSenha());
                return true;
            }
        }

        return false;
    }

    public boolean deletarUsuario(String cpf){
        //verificar se existe F
            //repeticao por entity F 
            //verificar se é igual F
        //se existir remover F
        //retornar TRUE F

        for (UsuarioEntity entity : lista){
            if(entity.getCpf().equals(cpf)){
                lista.remove(entity);
                 return true;
            }
        }
        return false;
    }

    public UsuarioRespostaDto buscaUnitaria(String cpf){
        //determinar o usuario
            //passar um por um
            //conferir caso seja igual
            //se for igual atribuir a variavel
        //retornar variavel

        UsuarioRespostaDto retorno = new UsuarioRespostaDto();

        for (UsuarioEntity entity : lista){
            if (entity.getCpf().equals(cpf)) {
                retorno.setCpf(entity.getCpf());
                retorno.setNome(entity.getNome());
                retorno.setLogin(entity.getLogin());
                return retorno;
            }
        }
        retorno.setCpf(cpf);
        retorno.setNome("-");
        retorno.setLogin("Usuário não encontrado");
        return retorno;
    }

    public boolean login(UsuarioLogin entrada){

        //verificar dados correspondem a algum usuario cadastrado
            //copiar endereco da lista
            //verificar credenciais
        //retornar

        for (UsuarioEntity entity : lista){
            if (entity.getCpf().equals(entrada.getCpf()) && entity.getSenha().equals(entrada.getSenha())){
                return true;
            }
        }
        return false;
    }

    public RetornoAlterarSenha trocarSenha(UsuarioAlterarSenha entrada, String cpf){
        //instanciar retorno para adicionar valores do mesmo
        //achar o usuario
        //validar a senha atual
        //validar nova senha de acordo com os requisitos
        //validar se a senha eh igual a mesma digitada
        //retornar

        RetornoAlterarSenha retorno = new RetornoAlterarSenha();

        for (UsuarioEntity entity : lista){

            if (entity.getCpf().equals(cpf)){
                retorno.setUsuarioEncontrado(true);//usuario encontrado
                if (entity.getSenha().equals(entrada.getSenhaAtual())){//valida senha atual
                    retorno.setSenhaAtual(true);
                    if (entrada.getSenhaNova().equals(entrada.getSenhaNovaConfirmacao()) && entrada.getSenhaNova().length()>=8){//requisitos: senhas iguais e ser de 8 digitos

                        entity.setSenha(entrada.getSenhaNovaConfirmacao());
                        retorno.setSenhaRequisitos(true);
                        retorno.setSenhAlterada(true);//troca a senha

                    } else {retorno.setSenhaRequisitos(false);} //caso nn atenda aos requisitos

                } else {retorno.setSenhaAtual(false);}//caso a senha atual nao condiga com a q ta na banco
            }
        }
        return retorno;
    }

}
