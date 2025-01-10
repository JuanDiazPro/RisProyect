package com.example.RisProyect.StockEInsumos.service;

import com.example.RisProyect.StockEInsumos.model.InsumosDTO;
import com.example.RisProyect.StockEInsumos.model.Insumos;
import com.example.RisProyect.StockEInsumos.Repository.InsumosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsumosService {

    @Autowired
    private InsumosRepository insumosRepository;

    public List<Insumos> findAll() {
        return insumosRepository.findAll();
    }

    public List<Insumos> findBajoStock() {
        return insumosRepository.findByStockLessThan(5);
    }

    public ResponseEntity<?> decrementarStock(Long id) {
        return insumosRepository.findById(id)
                .map(insumo -> {
                    if (insumo.getStock() > 0) {
                        insumo.setStock(insumo.getStock() - 1);
                        insumosRepository.save(insumo);
                        return ResponseEntity.ok().build();
                    } else {
                        return ResponseEntity.badRequest()
                                .body("No hay stock disponible");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public Insumos save(Insumos insumos) {
        return insumosRepository.save(insumos);
    }

    public ResponseEntity<?> update(Long id, InsumosDTO insumosDTO) {
        return insumosRepository.findById(id)
                .map(insumo -> {
                    insumo.setName(insumosDTO.getName());
                    insumo.setDescripcion(insumosDTO.getDescripcion());
                    insumo.setStock(insumosDTO.getStock());
                    insumosRepository.save(insumo);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> delete(Long id) {
        return insumosRepository.findById(id)
                .map(insumo -> {
                    insumosRepository.delete(insumo);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}