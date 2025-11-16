package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ComentarioReseniaModel;
import java.util.List;
import java.util.Optional;

public interface IComentarioReseniaService {

    ComentarioReseniaModel guardar(ComentarioReseniaModel comentario);

    List<ComentarioReseniaModel> listar();

    Optional<ComentarioReseniaModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<ComentarioReseniaModel> listarPorResenia(Integer idResenia);

    List<ComentarioReseniaModel> listarPorUsuario(Integer idUsuario);
}