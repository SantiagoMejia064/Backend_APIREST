package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.InscripcionModel;
import java.util.List;
import java.util.Optional;

public interface IInscripcionService {

    InscripcionModel guardar(InscripcionModel inscripcion);

    List<InscripcionModel> listar();

    Optional<InscripcionModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    boolean usuarioYaInscrito(Integer idUsuario, Integer idReto);

    List<InscripcionModel> listarPorUsuario(Integer idUsuario);

    List<InscripcionModel> listarPorReto(Integer idReto);
}