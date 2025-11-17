package com.apirest.backendFinal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Resenia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReseniaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResenia;

    @ManyToOne
    @JoinColumn(name = "idLibro")
    private LibroModel libro;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioModel usuario;

    private String fecha;
    private String opinion;
    private Integer calificacion;
}
