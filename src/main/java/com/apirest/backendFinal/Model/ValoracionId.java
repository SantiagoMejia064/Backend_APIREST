package com.apirest.backendFinal.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Clave primaria compuesta de la tabla valoracion (idResenia, idUsuario)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValoracionId implements Serializable {

    private Integer idResenia;
    private Integer idUsuario;
}
