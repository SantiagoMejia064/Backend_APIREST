package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.InscripcionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInscripcionRepository extends JpaRepository<InscripcionModel, Integer> {

    //Para evitar inscripciones duplicadas de "usuario + reto"
    boolean existsByUsuario_IdUsuarioAndRetoLectura_IdReto(Integer idUsuario, Integer idReto);

    //Todas las inscripciones de un usuario
    List<InscripcionModel> findByUsuario_IdUsuario(Integer idUsuario);

    //Todos los inscritos de un reto
    List<InscripcionModel> findByRetoLectura_IdReto(Integer idReto);

}