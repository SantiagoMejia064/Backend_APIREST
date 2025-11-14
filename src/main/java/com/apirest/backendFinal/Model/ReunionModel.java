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
@Table(name = "Reunion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReunionModel {

    @Id
    private Integer idReunion;

    @ManyToOne
    @JoinColumn(name = "idLibro")
    private LibroModel libro;

    private String fecha;
    private String hora;
    private String modalidad;
    private String lugar_o_enlace;
}
