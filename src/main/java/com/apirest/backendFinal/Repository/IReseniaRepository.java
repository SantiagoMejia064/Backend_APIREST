package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ReseniaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReseniaRepository extends JpaRepository<ReseniaModel, Integer> {

    List<ReseniaModel> findByLibro_IdLibro(Integer idLibro);

    List<ReseniaModel> findByUsuario_IdUsuario(Integer idUsuario);
}