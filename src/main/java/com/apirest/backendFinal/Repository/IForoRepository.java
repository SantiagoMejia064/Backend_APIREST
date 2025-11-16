package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ForoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IForoRepository extends JpaRepository<ForoModel, Integer> {

    List<ForoModel> findByUsuario_IdUsuario(Integer idUsuario);

    List<ForoModel> findByCategoria(String categoria);
}