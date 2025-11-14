package com.apirest.backendFinal.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "valoracion")
@IdClass(ValoracionId.class)   // PK compuesta
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionModel {

    // Parte 1 de la PK: idResenia
    @Id
    @Column(name = "idResenia")
    private Integer idResenia;

    // Parte 2 de la PK: idUsuario
    @Id
    @Column(name = "idUsuario")
    private Integer idUsuario;

    // Relaciones (FK) reutilizando esas mismas columnas
    @ManyToOne
    @JoinColumn(name = "idResenia", insertable = false, updatable = false)
    private ReseniaModel resenia;

    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    private UsuarioModel usuario;

    private String utilidad; // ENUM('0','1') en la BD
}
