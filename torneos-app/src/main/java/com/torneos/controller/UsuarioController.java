package com.torneos.controller;

import com.torneos.dto.UsuarioDTO;
import com.torneos.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String guardarUsuario(@ModelAttribute UsuarioDTO usuario, RedirectAttributes attributes) {
        boolean esEdicion = usuario.getId() != null;
        usuarioService.registrarUsuario(usuario);
        if (esEdicion) {
            attributes.addFlashAttribute("mensajeExito", "Usuario actualizado con éxito.");
        } else {
            attributes.addFlashAttribute("mensajeExito", "Usuario creado con éxito.");
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            usuarioService.eliminarUsuario(id);
            attributes.addFlashAttribute("mensajeExito", "Usuario eliminado con éxito.");
        } catch (Exception e) {
            attributes.addFlashAttribute("mensajeError", "No se puede eliminar el usuario.");
        }
        return "redirect:/usuarios";
    }
}
