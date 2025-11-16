package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ForoModel;
import java.util.List;
import java.util.Optional;

public interface IForoService {

    ForoModel guardar(ForoModel foro);

    List<ForoModel> listar();

    Optional<ForoModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    List<ForoModel> listarPorUsuario(Integer idUsuario);

    List<ForoModel> listarPorCategoria(String categoria);
}