package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.InscripcionModel;
import com.apirest.backendFinal.Repository.IInscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceImp implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Override
    public InscripcionModel guardar(InscripcionModel inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<InscripcionModel> listar() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Optional<InscripcionModel> obtenerPorId(Integer id) {
        return inscripcionRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        inscripcionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return inscripcionRepository.existsById(id);
    }

    @Override
    public boolean usuarioYaInscrito(Integer idUsuario, Integer idReto) {
        return inscripcionRepository.existsByUsuario_IdUsuarioAndReto_IdReto(idUsuario, idReto);
    }

    @Override
    public List<InscripcionModel> listarPorUsuario(Integer idUsuario) {
        return inscripcionRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<InscripcionModel> listarPorReto(Integer idReto) {
        return inscripcionRepository.findByReto_IdReto(idReto);
    }
}