package com.apirest.backendFinal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Libro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibro;

    private String titulo;
    private String autor;
    private String genero;
    private String anioPublicacion; // Usando String para representar fechas
    private String sinopsis;
    private String portada; // Representa la imagen binaria
    private String estadoLectura;
    private String fechaSeleccion;
}