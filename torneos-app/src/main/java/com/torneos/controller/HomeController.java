package com.torneos.controller;

import java.util.List;
import com.torneos.dto.CanchaDTO;
import com.torneos.dto.EquipoDTO;
import com.torneos.dto.PartidoDTO;
import com.torneos.dto.TorneoDTO;
import com.torneos.dto.UsuarioDTO;
import com.torneos.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final TorneoService torneoService;
    private final EquipoService equipoService;
    private final CanchaService canchaService;
    private final UsuarioService usuarioService;
    private final PartidoService partidoService;

    @Autowired
    public HomeController(TorneoService torneoService,
                          EquipoService equipoService,
                          CanchaService canchaService,
                          UsuarioService usuarioService,
                          PartidoService partidoService) {
        this.torneoService = torneoService;
        this.equipoService = equipoService;
        this.canchaService = canchaService;
        this.usuarioService = usuarioService;
        this.partidoService = partidoService;
    }

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
    public String mostrarIndex(Model model) {
        try {
            List<TorneoDTO> torneos = torneoService.listarTorneos();
            List<EquipoDTO> equipos = equipoService.listarEquipos();
            List<CanchaDTO> canchas = canchaService.listarCanchas();
            List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
            List<PartidoDTO> partidosPendientes = partidoService.listarPartidosPendientes();
            List<PartidoDTO> partidosJugados = partidoService.listarPartidosJugados();

            model.addAttribute("torneos", torneos);
            model.addAttribute("equipos", equipos);
            model.addAttribute("canchas", canchas);
            model.addAttribute("usuarios", usuarios);
            model.addAttribute("partidosPendientes", partidosPendientes);
            model.addAttribute("partidosJugados", partidosJugados);

            model.addAttribute("totalTorneos", torneos.size());
            model.addAttribute("totalEquipos", equipos.size());
            model.addAttribute("totalCanchas", canchas.size());
            model.addAttribute("totalUsuarios", usuarios.size());
            model.addAttribute("totalPartidos", partidosPendientes.size());
            model.addAttribute("totalPartidosJugados", partidosJugados.size());
        } catch (Exception e) {
            // Manejo silencioso
        }
        return "index";
    }

    @GetMapping("/espectador")
    public String mostrarEspectador() {
        return "espectador/homeEspectador";
    }
}
