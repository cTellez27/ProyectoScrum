package com.torneos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	public static int rol=0;
	
    @GetMapping("/")
    public String Inicio() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String mostrarhome() {
        return "/home";
    }
    @GetMapping("/login")
    public String mostrarlogin() {
        return "/login";
    }
    @GetMapping("/index")
    public String mostrarIndex() {
    	rol = 1;
        return "/index";
    }
    @GetMapping("/espectador")
    public String mostrarEspectador() {
    	rol = 0;
        return "/espectador/homeEspectador";
    }
}
