package com.apirest.backendFinal.Repository;

import com.apirest.backendFinal.Model.ArchivoAdjuntoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IArchivoAdjuntoRepository extends JpaRepository<ArchivoAdjuntoModel, Integer> {

    List<ArchivoAdjuntoModel> findByResenia_IdResenia(Integer idResenia);

    List<ArchivoAdjuntoModel> findByReunion_IdReunion(Integer idReunion);
}