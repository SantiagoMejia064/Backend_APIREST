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
@Table(name = "ArchivoAdjunto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivoAdjuntoModel {

    @Id
    private Integer idArchivo;

    @ManyToOne
    @JoinColumn(name = "idResenia")
    private ReseniaModel resenia;

    @ManyToOne
    @JoinColumn(name = "idReunion")
    private ReunionModel reunion;

    private String tipoArchivo;
    private String nombre;
    private String url;
}
