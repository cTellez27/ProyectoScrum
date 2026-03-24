package com.torneos.controller;

import com.torneos.dto.UsuarioDTO;
import com.torneos.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "registro"; // Nombre de tu archivo HTML de registro público
    }

    @PostMapping
    public String registrarCuenta(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        try {
            usuarioService.registrarUsuario(usuarioDTO);
            // Redirige con éxito para mostrar el mensaje verde
            return "redirect:/login?registrado=true";
        } catch (Exception e) {
            // Si falla (ej. correo duplicado), vuelve al form con error
            return "redirect:/registro?error";
        }
    }
}