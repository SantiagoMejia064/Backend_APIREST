package com.apirest.backendFinal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RetoLectura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetoLecturaModel {

    @Id
    private Integer idReto;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
}
