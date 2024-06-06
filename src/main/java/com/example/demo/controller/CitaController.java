package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.example.demo.entity.Cita;
import com.example.demo.repository.CitaRepository;

@Controller
@RequestMapping(path="/cita")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;
    
    @PostMapping(path="/new")
    public @ResponseBody String nuevo(@RequestParam String fecha, 
            @RequestParam String motivo, 
            @RequestParam String estado) {
        Cita c = new Cita();
        c.setFecha(fecha);
        c.setMotivo(motivo);
        c.setEstado(estado);
        
        citaRepository.save(c);
        return "Listo";
    }
    
    @GetMapping("/all")
    public @ResponseBody Iterable<Cita> listarTodos(){
        return citaRepository.findAll();
    }

    @GetMapping("/find")
    public @ResponseBody Cita buscarPorId(@RequestParam int id){
        return citaRepository.findById(id).orElse(null);
    }

    @PostMapping("/update/{id}")
    public @ResponseBody String actualizar(@PathVariable int id, 
            @RequestParam String fecha, 
            @RequestParam String motivo, 
            @RequestParam String estado) {
        Cita c = citaRepository.findById(id).orElse(null);
        if (c != null) {
            c.setFecha(fecha);
            c.setMotivo(motivo);
            c.setEstado(estado);
            citaRepository.save(c);
            return "Actualizado";
        } else {
            return "No encontrado";
        }
    }
    
    @GetMapping("/delete/{id}")
    public @ResponseBody String eliminar(@PathVariable int id) {
        citaRepository.deleteById(id);
        return "Eliminado";
    }
}
