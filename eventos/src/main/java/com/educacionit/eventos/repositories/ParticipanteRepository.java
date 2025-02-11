package com.educacionit.eventos.repositories;

import com.educacionit.eventos.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> findByDni(String dni);
}
