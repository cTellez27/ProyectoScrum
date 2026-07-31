package com.torneos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String Inicio() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String mostrarhome() {
        return "home";
    }
    @GetMapping("/login")
    public String mostrarlogin() {
        return "login";
    }
    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }
    @GetMapping("/espectador")
    public String mostrarEspectador() {
        return "espectador/homeEspectador";
    }
}
