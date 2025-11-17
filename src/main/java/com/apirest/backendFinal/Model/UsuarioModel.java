package com.apirest.backendFinal.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    
    private String nombreCompleto;
    private Integer edad;
    private String ocupacion;
    private String correoElectronico;
    private Integer telefono;
    private String rol;  // Asumimos que es un String, ya que el ENUM es solo en la DB
}

