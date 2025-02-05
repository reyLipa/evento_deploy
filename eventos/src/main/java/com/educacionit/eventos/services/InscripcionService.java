package com.educacionit.eventos.services;

import com.educacionit.eventos.entities.Inscripcion;
import com.educacionit.eventos.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }
    public List<Inscripcion> obtenerInscripcion(){
        return inscripcionRepository.findAll();
    }

    public Inscripcion obtenerInscripcionPorId(Long id){
        return inscripcionRepository.findById(id).orElse(null);

    }

    public void eliminarPorId(Long id){
        inscripcionRepository.deleteById(id);
    }

    public Inscripcion actualizarInscripcion(Inscripcion inscripcionNueva, Long id){

        Inscripcion inscripcionActual=obtenerInscripcionPorId(id);
        inscripcionActual.setFechaInscripcion(inscripcionNueva.getFechaInscripcion());
        inscripcionActual.setParticipante(inscripcionNueva.getParticipante());
        return inscripcionRepository.save(inscripcionActual);

    }

    public boolean verificarIngreso(String dni) {
        return inscripcionRepository.existsByParticipanteDni(dni);
    }
}

