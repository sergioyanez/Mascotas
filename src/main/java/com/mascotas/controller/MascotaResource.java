package com.mascotas.controller;

import com.mascotas.service.MascotaService;
import com.mascotas.service.dto.mascota.request.MascotaRequestDTO;
import com.mascotas.service.dto.mascota.response.MascotaResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class MascotaResource {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping("")
    public List<MascotaResponseDTO> findAll(){
        return this.mascotaService.findAll();
    }

    @GetMapping("/{id}")
    public MascotaResponseDTO findById( @PathVariable Long id ) throws ChangeSetPersister.NotFoundException {
        return this.mascotaService.findById( id );
    }

    @GetMapping("/{tipo}/{habilidad}")
    public List<MascotaResponseDTO> buscarPerrosPorHabilidad(@PathVariable String tipo, @PathVariable String habilidad){
        try {
            return this.mascotaService.buscarMascotaPorHabilidad(tipo, habilidad);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    public ResponseEntity<MascotaResponseDTO> save(@RequestBody @Valid MascotaRequestDTO entity) throws Exception {
        return ResponseEntity.ok(this.mascotaService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MascotaRequestDTO entity) throws Exception {
        return ResponseEntity.ok(this.mascotaService.update(id, entity));
    }

}
