package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ComentarioForoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IComentarioForoRepository extends JpaRepository<ComentarioForoModel, Integer> {

    List<ComentarioForoModel> findByForo_IdForo(Integer idForo);

    List<ComentarioForoModel> findByComentarioPadre_IdComentario(Integer idComentarioPadre);
}