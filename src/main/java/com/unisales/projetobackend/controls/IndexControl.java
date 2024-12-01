package com.unisales.projetobackend.controls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexControl {

    @GetMapping({ "", "/" })
    public String index() {
        return "index";
    }
}
