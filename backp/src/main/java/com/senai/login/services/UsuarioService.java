package com.senai.login.services;

import com.senai.login.dtos.UsuarioDto;
import com.senai.login.models.UsuarioEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    public Boolean cadastrar(UsuarioDto entrada){

        UsuarioEntity entity = new UsuarioEntity();

        entity.setCpf(entrada.getCpf());
        entity.setNome(entrada.getNome());
        entity.setLogin(entrada.getLogin());
        entity.setSenha(entrada.getSenha());
        //-- Preciso verificar se usuario ja foi cadastrado
        //-- Se o usuario ja esta cadastrado retona falso
        //-- Validar dados preenchido (camposo obrigatorios)
        //-- Se nao tiver algum dado preenchido retorna falso
        //-- Se nao foi cadastrado, cadastrar novo
        //-- Se deu certo cadastrar ele retornar verdadeiro


        return true;
    }

}
