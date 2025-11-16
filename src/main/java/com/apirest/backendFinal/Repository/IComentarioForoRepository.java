package com.apirest.backendFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.backendFinal.Model.ComentarioForoModel;

public interface IComentarioForoRepository extends JpaRepository<ComentarioForoModel, Integer>{
    
}
