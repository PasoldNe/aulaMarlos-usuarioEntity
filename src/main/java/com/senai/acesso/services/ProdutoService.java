package com.senai.acesso.services;

import com.senai.acesso.dtos.ProdutoCriarDto;
import com.senai.acesso.dtos.ProdutoDto;
import com.senai.acesso.dtos.ProdutoRetornoDto;
import com.senai.acesso.models.CategoriaEntity;
import com.senai.acesso.models.ProdutoEntity;
import com.senai.acesso.repositories.CategoriaRepository;
import com.senai.acesso.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;
    public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    /*
    armazenar os dados
    verificar se o categoriaId existe
        retornar o status da busca
    --salvar no repository todo o produto
    --buscar o ID do produto e armazenar localmente
     */
    //statusBuscaCategoria: TRUE - existe || FALSE - nao existe
    public ProdutoRetornoDto cadastrarProduto(ProdutoCriarDto entrada){
        ProdutoEntity entity = new ProdutoEntity();
        ProdutoRetornoDto retorno = new ProdutoRetornoDto();
        CategoriaEntity categoria = categoriaRepository.getById(entrada.getCategoriaId());
        if (categoria.getNome().equals("")){
            retorno.setStatusBuscaCategoria(false);
            retorno.setNomeCategoria("Não encontrada");
        } else {
            //settando os valores de retorno
            retorno.setNomeCategoria(categoria.getNome());
            retorno.setStatusBuscaCategoria(true);
            retorno.setProdutoId(entity.getId());
            //salvar no db
            entity.setCategoriaId(entrada.getCategoriaId());
            entity.setNome(entrada.getNome());
            entity.setPreco(entrada.getPreco());
            repository.save(entity);
            //debug
            System.out.println("Produto: " + entity.getNome() + " ADICIONADO na categoria: " + categoria.getNome() + " .");
        }
        return retorno;
    }


    /*
    criar DTO vazio
    criar lista de DTO
    procurar cada produto no banco
    copiar os dados
    salvar na lista
    retornar lista
     */
    //retorna possivel lista com todos os produtos
    public List<ProdutoDto> listarProdutos(){
        List<ProdutoDto> retorno = new ArrayList();
        List<ProdutoEntity> entityList = repository.findAll();

        for (ProdutoEntity entity : entityList){
            ProdutoDto dto = new ProdutoDto();
            dto.setNome(entity.getNome());
            dto.setPreco(entity.getPreco());
            dto.setCategoriaId(entity.getCategoriaId());
            dto.setId(entity.getId());
            //DEBUG
            System.out.println("Produto encontrado com ID: " + entity.getId());
            retorno.add(dto);
        }
        //DEBUG
        System.out.println("Fim dos produtos");
        return retorno;
    }

    /*
    receber
    percorrer pelo banco se existe algum
    repetição de entity pelo repository
    retornar ProdutoDto
     */
    //retorna apenas um produto via ID
    public ProdutoDto consultarProduto(Long id){
        List<ProdutoEntity> listaEntity = repository.findAll();
        ProdutoDto retorno = new ProdutoDto();

        for (ProdutoEntity entity : listaEntity) {
            retorno.setId(entity.getId());
            retorno.setCategoriaId(entity.getCategoriaId());
            retorno.setPreco(entity.getPreco());
            retorno.setNome(entity.getNome());
        }
        //DEBUG
        System.out.println("Produto encontrado com ID: " + retorno.getId());
        return retorno;
    }


}
