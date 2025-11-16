package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ReunionModel;
import java.util.List;
import java.util.Optional;

public interface IReunionService {

    ReunionModel guardar(ReunionModel reunion);

    List<ReunionModel> listar();

    Optional<ReunionModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<ReunionModel> listarPorLibro(Integer idLibro);
}
