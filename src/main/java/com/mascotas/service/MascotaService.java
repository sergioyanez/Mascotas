package com.mascotas.service;

import com.mascotas.repository.MascotaRepository;
import com.mascotas.service.dto.mascota.request.MascotaRequestDTO;
import com.mascotas.model.Mascota;
import com.mascotas.service.dto.mascota.response.MascotaResponseDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MascotaService {
   @Autowired
    private MascotaRepository mascotaRepository;

    @Transactional(readOnly = true)
    public List<MascotaResponseDTO> buscarMascotaPorHabilidad(String tipo, String habilidad)throws Exception{

        var resultado = mascotaRepository.getPerrosPorHabilidadOrderByEdadAsc(tipo, habilidad);
        try{
            return resultado.stream().map(mascota->new MascotaResponseDTO(mascota.getNombre(),mascota.getTipo(),mascota.getRaza(),mascota.getHabilidad(),mascota.getEdad())).collect(Collectors.toList());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public List<MascotaResponseDTO>findAll(){
        return  this.mascotaRepository.findAll().stream().map(MascotaResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public MascotaResponseDTO findById(Long id) throws ChangeSetPersister.NotFoundException {
        return this.mascotaRepository.findById(id)
                .map(MascotaResponseDTO::new)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }



    @Transactional
    public MascotaResponseDTO save(MascotaRequestDTO entity) throws Exception {
        try{
            final var mascota = new Mascota(entity);
            final var result = this.mascotaRepository
                    .save(mascota);
            return new MascotaResponseDTO(result.getNombre(), result.getTipo(),result.getRaza(),result.getHabilidad(),result.getEdad());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public MascotaResponseDTO update(Long id, MascotaRequestDTO entity) throws Exception {
        try {
            Optional<Mascota> entityOpcional = mascotaRepository.findById(id);

            if (!entityOpcional.isPresent()) {
                throw new Exception("Mascota no encontrada con id: " + id);
            }

            Mascota mascota = entityOpcional.get();

            // Actualizamos solo los campos que han cambiado
            mascota.setNombre(entity.getNombre());
            mascota.setRaza(entity.getRaza());
            mascota.setEdad(entity.getEdad());
            mascota.setHabilidad(entity.getHabilidad());
            mascota.setTipo(entity.getTipo());

            // Guardamos los cambios
            mascotaRepository.save(mascota);
            return new MascotaResponseDTO(mascota.getNombre(), mascota.getTipo(), mascota.getRaza(), mascota.getHabilidad(), mascota.getEdad());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Transactional
    public boolean delete(Long id) throws Exception {
        try{
            if(mascotaRepository.existsById(id)){
                mascotaRepository.deleteById(id);
                return true;
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }




}
