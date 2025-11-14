package com.apirest.backendFinal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PropuestaLibro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropuestaLibroModel {

    @Id
    private Integer idPropuesta;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "idLibro")
    private LibroModel libro;

    private String fechaPropuesta;
    private String estado;
    private String fechaDesicion;
}
