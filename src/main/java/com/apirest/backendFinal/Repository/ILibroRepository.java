package com.apirest.backendFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.backendFinal.Model.LibroModel;

public interface ILibroRepository extends JpaRepository<LibroModel, Integer>{

}
