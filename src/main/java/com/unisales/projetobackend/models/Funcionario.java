package com.unisales.projetobackend.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import com.unisales.projetobackend.utils.Enumeradores.AtivoInativoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 150)
    private String nome;

    @Column(unique = true, length = 12)
    private String cpf;
 
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
 
    @ManyToOne
    @JoinColumn(name = "departamentoId")
    private Departamento departamento;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdmissao;

    @Enumerated
    private AtivoInativoEnum status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {

        if(nome == null || nome.isBlank() || nome.isEmpty() || nome.length() > 150)
            throw new Exception("Nome inválido");

        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws Exception {

        if(cpf == null || cpf.isBlank() || cpf.isEmpty() || cpf.length() != 12)
            throw new Exception("Cpf inválido");

        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public AtivoInativoEnum getStatus() {
        return status;
    }

    public void setStatus(AtivoInativoEnum status) {
        this.status = status;
    }
}
