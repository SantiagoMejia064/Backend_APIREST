package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ComentarioForoModel;
import java.util.List;
import java.util.Optional;

public interface IComentarioForoService {

    ComentarioForoModel guardar(ComentarioForoModel comentario);

    List<ComentarioForoModel> listar();

    Optional<ComentarioForoModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<ComentarioForoModel> listarPorForo(Integer idForo);

    List<ComentarioForoModel> listarPorComentarioPadre(Integer idPadre);
}