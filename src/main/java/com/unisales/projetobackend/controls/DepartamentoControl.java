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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class DepartamentoControl {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/departamentos")
    public String listar(Model model) {

        List<Departamento> response = departamentoService.listar();
        model.addAttribute("listaDepartamentos", response);
        return "departamentos/lista";
    }

    @GetMapping("/departamentos/cadastro")
    public String inserir(@RequestParam String param) {
        return "departamentos/nova";
    }
    
    @PostMapping(value = "/Departamentos/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postMethodName(Departamento departamento) {

        departamentoService.inserir(departamento);
        return "redirect:/departamentos";
    }

    @GetMapping("/departamentos/{id}")
    public String recuperar(Model model, @PathVariable UUID id) {

        Departamento departamento = departamentoService.recuperar(id);
        if(departamento == null)
            return "redirect:/departamentos";

        model.addAttribute("departamento", departamento);
        return "departamentos/abre";
    }

    @GetMapping("/departamentos/exclui/{id}")
    public String excluir(@PathVariable UUID id) {

        departamentoService.excluir(id);
        return "redirect:/departamentos";
    }
    
    @PostMapping(value = "/departamentos/atualiza/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String atualiza(@PathVariable UUID id, Departamento Departamento) {

        Departamento departamento = departamentoService.recuperar(id);
        if (departamento != null)
            departamentoService.inserir(departamento);

        return "redirect:/departamentos";
    }
   
    
}
