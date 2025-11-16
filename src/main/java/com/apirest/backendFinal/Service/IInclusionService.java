package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.InclusionModel;
import com.apirest.backendFinal.Model.InclusionId;
import java.util.List;
import java.util.Optional;

public interface IInclusionService {

    InclusionModel guardar(InclusionModel inclusion);

    List<InclusionModel> listar();

    Optional<InclusionModel> obtenerPorId(InclusionId id);

    void eliminar(InclusionId id);

    boolean existsById(InclusionId id);

    List<InclusionModel> listarPorReto(Integer idReto);

    List<InclusionModel> listarPorLibro(Integer idLibro);
}