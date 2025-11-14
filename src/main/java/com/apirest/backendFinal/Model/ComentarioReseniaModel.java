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
@Table(name = "ComentarioReseña")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioReseniaModel {

    @Id
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "idResenia")
    private ReseniaModel resenia;

    private String fechaPublicacion;
    private String contenido;
}
