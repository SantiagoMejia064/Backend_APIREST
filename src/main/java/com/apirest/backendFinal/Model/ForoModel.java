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
@Table(name = "Foro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForoModel {

    @Id
    private Integer idForo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioModel usuario;

    private String fechaCreacion;
    private String categoria;
    private String descripcion;
}
