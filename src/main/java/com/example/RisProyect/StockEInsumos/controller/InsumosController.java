package com.example.RisProyect.StockEInsumos.controller;

import com.example.RisProyect.StockEInsumos.model.InsumosDTO;
import com.example.RisProyect.StockEInsumos.model.Insumos;
import com.example.RisProyect.StockEInsumos.service.InsumosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
public class InsumosController {

    @Autowired
    private InsumosService insumosService;

    @GetMapping
    public List<Insumos> getAllInsumos() {
        return insumosService.findAll();
    }

    @GetMapping("/acabandose")
    public List<Insumos> getInsumosAcabandose() {
        return insumosService.findBajoStock();
    }

    @PostMapping("/tomar/{id}")
    public ResponseEntity<?> tomarInsumo(@PathVariable Long id) {
        return insumosService.decrementarStock(id);
    }

    @PostMapping
    public Insumos createInsumo(@RequestBody InsumosDTO insumosDTO) {
        return insumosService.save(new Insumos(
                insumosDTO.getName(),
                insumosDTO.getDescripcion(),
                insumosDTO.getStock()
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInsumo(@PathVariable Long id, @RequestBody InsumosDTO insumosDTO) {
        return insumosService.update(id, insumosDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInsumo(@PathVariable Long id) {
        return insumosService.delete(id);
    }
}
