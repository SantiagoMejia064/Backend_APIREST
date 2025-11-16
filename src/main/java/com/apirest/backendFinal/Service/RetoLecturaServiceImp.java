package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.RetoLecturaModel;
import com.apirest.backendFinal.Repository.IRetoLecturaRepository;
import com.apirest.backendFinal.Repository.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetoLecturaServiceImp implements IRetoLecturaService {

    @Autowired
    private IRetoLecturaRepository retoLecturaRepository;

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Override
    public RetoLecturaModel guardar(RetoLecturaModel reto) {
        return retoLecturaRepository.save(reto);
    }

    @Override
    public List<RetoLecturaModel> listar() {
        return retoLecturaRepository.findAll();
    }

    @Override
    public Optional<RetoLecturaModel> obtenerPorId(Integer id) {
        return retoLecturaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        retoLecturaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return retoLecturaRepository.existsById(id);
    }

    @Override
    public boolean retoTieneInscritos(Integer idReto) {
        return !inscripcionRepository.findByRetoLectura_IdReto(idReto).isEmpty();
    }
}