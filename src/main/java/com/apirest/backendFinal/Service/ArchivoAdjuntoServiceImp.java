package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ArchivoAdjuntoModel;
import com.apirest.backendFinal.Repository.IArchivoAdjuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivoAdjuntoServiceImp implements IArchivoAdjuntoService {

    @Autowired
    private IArchivoAdjuntoRepository archivoAdjuntoRepository;

    @Override
    public ArchivoAdjuntoModel guardar(ArchivoAdjuntoModel archivo) {
        return archivoAdjuntoRepository.save(archivo);
    }

    @Override
    public List<ArchivoAdjuntoModel> listar() {
        return archivoAdjuntoRepository.findAll();
    }

    @Override
    public Optional<ArchivoAdjuntoModel> obtenerPorId(Integer id) {
        return archivoAdjuntoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        archivoAdjuntoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return archivoAdjuntoRepository.existsById(id);
    }

    @Override
    public List<ArchivoAdjuntoModel> listarPorResenia(Integer idResenia) {
        return archivoAdjuntoRepository.findByResenia_IdResenia(idResenia);
    }

    @Override
    public List<ArchivoAdjuntoModel> listarPorReunion(Integer idReunion) {
        return archivoAdjuntoRepository.findByReunion_IdReunion(idReunion);
    }
}