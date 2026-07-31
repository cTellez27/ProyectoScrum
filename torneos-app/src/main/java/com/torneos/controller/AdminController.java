package com.torneos.controller;

import com.torneos.servicio.TorneoService;
import com.torneos.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public String mostrarPanel(Model model) {
        model.addAttribute("pendientes", usuarioService.listarPendientes());
        model.addAttribute("organizadores", usuarioService.listarOrganizadores());
        model.addAttribute("torneos", torneoService.listarTorneos());
        return "admin_panel";
    }

    @PostMapping("/aprobar/{id}")
    public String aprobarOrganizador(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.aprobarUsuario(id);
            redirectAttributes.addFlashAttribute("exito", "Usuario organizador aprobado con éxito.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al aprobar usuario: " + e.getMessage());
        }
        return "redirect:/admin";
    }

    @PostMapping("/asignar")
    public String asignarTorneo(@RequestParam Long usuarioId,
                                @RequestParam Long torneoId,
                                RedirectAttributes redirectAttributes) {
        try {
            torneoService.asignarOrganizador(torneoId, usuarioId);
            redirectAttributes.addFlashAttribute("exito", "Organizador asignado al torneo correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al asignar organizador: " + e.getMessage());
        }
        return "redirect:/admin";
    }
}
