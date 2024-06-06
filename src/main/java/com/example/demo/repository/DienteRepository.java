package com.example.demo.repository;

import com.example.demo.entity.Diente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DienteRepository extends CrudRepository<Diente, Integer> {
    List<Diente> findByPaciente(int paciente);
}
