package com.educacionit.eventos.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "incripciones")
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "participante_id", nullable = false)
    private Participante participante;

    private LocalDate fechaInscripcion;

    public Inscripcion() {
    }

    public Inscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = LocalDate.now();
    }

    public Inscripcion(Long id, Participante participante, LocalDate fechaInscripcion) {
        this.id = id;
        this.participante = participante;
        this.fechaInscripcion = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", participante=" + participante +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}