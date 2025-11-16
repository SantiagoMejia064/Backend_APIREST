package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ComentarioReseniaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentarioReseniaRepository extends JpaRepository<ComentarioReseniaModel, Integer> {

    List<ComentarioReseniaModel> findByResenia_IdResenia(Integer idResenia);

    List<ComentarioReseniaModel> findByUsuario_IdUsuario(Integer idUsuario);
}