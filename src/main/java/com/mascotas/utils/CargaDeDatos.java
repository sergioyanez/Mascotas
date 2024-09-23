package com.mascotas.utils;

import com.mascotas.model.Mascota;
import com.mascotas.repository.MascotaRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargaDeDatos {

    private final MascotaRepository mascotaRepository;

    @Autowired
    public CargaDeDatos(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public void cargarDatosDesdeCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/java/com/mascotas/csv/mascotas.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {

            for (CSVRecord csvRecord : csvParser) {
                Mascota mascota = new Mascota();
                mascota.setNombre(csvRecord.get("nombre"));
                mascota.setRaza(csvRecord.get("raza"));
                mascota.setEdad(Integer.parseInt(csvRecord.get("edad")));
                mascota.setHabilidad(csvRecord.get("habilidad"));
                mascota.setTipo(csvRecord.get("tipo"));
                mascotaRepository.save(mascota); // Guarda la mascota en la base de datos
            }
        }
    }
//comentario de prueba para clave SSH
}

