package com.example.RisProyect.ManejoTurnos.service;

import com.example.RisProyect.ManejoTurnos.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}

