package com.educacionit.eventos.repositories;

import com.educacionit.eventos.entities.Inscripcion;
import com.educacionit.eventos.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByParticipanteDni(String dni);
    //Optional<Inscripcion> findByFecha(LocalDate fechaInscripcion);
    List<Inscripcion> findByParticipante_Dni(String dni);
}
