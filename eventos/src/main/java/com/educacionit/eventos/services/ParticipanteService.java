package com.educacionit.eventos.services;

import com.educacionit.eventos.entities.Inscripcion;
import com.educacionit.eventos.entities.Participante;
import com.educacionit.eventos.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipanteService {
    @Autowired
    private ParticipanteRepository participanteRepository;
    public Participante crearParticipante(Participante participante) {
        return participanteRepository.save(participante);
    }
    public List<Participante> obtenerParticipante(){
        return participanteRepository.findAll();
    }
    public Participante obtenerParticipantePorId(Long id){
        return participanteRepository.findById(id).orElse(null);
    }

    public void eliminarPorId(Long id){
        participanteRepository.deleteById(id);
    }

    public Participante actualizarParticipante(Participante participanteNuevo, Long id){

        Participante participanteActual=obtenerParticipantePorId(id);
        participanteActual.setApellido(participanteNuevo.getApellido());
        participanteActual.setNombre(participanteNuevo.getNombre());
        participanteActual.setDni(participanteNuevo.getDni());
        return participanteRepository.save(participanteActual);

    }

    public Participante obtenerParticipantePorDni(String dni){
        return participanteRepository.findByDni(dni).orElse(null);

    }
    public Participante editar(Long id){
        return participanteRepository.getById(id);
    }


}
