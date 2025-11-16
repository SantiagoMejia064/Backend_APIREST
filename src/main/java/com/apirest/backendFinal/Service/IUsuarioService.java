package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.UsuarioModel;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    UsuarioModel guardar(UsuarioModel usuario);

    List<UsuarioModel> listar();

    Optional<UsuarioModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    // Validaciones especiales
    boolean existsByCorreo(String correo);

    List<UsuarioModel> listarPorRol(String rol);
}