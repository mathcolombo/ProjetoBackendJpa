package com.unisales.projetobackend.controls;

import java.util.List;
import java.util.UUID;

import com.unisales.projetobackend.models.Departamento;
import com.unisales.projetobackend.models.Funcionario;
import com.unisales.projetobackend.services.DepartamentoService;
import com.unisales.projetobackend.services.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FuncionarioControl {

    // Injeção de dependência para o serviço de Funcionários
    @Autowired
    private FuncionarioService funcionarioService;

    // Injeção de dependência para o serviço de Departamentos
    @Autowired
    private DepartamentoService departamentoService;

    // Mapeamento de rota para listar todos os funcionários
    @GetMapping("/funcionarios")
    public String listar(Model model) {

        // Recupera a lista de funcionários através do serviço
        List<Funcionario> funcionarios = this.funcionarioService.listar();
        // Atribui a lista de funcionários ao modelo
        model.addAttribute("listaFuncionarios", funcionarios);
        // Retorna o template para listar os funcionários
        return "funcionarios/lista";
    }

    // Mapeamento de rota para exibir o formulário de cadastro de funcionário
    @GetMapping("funcionarios/cadastro")
    public String inserir(Model model) {

        // Recupera a lista de departamentos para preencher o formulário de cadastro
        List<Departamento> departamentos = departamentoService.listar();
        // Atribui a lista de departamentos ao modelo
        model.addAttribute("departamentos", departamentos);
        // Retorna o template para cadastrar o funcionário
        return "funcionarios/cadastro";
    }
    
    // Método para cadastrar um funcionário no banco de dados
    @PostMapping(value = "/funcionarios/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postMethodName(Funcionario funcionario) {

        // Chama o serviço para inserir o novo funcionário
        this.funcionarioService.inserir(funcionario);
        // Redireciona para a lista de funcionários após o cadastro
        return "redirect:/funcionarios";
    }

    // Mapeamento de rota para recuperar e exibir detalhes de um funcionário
    @GetMapping("/funcionarios/{id}")
    public String recuperar(Model model, @PathVariable UUID id) {

        // Recupera o funcionário pelo ID fornecido
        Funcionario funcionario = this.funcionarioService.recuperar(id);
        // Se não encontrar o funcionário, redireciona para a lista de funcionários
        if(funcionario == null)
            return "redirect:/funcionarios";

        // Atribui o funcionário ao modelo para exibir no template
        model.addAttribute("funcionario", funcionario);
        // Retorna o template para exibir os detalhes do funcionário
        return "funcionarios/abre";
    }

    // Mapeamento de rota para excluir um funcionário
    @GetMapping("/funcionarios/exclui/{id}")
    public String excluir(@PathVariable UUID id) {

        // Chama o serviço para excluir o funcionário com o ID fornecido
        this.funcionarioService.excluir(id);
        // Redireciona para a lista de funcionários após a exclusão
        return "redirect:/funcionarios";
    }
    
    // Método para atualizar as informações de um funcionário existente
    @PostMapping(value = "/funcionarios/atualiza/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String atualiza(@PathVariable UUID id, @ModelAttribute Funcionario funcionario, Model model) throws Exception {

        // Recupera o funcionário existente no banco pelo ID
        Funcionario funcionarioExistente = this.funcionarioService.recuperar(id);
        // Se o funcionário existir, atualiza seus dados com os novos valores
        if (funcionarioExistente != null) {
            funcionarioExistente.setNome(funcionario.getNome());
            funcionarioExistente.setCpf(funcionario.getCpf());
            funcionarioExistente.setDataNascimento(funcionario.getDataNascimento());
            funcionarioExistente.setDepartamento(funcionario.getDepartamento());
            funcionarioExistente.setDataAdmissao(funcionario.getDataAdmissao());
            funcionarioExistente.setStatus(funcionario.getStatus());
            // Chama o serviço para atualizar as informações no banco
            funcionarioService.inserir(funcionarioExistente);
        }
        // Recupera todos os departamentos para popular o campo select no formulário de atualização
        List<Departamento> departamentos = departamentoService.listar();
        // Adiciona a lista de departamentos ao modelo
        model.addAttribute("departamentos", departamentos);
        // Redireciona para a lista de funcionários após a atualização
        return "redirect:/funcionarios";
    }
}
