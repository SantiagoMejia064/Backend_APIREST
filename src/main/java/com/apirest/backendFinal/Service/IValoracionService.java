package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ValoracionModel;
import com.apirest.backendFinal.Model.ValoracionId;
import java.util.List;
import java.util.Optional;

public interface IValoracionService {

    ValoracionModel guardar(ValoracionModel valoracion);

    List<ValoracionModel> listar();

    Optional<ValoracionModel> obtenerPorId(ValoracionId id);

    void eliminar(ValoracionId id);

    boolean existsById(ValoracionId id);

    boolean usuarioYaValoro(Integer idUsuario, Integer idResenia);

    List<ValoracionModel> listarPorResenia(Integer idResenia);
}