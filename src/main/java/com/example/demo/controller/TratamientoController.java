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

import com.example.demo.entity.Tratamiento;
import com.example.demo.repository.TratamientoRepository;

@Controller
@RequestMapping(path="/tratamiento")
public class TratamientoController {

    @Autowired
    private TratamientoRepository tratamientoRepository;
    
    @PostMapping(path="/new")
    public @ResponseBody String nuevo(@RequestParam int paciente,
            @RequestParam String tipo, 
            @RequestParam String fecha, 
            @RequestParam String procedimientos, 
            @RequestParam String resultados, 
            @RequestParam String medicamentos, 
            @RequestParam String instrucciones) {
        Tratamiento t = new Tratamiento();
        t.setPaciente(paciente);
        t.setTipo(tipo);
        t.setFecha(fecha);
        t.setProcedimientos(procedimientos);
        t.setResultados(resultados);
        t.setMedicamentos(medicamentos);
        t.setInstrucciones(instrucciones);
        
        tratamientoRepository.save(t);
        return "Listo";
    }
    
    @GetMapping("/all")
    public @ResponseBody Iterable <Tratamiento> listarTodos(){
        return tratamientoRepository.findAll();
    }

    @GetMapping("/find")
    public @ResponseBody Tratamiento buscarPorId(@RequestParam int id){
        return tratamientoRepository.findById(id).orElse(null);
    }

    @PostMapping("/update/{id}")
    public @ResponseBody String actualizar(@PathVariable int id, 
            @RequestParam int paciente,
            @RequestParam String tipo, 
            @RequestParam String fecha, 
            @RequestParam String procedimientos, 
            @RequestParam String resultados, 
            @RequestParam String medicamentos, 
            @RequestParam String instrucciones) {
        Tratamiento t = tratamientoRepository.findById(id).orElse(null);
        if (t != null) {
            t.setPaciente(paciente);
            t.setTipo(tipo);
            t.setFecha(fecha);
            t.setProcedimientos(procedimientos);
            t.setResultados(resultados);
            t.setMedicamentos(medicamentos);
            t.setInstrucciones(instrucciones);
            tratamientoRepository.save(t);
            return "Actualizado";
        } else {
            return "No encontrado";
        }
    }
    
    @GetMapping("/delete/{id}")
    public @ResponseBody String eliminar(@PathVariable int id) {
        tratamientoRepository.deleteById(id);
        return "Eliminado";
    }
}
