package com.mascotas.controller;

import com.mascotas.service.MascotaService;
import com.mascotas.service.dto.mascota.response.MascotaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MascotaViewController {

    private final MascotaService mascotaService;

    @GetMapping("/mascotas")
    public String mostrarMascotas(Model model) {
        List<MascotaResponseDTO> mascotas = mascotaService.findAll();
        model.addAttribute("mascotas", mascotas);
        return "mascotas"; // Nombre del archivo HTML sin la extensión
    }
}

