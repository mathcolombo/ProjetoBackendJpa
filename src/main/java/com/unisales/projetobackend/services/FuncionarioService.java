package com.unisales.projetobackend.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.unisales.projetobackend.models.Funcionario;
import com.unisales.projetobackend.models.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario inserir(Funcionario funcionario) {
        return this.funcionarioRepository.save(funcionario);
    }

    public Funcionario recuperar(UUID id) {

        Funcionario funcionario = this.funcionarioRepository.findById(id).get();
        if(funcionario == null)
            return null;

        return funcionario;
    }

    public List<Funcionario> listar() {
        return this.funcionarioRepository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
    }

    public Funcionario excluir(UUID id) {
        Funcionario funcionario = this.recuperar(id);

        if(funcionario != null)
            this.funcionarioRepository.delete(funcionario);

        return funcionario;
    }
}
