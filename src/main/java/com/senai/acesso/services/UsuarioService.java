package com.senai.acesso.services;

import com.senai.acesso.repositories.UsuarioRepository;
import com.senai.acesso.dtos.*;
import com.senai.acesso.models.UsuarioEntity;
import com.senai.acesso.util.SenhaUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    //--Injeção de dependencia no service
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    //--Metodo para cadastrar o usuário que recebe os dados do usuário no DTO!!!
    public boolean cadastrar(UsuarioDto usuarioDto) {

        Optional<UsuarioEntity> usuarioOP = repository.findByCpf(usuarioDto.getCpf());
        if (usuarioOP.isPresent()){
            return false;
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

        String senhaCripto = SenhaUtil.encode(usuarioDto.getSenha());

        //-- ou seja obtém o valor dos dados do DTO e atribui para um novo objeto do tipo Entity
        usuarioEntity.setCpf(usuarioDto.getCpf());
        usuarioEntity.setNome(usuarioDto.getNome());
        usuarioEntity.setLogin(usuarioDto.getLogin());
        usuarioEntity.setSenha(senhaCripto); //-- senha criptografada

        //--Insert no banco de dados!
        repository.save(usuarioEntity);

        //--Se deu certo cadastrar o usuário eu retrorno verdadeiro (true)
        return true;
    }

    public List<UsuarioRespostaDto> listarUsuarios(){
        List<UsuarioEntity> lista = repository.findAll();
        List<UsuarioRespostaDto> listaResposta = new ArrayList();

        for (UsuarioEntity usuario : lista) {
            UsuarioRespostaDto respostaDto = new UsuarioRespostaDto();
            respostaDto.setCpf(usuario.getCpf());
            respostaDto.setLogin(usuario.getLogin());
            respostaDto.setNome(usuario.getNome());
            listaResposta.add(respostaDto);
        }
        return listaResposta;
    }

    public boolean atualizarUsuario(UsuarioAtualizaDto usuarioDto, String cpf){

        Optional<UsuarioEntity> usuarioOP = repository.findByCpf(cpf);
        if (!usuarioOP.isPresent()){
            return false;
        }

        //--Obter o usuarioEntity do optional e atribuir no objeto "usuario"
        UsuarioEntity usuario = usuarioOP.get();

        //--atualiza dados do usuário
        usuario.setNome(usuarioDto.getNome());
        usuario.setLogin(usuarioDto.getLogin());

        //--Update do banco de dados
        repository.save(usuario);

        //--retornar quando o usuario foi encontrado e dados atualizados
        return true;

    }

    public boolean removerUsuario(String cpf){

        Optional<UsuarioEntity> usuarioOP = repository.findByCpf(cpf);
        if (usuarioOP.isPresent()){
            repository.delete(usuarioOP.get());
            return true;
        }
        return false;
    }

    public UsuarioRespostaDto obterUsuario(String cpf){

        UsuarioRespostaDto usuarioDto = new UsuarioRespostaDto();

        Optional<UsuarioEntity> usuarioOP = repository.findByCpf(cpf);

        if (usuarioOP.isPresent()){
            UsuarioEntity usuario = usuarioOP.get();
            usuarioDto.setNome(usuario.getNome());
            usuarioDto.setLogin(usuario.getLogin());
            usuarioDto.setCpf(usuario.getCpf());
        }

        return usuarioDto;
    }

    public boolean login(LoginDto loginDto){

        Optional<UsuarioEntity> usuarioOP = repository.findByLogin(loginDto.getLogin());

        if (usuarioOP.isPresent()){
            String senhaCripto = SenhaUtil.encode(loginDto.getSenha());
            UsuarioEntity usuario = usuarioOP.get();
            if (SenhaUtil.matches(loginDto.getSenha(),usuario.getSenha())){
                return true;
            }
        }

        return false;
    }

    public boolean trocarSenha(UsuarioTrocaSenhaDto usuarioTrocaSenhaDto, String cpf){

        Optional<UsuarioEntity> usuarioOP = repository.findByCpf(cpf);

        if (usuarioOP.isPresent()){

            UsuarioEntity usuario = usuarioOP.get();

            if (usuarioTrocaSenhaDto.getSenhaNovaConfirmacao().equals(usuarioTrocaSenhaDto.getSenhaNova())){

                String senhaCripto = SenhaUtil.encode(usuarioTrocaSenhaDto.getSenhaNova());

                //-- significa que o usuário com aquele cpf existe e a senha esta correta
                usuario.setSenha(senhaCripto);

                //-atualizar senha do usuário
                repository.save(usuario);

                return true;

            }
        }
        return false;
    }

}
