package com.unisales.projetobackend.controls;

import java.util.List;
import java.util.UUID;

import com.unisales.projetobackend.models.Departamento;
import com.unisales.projetobackend.services.DepartamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartamentoControl {

    // Injeção de dependência para o serviço de Departamentos
    @Autowired
    private DepartamentoService departamentoService;

    // Mapeamento de rota para listar todos os departamentos
    @GetMapping("/departamentos")
    public String listar(Model model) {

        // Recupera a lista de departamentos através do serviço
        List<Departamento> departamentos = departamentoService.listar();
        // Atribui a lista de departamentos ao modelo
        model.addAttribute("listaDepartamentos", departamentos);
        // Retorna o template para listar os departamentos
        return "departamentos/lista";
    }

    // Mapeamento de rota para exibir o formulário de cadastro de departamento
    @GetMapping("/departamentos/cadastro")
    public String inserir() {
        // Retorna o template para cadastrar um novo departamento
        return "departamentos/cadastro";
    }
    
    // Método para cadastrar um departamento no banco de dados
    @PostMapping(value = "/departamentos/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String cadastrarDepartamento(Departamento departamento) {

        // Chama o serviço para inserir o novo departamento
        departamentoService.inserir(departamento);
        // Redireciona para a lista de departamentos após o cadastro
        return "redirect:/departamentos";
    }

    // Mapeamento de rota para recuperar e exibir os detalhes de um departamento
    @GetMapping("/departamentos/{id}")
    public String recuperar(Model model, @PathVariable UUID id) {

        // Recupera o departamento pelo ID fornecido
        Departamento departamento = departamentoService.recuperar(id);
        // Se não encontrar o departamento, redireciona para a lista de departamentos
        if(departamento == null)
            return "redirect:/departamentos";

        // Atribui o departamento ao modelo para exibir no template
        model.addAttribute("departamento", departamento);
        // Retorna o template para exibir os detalhes do departamento
        return "departamentos/abre";
    }

    // Mapeamento de rota para excluir um departamento
    @GetMapping("/departamentos/exclui/{id}")
    public String excluir(@PathVariable UUID id) {

        // Chama o serviço para excluir o departamento com o ID fornecido
        departamentoService.excluir(id);
        // Redireciona para a lista de departamentos após a exclusão
        return "redirect:/departamentos";
    }
    
    // Método para atualizar as informações de um departamento existente
    @PostMapping(value = "/departamentos/atualiza/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String atualiza(@PathVariable UUID id, @ModelAttribute Departamento departamento) throws Exception {

        // Recupera o departamento existente no banco pelo ID
        Departamento departamentoExistente = departamentoService.recuperar(id);
        // Se o departamento existir, atualiza seus dados com os novos valores
        if (departamentoExistente != null) {
            departamentoExistente.setNome(departamento.getNome());
            // Chama o serviço para atualizar as informações no banco
            departamentoService.inserir(departamentoExistente);
        }

        // Redireciona para a lista de departamentos após a atualização
        return "redirect:/departamentos";
    }
}
