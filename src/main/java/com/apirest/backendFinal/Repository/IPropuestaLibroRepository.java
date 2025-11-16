package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.PropuestaLibroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPropuestaLibroRepository extends JpaRepository<PropuestaLibroModel, Integer> {

    // Todas las propuestas de un usuario
    List<PropuestaLibroModel> findByUsuario_IdUsuario(Integer idUsuario);

    // Todas las propuestas para un libro
    List<PropuestaLibroModel> findByLibro_IdLibro(Integer idLibro);
}