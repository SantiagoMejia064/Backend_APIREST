package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ReseniaModel;
import java.util.List;
import java.util.Optional;

public interface IReseniaService {

    ReseniaModel guardar(ReseniaModel resenia);

    List<ReseniaModel> listar();

    Optional<ReseniaModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<ReseniaModel> listarPorLibro(Integer idLibro);

    List<ReseniaModel> listarPorUsuario(Integer idUsuario);
}