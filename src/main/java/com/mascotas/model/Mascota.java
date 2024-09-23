package com.mascotas.model;

import com.mascotas.service.dto.mascota.request.MascotaRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "mascotas")
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private String raza;

    private String habilidad;

    private int edad;

    public Mascota(MascotaRequestDTO entity) {
        this.nombre = entity.getNombre();
        this.tipo = entity.getTipo();
        this.raza = entity.getRaza();
        this.habilidad = entity.getHabilidad();
        this.edad = entity.getEdad();
    }



    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", raza='" + raza + '\'' +
                ", habilidad='" + habilidad + '\'' +
                ", edad=" + edad +
                '}';
    }


}
