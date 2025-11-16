package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ArchivoAdjuntoModel;
import java.util.List;
import java.util.Optional;

public interface IArchivoAdjuntoService {

    ArchivoAdjuntoModel guardar(ArchivoAdjuntoModel archivo);

    List<ArchivoAdjuntoModel> listar();

    Optional<ArchivoAdjuntoModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<ArchivoAdjuntoModel> listarPorResenia(Integer idResenia);

    List<ArchivoAdjuntoModel> listarPorReunion(Integer idReunion);
}