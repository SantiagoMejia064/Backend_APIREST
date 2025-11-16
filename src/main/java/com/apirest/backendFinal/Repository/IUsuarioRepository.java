package com.apirest.backendFinal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirest.backendFinal.Model.UsuarioModel;

@Repository
public interface IUsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    // Para buscar por correo si lo necesitas
    UsuarioModel findByCorreoElectronico(String correoElectronico);

    // Para filtrar por rol (lector, moderador, administrador)
    List<UsuarioModel> findByRol(String rol);
}
