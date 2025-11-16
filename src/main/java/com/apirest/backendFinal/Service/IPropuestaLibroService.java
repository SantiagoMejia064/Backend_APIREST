package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.PropuestaLibroModel;
import java.util.List;
import java.util.Optional;

public interface IPropuestaLibroService {

    PropuestaLibroModel guardar(PropuestaLibroModel propuesta);

    List<PropuestaLibroModel> listar();

    Optional<PropuestaLibroModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<PropuestaLibroModel> listarPorUsuario(Integer idUsuario);

    List<PropuestaLibroModel> listarPorLibro(Integer idLibro);
}