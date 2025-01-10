package com.example.RisProyect.ManejoTurnos.controller;


import com.example.RisProyect.ManejoTurnos.model.Paciente;
import com.example.RisProyect.ManejoTurnos.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    @PostMapping
    public Paciente savePaciente(@RequestBody Paciente paciente) {
        return pacienteService.savePaciente(paciente);
    }
}
