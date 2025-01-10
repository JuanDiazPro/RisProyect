package com.example.RisProyect.ReportesYInformes.service;

import com.example.RisProyect.ReportesYInformes.ReportesYInformesRepository;
import com.example.RisProyect.ReportesYInformes.model.ReportesYInformes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportesYInformesService {

    @Autowired
    private ReportesYInformesRepository reportesYInformesRepository;

    public List<ReportesYInformes> obtenerTodos() {
        return reportesYInformesRepository.findAll();
    }

    public ReportesYInformes guardarReporte(ReportesYInformes reporte) {
        return reportesYInformesRepository.save(reporte);
    }

    public void eliminarReporte(Long id) {
        reportesYInformesRepository.deleteById(id);
    }
}

