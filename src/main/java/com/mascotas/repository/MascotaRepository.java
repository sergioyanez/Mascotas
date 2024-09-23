package com.mascotas.repository;

import com.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("MascotaRepository")
public interface MascotaRepository extends JpaRepository<Mascota,Long>{

    @Query("SELECT m FROM Mascota m WHERE m.tipo = :tipo and m.habilidad = :habilidad ORDER BY m.edad ASC")
    public List<Mascota> getPerrosPorHabilidadOrderByEdadAsc(String tipo, String habilidad);
    List<Mascota> findByHabilidad(String habilidad);

}

