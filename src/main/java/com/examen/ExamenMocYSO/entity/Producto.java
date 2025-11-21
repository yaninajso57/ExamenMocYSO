package com.examen.ExamenMocYSO.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String categoria;

    @Column
    private float precio;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column
    private String observaciones;

    @Column
    private Integer cantidad;
}
