package com.torneos.controller;

import com.torneos.dto.UsuarioDTO;
import com.torneos.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired 
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuario/lista-usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuario/form-usuario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // .orElse(new UsuarioDTO()) evita errores si el ID no existe
        model.addAttribute("usuario", usuarioService.buscarPorId(id).orElse(new UsuarioDTO()));
        return "usuario/form-usuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute UsuarioDTO usuario) {
        // 1. Ya no recibimos 'String contrasena' aparte.
        // 2. El DTO ya trae la contraseña adentro (usuario.getContrasenaUsuario())
        
        // Llamamos al método del servicio (asegúrate que en el servicio se llame igual)
        usuarioService.registrarUsuario(usuario); 
        
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}