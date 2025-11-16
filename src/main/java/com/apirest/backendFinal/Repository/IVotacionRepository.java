package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.VotacionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVotacionRepository extends JpaRepository<VotacionModel, Integer> {

    // Para revisar votos de una propuesta
    List<VotacionModel> findByPropuesta_IdPropuesta(Integer idPropuesta);

    // Para saber si un usuario ya votó una propuesta
    boolean existsByUsuario_IdUsuarioAndPropuesta_IdPropuesta(Integer idUsuario, Integer idPropuesta);
}