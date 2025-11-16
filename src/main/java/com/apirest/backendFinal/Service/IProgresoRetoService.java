package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ProgresoRetoModel;
import java.util.List;
import java.util.Optional;

public interface IProgresoRetoService {

    ProgresoRetoModel guardar(ProgresoRetoModel progreso);

    List<ProgresoRetoModel> listar();

    Optional<ProgresoRetoModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    boolean libroPerteneceAReto(Integer idLibro, Integer idReto);

    List<ProgresoRetoModel> listarPorInscripcion(Integer idInscripcion);
}