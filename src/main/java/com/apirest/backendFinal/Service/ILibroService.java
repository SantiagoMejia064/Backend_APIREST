package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.LibroModel;
import java.util.List;
import java.util.Optional;

public interface ILibroService {

    LibroModel guardar(LibroModel libro);

    List<LibroModel> listar();

    Optional<LibroModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<LibroModel> listarPorGenero(String genero);
}