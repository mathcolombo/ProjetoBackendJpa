package com.unisales.projetobackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.unisales.projetobackend.models.Departamento;
import com.unisales.projetobackend.models.repositories.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento inserir(Departamento departamento) {
        return this.departamentoRepository.save(departamento);
    }

    public Departamento recuperar(UUID id) {
        
        Departamento departamento = departamentoRepository.findById(id).get();
        if(departamento == null)
            return null;

        return departamento;
    }

    public List<Departamento> listar() {
        return this.departamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Departamento excluir(UUID id) {
        Departamento departamento = this.recuperar(id);

        if(departamento != null)
            this.departamentoRepository.delete(departamento);

        return departamento;
    }
}
