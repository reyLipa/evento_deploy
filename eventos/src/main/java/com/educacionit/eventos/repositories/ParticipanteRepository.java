package com.educacionit.eventos.repositories;

import com.educacionit.eventos.entities.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}
