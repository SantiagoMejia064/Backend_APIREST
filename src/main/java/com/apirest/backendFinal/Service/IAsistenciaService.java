package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.AsistenciaModel;
import java.util.List;
import java.util.Optional;

public interface IAsistenciaService {

    AsistenciaModel guardar(AsistenciaModel asistencia);

    List<AsistenciaModel> listar();

    Optional<AsistenciaModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<AsistenciaModel> listarPorUsuario(Integer idUsuario);

    List<AsistenciaModel> listarPorReunion(Integer idReunion);
}