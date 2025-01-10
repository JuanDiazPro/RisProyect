package com.example.RisProyect.StockEInsumos.model;

public class InsumosDTO {
    private String name;
    private String descripcion;
    private Integer stock;

    public InsumosDTO(String name, String descripcion, Integer stock) {
        this.name = name;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}