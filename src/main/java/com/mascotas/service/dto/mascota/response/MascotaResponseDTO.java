package com.mascotas.service.dto.mascota.response;

import com.mascotas.model.Mascota;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MascotaResponseDTO {

    private String nombre;

    private String tipo;

    private String raza;

    private String habilidad;

    private int edad;

    public MascotaResponseDTO(String nombre, String tipo, String raza, String habilidad, int edad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.habilidad = habilidad;
        this.edad = edad;
    }

    public MascotaResponseDTO(Mascota m) {
        this(m.getNombre(), m.getTipo(), m.getRaza(), m.getHabilidad(), m.getEdad());
    }
}
