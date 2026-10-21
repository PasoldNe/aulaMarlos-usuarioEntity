package com.senai.acesso.repositories;

import com.senai.acesso.models.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByCpf(String cpf);

    Optional<UsuarioEntity> findByLogin(String login);


}
