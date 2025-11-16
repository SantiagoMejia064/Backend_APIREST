package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.RetoLecturaModel;
import java.util.List;
import java.util.Optional;

public interface IRetoLecturaService {

    RetoLecturaModel guardar(RetoLecturaModel reto);

    List<RetoLecturaModel> listar();

    Optional<RetoLecturaModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    boolean retoTieneInscritos(Integer idReto);
}