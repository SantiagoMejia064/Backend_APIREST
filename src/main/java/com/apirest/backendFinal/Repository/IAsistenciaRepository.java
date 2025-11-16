package com.apirest.backendFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.backendFinal.Model.AsistenciaModel;

public interface IAsistenciaRepository extends JpaRepository<AsistenciaModel, Integer>{
    
}
