package com.example.RisProyect.ReportesYInformes.controller;

import com.example.RisProyect.ReportesYInformes.model.ReportesYInformes;
import com.example.RisProyect.ReportesYInformes.model.ReportesYInformesDTO;
import com.example.RisProyect.ReportesYInformes.service.ReportesYInformesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReportesYInformesController {

    @Autowired
    private ReportesYInformesService reportesYInformesService;

    @GetMapping
    public List<ReportesYInformesDTO> obtenerReportes() {
        return reportesYInformesService.obtenerTodos().stream().map(reporte -> {
            ReportesYInformesDTO dto = new ReportesYInformesDTO();
            dto.setTitulo(reporte.getTitulo());
            dto.setDescripcion(reporte.getDescripcion());
            dto.setAutor(reporte.getAutor());
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ReportesYInformes crearReporte(@RequestBody ReportesYInformes reporte) {
        return reportesYInformesService.guardarReporte(reporte);
    }

    @DeleteMapping("/{id}")
    public void eliminarReporte(@PathVariable Long id) {
        reportesYInformesService.eliminarReporte(id);
    }
}

