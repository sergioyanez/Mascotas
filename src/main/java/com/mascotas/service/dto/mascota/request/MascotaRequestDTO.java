package com.mascotas.service.dto.mascota.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MascotaRequestDTO {

    @NotNull( message = "Los campos nombre, tipo, raza, habilidad y  edad son campos obligatorios.")
    @NotEmpty( message =  "Los campos nombre, tipo, raza, habilidad y  edad son campos obligatorios.")

    private String nombre;

    private String tipo;

    private String raza;

    private String habilidad;

    private int edad;

}
