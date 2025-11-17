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
@Table(name = "ComentarioForo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioForoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "idComentarioPadre")
    private ComentarioForoModel comentarioPadre;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "idForo")
    private ForoModel foro;

    private String fechaPublicacion;
    private String contenido;
}
