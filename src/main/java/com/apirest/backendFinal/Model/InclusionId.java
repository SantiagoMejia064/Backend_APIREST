package com.apirest.backendFinal.Model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Clave primaria compuesta de Inclusion (idReto, idLibro)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InclusionId implements Serializable {

    private Integer idReto;
    private Integer idLibro;
}
