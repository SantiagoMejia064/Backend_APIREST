package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.VotacionModel;
import java.util.List;
import java.util.Optional;

public interface IVotacionService {

    VotacionModel guardar(VotacionModel votacion);

    List<VotacionModel> listar();

    Optional<VotacionModel> obtenerPorId(Integer id);

    void eliminar(Integer id);

    boolean existsById(Integer id);

    boolean existsByUsuarioAndPropuesta(Integer idUsuario, Integer idPropuesta);

    List<VotacionModel> listarPorPropuesta(Integer idPropuesta);
}