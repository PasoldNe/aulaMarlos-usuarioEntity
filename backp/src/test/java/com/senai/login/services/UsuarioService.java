package com.senai.login.services;

import com.senai.login.dtos.UsuarioDto;
import com.senai.login.dtos.UsuarioRespostaDto;
import com.senai.login.models.UsuarioEntity;
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

}
