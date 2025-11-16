package com.apirest.backendFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.backendFinal.Model.ReunionModel;

public interface IReunionRepository extends JpaRepository<ReunionModel, Integer>{

}
