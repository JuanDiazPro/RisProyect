package com.example.RisProyect.StockEInsumos.model;


import jakarta.persistence.*;

@Entity
@Table(name = "insumos", indexes = {
        @Index(name = "insumos_name", columnList = "name"),

})
public class Insumos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(80)")
    private String name;

    @Column(name = "descripcion", nullable = false, columnDefinition = "VARCHAR(40)")
    private String descripcion;

    @Column(name = "stock", nullable = false, columnDefinition = "Integer(10)")
    private Integer stock;


    public Insumos(String name, String descripcion, Integer stock) {
        this.name = name;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public Insumos() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
