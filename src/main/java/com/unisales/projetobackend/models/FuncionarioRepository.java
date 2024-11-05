package com.unisales.projetobackend.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    @Query(value = "select f from Funcionario f order by f.nome")
    List<Funcionario> selecionaOrdenadoPorNome();
}
