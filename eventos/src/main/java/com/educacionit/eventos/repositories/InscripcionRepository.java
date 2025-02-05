package com.educacionit.eventos.repositories;

import com.educacionit.eventos.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    boolean existsByParticipanteDni(String dni);
}
