package com.educacionit.eventos.services;

import com.educacionit.eventos.entities.Inscripcion;
import com.educacionit.eventos.entities.Participante;
import com.educacionit.eventos.repositories.InscripcionRepository;
import com.educacionit.eventos.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private ParticipanteRepository participanteRepository;

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
//    public Inscripcion obtenerInscripcionFecha(LocalDate fechaInscripcion){
//        return inscripcionRepository.findByFecha(fechaInscripcion).orElse(null);
//
//    }

    public List<LocalDate> obtenerFechasInscripcion(String dni) {
        return inscripcionRepository.findByParticipante_Dni(dni)
                .stream()
                .map(Inscripcion::getFechaInscripcion) // Extraemos solo las fechas
                .collect(Collectors.toList());
    }


    ///PRUEBA
    public List<Participante> listarParticipantes() {
        return participanteRepository.findAll();
    }
    public void inscribirParticipante(Long participanteId) {
        Optional<Participante> participanteOpt = participanteRepository.findById(participanteId);

        if (participanteOpt.isPresent()) {
            Inscripcion inscripcion = new Inscripcion();
            inscripcion.setParticipante(participanteOpt.get());
            inscripcion.setFechaInscripcion(LocalDate.now());

            inscripcionRepository.save(inscripcion);
        }
    }

}

