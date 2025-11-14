package com.apirest.backendFinal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Inclusion")
@IdClass(InclusionId.class)   // PK compuesta
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InclusionModel {

    // Parte 1 de la PK: idReto
    @Id
    @Column(name = "idReto")
    private Integer idReto;

    // Parte 2 de la PK: idLibro
    @Id
    @Column(name = "idLibro")
    private Integer idLibro;

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "idReto", insertable = false, updatable = false)
    private RetoLecturaModel reto;

    @ManyToOne
    @JoinColumn(name = "idLibro", insertable = false, updatable = false)
    private LibroModel libro;
}
