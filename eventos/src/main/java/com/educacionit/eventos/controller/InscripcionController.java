package com.educacionit.eventos.controller;

import com.educacionit.eventos.entities.Inscripcion;
import com.educacionit.eventos.services.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/inscripcion")
public class InscripcionController {
    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping
    public String mostrarFormulario2(Model model) {
        model.addAttribute("participantes", inscripcionService.listarParticipantes());
        model.addAttribute("inscripciones", inscripcionService.obtenerInscripcion());
        return "listas";
    }

    @GetMapping("/verificar")
    public String vericar(){
        return "inscripcion";
    }
    @PostMapping("/crearInscripcion")
    public Inscripcion crearInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.crearInscripcion(inscripcion);
    }
    @GetMapping("/obtenerLista")
    public List<Inscripcion> obtenerInscripcion(){
        return inscripcionService.obtenerInscripcion();
    }
    @GetMapping("/obtenerPorId/{id}")
    public Inscripcion obtenerInscripcionPorId(@PathVariable Long id){
        return inscripcionService.obtenerInscripcionPorId(id);
    }
//    @GetMapping("/obtenerPorId/{fecha}")
//    public Inscripcion obtenerInscripcionFecha(@PathVariable LocalDate fecha){
//        return inscripcionService.obtenerInscripcionFecha(fecha);
//    }
    @DeleteMapping("eliminarInscripcion/{id}")
    public void eliminarPorId(@PathVariable Long id){
        inscripcionService.eliminarPorId(id);
    }

    @PutMapping("/actualizar/{id}")
    public Inscripcion actualizarInscripcion(@RequestBody Inscripcion inscripcionNueva, @PathVariable Long id){
        return inscripcionService.actualizarInscripcion(inscripcionNueva, id);}

//funciona en postmaping
    //    @GetMapping("/verificar-ingreso/{dni}")
    //    public ResponseEntity<String> verificarIngreso(@PathVariable String dni) {
    //        boolean puedeIngresar = inscripcionService.verificarIngreso(dni);
    //        if (puedeIngresar) {
    //            return ResponseEntity.ok("Acceso permitido");
    //        } else {
    //            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso denegado");
    //        }
    //    }

    ////////////vista


    @GetMapping("/verificar-ingreso")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mensaje", "Ingrese su DNI para verificar el acceso.");
//        return "verificar-ingreso";
        return "inscripcion";
    }

//    @PostMapping("/verificar-ingreso")
//    public String verificarIngreso(@RequestParam String dni, Model model) {
//        boolean puedeIngresar = inscripcionService.verificarIngreso(dni);
//
//        if (puedeIngresar) {
//            model.addAttribute("mensaje", "✅ Acceso permitido");
//
//        } else {
//            model.addAttribute("mensaje", "❌ Acceso denegado");
//        }
//
////        return "verificar-ingreso";
//        return "inscripcion";
//    }

    @PostMapping("/verificar-ingreso")
    public String verificarIngreso(@RequestParam String dni, Model model) {
        boolean puedeIngresar = inscripcionService.verificarIngreso(dni);
        List<LocalDate> fechasInscripcion = inscripcionService.obtenerFechasInscripcion(dni);

        if (puedeIngresar) {
            model.addAttribute("mensaje", "✅ Acceso permitido");
        } else {
            model.addAttribute("mensaje", "❌ Acceso denegado");
        }

        model.addAttribute("fechasInscripcion", fechasInscripcion);

        return "inscripcion";

    }

    ///prueba


    @PostMapping("/inscribir")
    public String inscribir(@RequestParam Long participanteId) {
        inscripcionService.inscribirParticipante(participanteId);
        return "redirect:/inscripcion";
    }


}
