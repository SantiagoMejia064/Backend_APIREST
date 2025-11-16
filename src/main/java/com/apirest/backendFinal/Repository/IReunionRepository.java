package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ReunionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReunionRepository extends JpaRepository<ReunionModel, Integer> {

    List<ReunionModel> findByLibro_IdLibro(Integer idLibro);
}