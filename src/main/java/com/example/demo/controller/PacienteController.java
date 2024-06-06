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

import com.example.demo.entity.Paciente;
import com.example.demo.repository.PacienteRepository;

import java.sql.Date;

@Controller
@RequestMapping(path="/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @PostMapping(path="/new")
    public @ResponseBody String nuevo(@RequestParam String nombres, 
            @RequestParam String apellidos, 
            @RequestParam String cedula,
            @RequestParam String telefono, 
            @RequestParam String residencia, 
            @RequestParam String email,
            @RequestParam Date nacimiento) {
        Paciente p = new Paciente();
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setCedula(cedula);
        p.setTelefono(telefono);
        p.setResidencia(residencia);
        p.setEmail(email);
        p.setNacimiento(nacimiento);
        
        pacienteRepository.save(p);
        return "Listo";
    }
    
    @GetMapping("/new_frontend")
    public String mostrarFormularioNuevo() {
        return "formularioNuevoPaciente"; 
    }
    
    @PostMapping("/new_frontend")
    public String nuevo(@RequestParam String nombres,
                        @RequestParam String apellidos,
                        @RequestParam String cedula,
                        @RequestParam String telefono,
                        @RequestParam String residencia,
                        @RequestParam String email,
                        @RequestParam Date nacimiento,
                        Model model) {
        Paciente p = new Paciente();
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setCedula(cedula);
        p.setTelefono(telefono);
        p.setResidencia(residencia);
        p.setEmail(email);
        p.setNacimiento(nacimiento);

        pacienteRepository.save(p);

        model.addAttribute("mensaje", "Paciente creado correctamente");
        return "respuestaCreacionPaciente"; 
    }
    
    @GetMapping(path="/all")
    public @ResponseBody Iterable <Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }

    @GetMapping(path="/all_frontend")
    public String listarTodos_frontend(Model modelo){
        ArrayList<Paciente> lista = (ArrayList<Paciente>) pacienteRepository.findAll();
        modelo.addAttribute("pacientes", lista);
        return "listadoPacientes";
    }
    
    @GetMapping(path="/find")
    public @ResponseBody Paciente buscarId(@RequestParam int id){
        return pacienteRepository.findById(id).orElse(null);
    }
    
    @PostMapping(path="/update/{id}")
    public @ResponseBody String actualizar(@PathVariable int id, 
            @RequestParam String nombres, 
            @RequestParam String apellidos, 
            @RequestParam String cedula,
            @RequestParam String telefono, 
            @RequestParam String residencia, 
            @RequestParam String email,
            @RequestParam Date nacimiento) {
        Paciente p = buscarId(id);
        p.setNombres(nombres);
        p.setApellidos(apellidos);
        p.setCedula(cedula);
        p.setTelefono(telefono);
        p.setResidencia(residencia);
        p.setEmail(email);
        p.setNacimiento(nacimiento);
        pacienteRepository.save(p);
        return "Actualizado";
    }
    
    @GetMapping("/delete/{id}")
    public String mostrarConfirmacionEliminar(@PathVariable int id, Model model) {
        Paciente paciente = pacienteRepository.findById(id).orElse(null);
        model.addAttribute("paciente", paciente);
        return "confirmarEliminarPaciente"; 
    }

    @PostMapping("/delete/{id}")
    public String eliminar(@PathVariable int id) {
        pacienteRepository.deleteById(id);
        return "redirect:/paciente/all_frontend"; 
    }
}
