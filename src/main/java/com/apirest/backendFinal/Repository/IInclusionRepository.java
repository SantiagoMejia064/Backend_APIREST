package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.InclusionModel;
import com.apirest.backendFinal.Model.InclusionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInclusionRepository extends JpaRepository<InclusionModel, InclusionId> {

    List<InclusionModel> findByReto_IdReto(Integer idReto);

    List<InclusionModel> findByLibro_IdLibro(Integer idLibro);
}