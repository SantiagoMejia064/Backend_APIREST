package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.AsistenciaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAsistenciaRepository extends JpaRepository<AsistenciaModel, Integer> {

    List<AsistenciaModel> findByUsuario_IdUsuario(Integer idUsuario);

    List<AsistenciaModel> findByReunion_IdReunion(Integer idReunion);
}