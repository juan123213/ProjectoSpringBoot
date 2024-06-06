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
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;



import com.example.demo.entity.Diente;
import com.example.demo.repository.DienteRepository;


import com.example.demo.service.PdfService;
import org.thymeleaf.context.Context;

/*import javax.servlet.http.HttpServletResponse; */
import java.io.IOException;



@Controller
@RequestMapping(path="/diente")
public class DienteController {

    @Autowired
    private DienteRepository dienteRepository;
    
    @Autowired
    private PdfService pdfService;
    
    @PostMapping(path="/new")
    public @ResponseBody String nuevo(@RequestParam int paciente,
            @RequestParam int numero, 
            @RequestParam String notas,
            @RequestParam int cuadrante,
            @RequestParam String estado,
            @RequestParam String imagenUrl) {
        Diente t = new Diente();
        t.setPaciente(paciente);
        t.setNotas(notas);
        t.setNumero(numero);
        t.setCuadrante(cuadrante);
        t.setImagenUrl(imagenUrl);
        t.setEstado(estado);

   
        dienteRepository.save(t);
        return "Listo";
    }
    
    @GetMapping("/by-paciente/{pacienteId}")
    public String listarPorPaciente(@PathVariable int pacienteId, Model model) {
        List<Diente> dientes = dienteRepository.findByPaciente(pacienteId);
        Map<Integer, List<Diente>> dientesPorCuadrante = dientes.stream()
            .collect(Collectors.groupingBy(
                Diente::getCuadrante,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    lista -> {
                        int cuadrante = lista.get(0).getCuadrante();
                        if (cuadrante == 1 || cuadrante == 3) {
                            return lista.stream().sorted(Comparator.comparingInt(Diente::getNumero)).collect(Collectors.toList());
                        } else {
                            return lista.stream().sorted(Comparator.comparingInt(Diente::getNumero).reversed()).collect(Collectors.toList());
                        }
                    }
                )
            ));

        model.addAttribute("dientesPorCuadrante", dientesPorCuadrante);
        model.addAttribute("pacienteId", pacienteId);
        return "dientesPaciente";
    }
    
    @GetMapping("/details/{id}")
    public String showDienteDetails(@PathVariable int id, Model model) {
        Diente diente = dienteRepository.findById(id).orElse(null);
        if (diente == null) {
            return "redirect:/error";
        }
        model.addAttribute("diente", diente);
        return "dienteDetail";
    }
    
    @GetMapping("/all")
    public @ResponseBody Iterable <Diente> listarTodos(){
        return dienteRepository.findAll();
    }

    @GetMapping("/find")
    public @ResponseBody Diente buscarPorId(@RequestParam int id){
        return dienteRepository.findById(id).orElse(null);
    }

    @PostMapping("/update/{id}")
    public @ResponseBody String actualizar(@PathVariable int id, 
            @RequestParam int paciente,
            @RequestParam int numero, 
            @RequestParam int cuadrante,
            @RequestParam String notas,
            @RequestParam String estado,
            @RequestParam String imagenUrl) {
    	Diente t = dienteRepository.findById(id).orElse(null);
        if (t != null) {
            t.setPaciente(paciente);
            t.setNumero(numero);
            t.setNotas(notas);
            t.setCuadrante(cuadrante);
            t.setImagenUrl(imagenUrl);
            t.setEstado(estado);
            
            dienteRepository.save(t);
            return "Actualizado";
        } else {
            return "No encontrado";
        }
    }
    
    @GetMapping("/delete/{id}")
    public @ResponseBody String eliminar(@PathVariable int id) {
        dienteRepository.deleteById(id);
        return "Eliminado";
    }
    
   /* @GetMapping("/export/pdf/{pacienteId}")
    public void exportToPdf(@PathVariable int pacienteId, HttpServletResponse response) throws IOException {
        List<Diente> dientes = dienteRepository.findByPaciente(pacienteId);
        Map<Integer, List<Diente>> dientesPorCuadrante = dientes.stream()
            .collect(Collectors.groupingBy(
                Diente::getCuadrante,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    lista -> {
                        int cuadrante = lista.get(0).getCuadrante();
                        if (cuadrante == 1 || cuadrante == 3) {
                            return lista.stream().sorted(Comparator.comparingInt(Diente::getNumero)).collect(Collectors.toList());
                        } else {
                            return lista.stream().sorted(Comparator.comparingInt(Diente::getNumero).reversed()).collect(Collectors.toList());
                        }
                    }
                )
            ));

        Context context = new Context();
        context.setVariable("dientesPorCuadrante", dientesPorCuadrante);
        context.setVariable("pacienteId", pacienteId);

        byte[] pdfContent = pdfService.generatePdf("dientesPaciente", context);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=odontograma_paciente_" + pacienteId + ".pdf");
        response.getOutputStream().write(pdfContent);
    }*/
}
