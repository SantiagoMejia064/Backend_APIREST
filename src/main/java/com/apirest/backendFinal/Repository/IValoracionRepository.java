package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ValoracionModel;
import com.apirest.backendFinal.Model.ValoracionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IValoracionRepository extends JpaRepository<ValoracionModel, ValoracionId> {

    // Ver si ya existe valoración usuario+reseña
    boolean existsByIdReseniaAndIdUsuario(Integer idResenia, Integer idUsuario);

    // Todas las valoraciones de una reseña
    List<ValoracionModel> findByIdResenia(Integer idResenia);
}