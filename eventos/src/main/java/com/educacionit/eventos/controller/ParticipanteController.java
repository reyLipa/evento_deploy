package com.educacionit.eventos.controller;

import com.educacionit.eventos.entities.Inscripcion;
import com.educacionit.eventos.entities.Participante;
import com.educacionit.eventos.services.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/participantes")
@SessionAttributes("participante")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;
@GetMapping
    public String main(Model model){
        List<Participante> participantes=participanteService.obtenerParticipante();
        model.addAttribute("participantes",participantes);
        return "main";
    }

    @GetMapping("/formulario")
    public String formulario(Model model){
    Participante participante=new Participante();
        model.addAttribute("formParticipante", "Formulario de Participantes al Evento");
        model.addAttribute("boton", "Enviar");
        model.addAttribute("participante", participante);

    return "form";
    }
    @PostMapping
    public String alta(Participante participante, Model model, SessionStatus sessionStatus){
        System.out.println("participantes"+ participante);
        participanteService.crearParticipante(participante);
        sessionStatus.setComplete();
        //return "main";
        return "redirect:participantes";
    }
    @GetMapping("/{id}")
    public String eliminar(@PathVariable Long id){
        participanteService.eliminarPorId(id);
        return "redirect:/participantes";
    }

    @GetMapping("/formulario/{id}")
    public String Editar(@PathVariable Long id, Model model) {
        Participante participante = participanteService.editar(id);
        model.addAttribute("participante", participante);
        model.addAttribute("formParticipante", "Edicion de Participante");
        model.addAttribute("boton", "Modificar");
        return "form";

    }


//@GetMapping("/formulario")
//    public String formulario(Model model){
//    Participante participante=new Participante();
//    model.addAttribute("formParticipante", "Formulario de Participantes al Evento");
//    model.addAttribute("participante", participante);
//   return "form";
//    }
//@PostMapping
//    public String alta(Participante participante, Model model){
//        System.out.println("participante");
//        participanteService.crearParticipante(participante);
//        return "redirect:participantes";
//
//    }

    //METODOS CRUD
    @PostMapping("/crearParticipante")
    public Participante crearParticipante(@RequestBody Participante participante){
        return participanteService.crearParticipante(participante);
}
@GetMapping("/obtenerParticipantes")
    public List<Participante> obtenerParticipantes(){
        return participanteService.obtenerParticipante();
    }
    @GetMapping("/obtenerParticipantes/{id}")
    public Participante obtenerParticipantePorId(Long id){
        return participanteService.obtenerParticipantePorId(id);
    }
@GetMapping("/eliminiarPorId")
    public void eliminarPorId(Long id){
        participanteService.eliminarPorId(id);
    }
@GetMapping("/modificarParticipante")
    public Participante actualizarParticipante(Participante participanteNuevo, Long id){
        return participanteService.actualizarParticipante(participanteNuevo, id);
    }
@GetMapping("/obtenerParticipanteDni")
    public Participante obtenerParticipantePorDni(String dni){
        return participanteService.obtenerParticipantePorDni(dni);
    }



    }
