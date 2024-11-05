package com.unisales.projetobackend.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

    @Query(value = "select d from Departamento d order by d.nome")
    List<Departamento> selecionaOrdenadoPorNome();
}
