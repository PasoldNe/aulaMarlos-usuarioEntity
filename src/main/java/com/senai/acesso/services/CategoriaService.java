package com.senai.acesso.services;

import com.senai.acesso.dtos.CategoriaCriarDto;
import com.senai.acesso.dtos.CategoriaDto;
import com.senai.acesso.models.CategoriaEntity;
import com.senai.acesso.repositories.CategoriaRepository;
import org.springframework.aot.generate.GeneratedTypeReference;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;
    public CategoriaService(CategoriaRepository repository) {this.repository = repository;}


    /*instanciar entity para salvar local
    transferir do dto para entity
    salvar no banco
    obter ID da categoria do banco
    retornar*/
    /*public String cadastrarCategoria(CategoriaCriarDto entrada){
        CategoriaEntity entity = new CategoriaEntity();

        entity.setNome(entrada.getNome());

        repository.save(entity);


        //TODO -- buscar ID no banco
        //TODO -- salvar no entity o ID

        return "Categoria cadastrada COM SUCESSO! ID: " + + " | Nome: " + entity.getNome();
    }
*/
    //cadastrar nova categoria SEM VALIDAR se ja existe pois isso esta dentro de *buscarCategoria*
    public String cadastrarCategoria(CategoriaCriarDto entrada){
        CategoriaEntity entity = new CategoriaEntity();
        entity.setNome(entrada.getNome());

        repository.save(entity);
        //DEBUG
        System.out.println("Categoria ADICIONADA");
        System.out.println("ID - " + entity.getId());
        System.out.println("Nome - " + entity.getNome());
        return "Sucesso, categoria CADASTRADA!";

    }

    //SOMENTE buscar categoria | TRUE=Encontrada \ FALSE=Nao econtrada
    public boolean buscarCategoria(String nome){
        if (repository.findByNome(nome).isPresent()){
            return true;
        }
        return false;
    }

    /*Lista
    criar lista de categoriadto vazia
    repeticao enquanto tiver enttity no banco vai continuar
    obter entity do banco
    settar informacoes do entity no dto
    adicionar dto na lista*/
    //buscar por todas as categorias do banco e exibir via json
    public List<CategoriaDto> listarCategorias(){
        List<CategoriaDto> lista = new ArrayList();

        for (CategoriaEntity entity : repository.findAll()){
            CategoriaDto categoria = new CategoriaDto();
            categoria.setId(entity.getId());
            categoria.setNome(entity.getNome());
            lista.add(categoria);
            //DEBUG
            System.out.println("Categoria encontrada: ID - " + entity.getId());
        }
        return lista;
    }
}
