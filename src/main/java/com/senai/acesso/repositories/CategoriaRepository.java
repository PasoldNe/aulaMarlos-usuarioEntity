package com.senai.acesso.repositories;

import com.senai.acesso.models.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNome(String nome);
    Optional<CategoriaEntity> findById(Long id);
}
