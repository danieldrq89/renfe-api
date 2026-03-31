package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String redirectToSwagger() {
        // Redirige la raíz a la URL de Swagger UI
        return "redirect:/swagger-ui/index.html";
    }
}
