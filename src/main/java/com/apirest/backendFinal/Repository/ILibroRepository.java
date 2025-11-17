package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.LibroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILibroRepository extends JpaRepository<LibroModel, Integer> {

    //Busca libros por genero de la entidad LibroModel
    List<LibroModel> findByGenero(String genero);
}