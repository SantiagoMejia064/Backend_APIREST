package com.apirest.backendFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.backendFinal.Model.InscripcionModel;

public interface IInscripcionRepository extends JpaRepository<InscripcionModel, Integer>{

}
