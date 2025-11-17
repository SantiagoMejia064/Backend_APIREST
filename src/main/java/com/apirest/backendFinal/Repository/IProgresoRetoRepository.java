package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ProgresoRetoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProgresoRetoRepository extends JpaRepository<ProgresoRetoModel, Integer> {

    // Progresos por inscripción
    List<ProgresoRetoModel> findByInscripcion_IdInscripcion(Integer idInscripcion);

    // Progresos por libro (dentro de retos)
    List<ProgresoRetoModel> findByLibro_IdLibro(Integer idLibro);

    // Para saber si ya hay progreso para (inscripción + libro)
    Optional<ProgresoRetoModel> findByInscripcion_IdInscripcionAndLibro_IdLibro(
            Integer idInscripcion,
            Integer idLibro
    );
}