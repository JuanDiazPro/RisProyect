package com.example.RisProyect.StockEInsumos.Repository;

import com.example.RisProyect.StockEInsumos.model.Insumos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumosRepository extends JpaRepository<Insumos, Long> {
    List<Insumos> findByStockLessThan(Integer stock);
}
