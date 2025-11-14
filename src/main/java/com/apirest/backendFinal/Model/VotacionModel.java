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
@Table(name = "Votacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VotacionModel {

    @Id
    private Integer idVotacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "idPropuesta")
    private PropuestaLibroModel propuestaLibro;

    private String fecha;
    private String voto;
}
