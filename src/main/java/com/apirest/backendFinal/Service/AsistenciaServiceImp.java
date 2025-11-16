package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.AsistenciaModel;
import com.apirest.backendFinal.Repository.IAsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImp implements IAsistenciaService {

    @Autowired
    private IAsistenciaRepository asistenciaRepository;

    @Override
    public AsistenciaModel guardar(AsistenciaModel asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public List<AsistenciaModel> listar() {
        return asistenciaRepository.findAll();
    }

    @Override
    public Optional<AsistenciaModel> obtenerPorId(Integer id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return asistenciaRepository.existsById(id);
    }

    @Override
    public List<AsistenciaModel> listarPorUsuario(Integer idUsuario) {
        return asistenciaRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<AsistenciaModel> listarPorReunion(Integer idReunion) {
        return asistenciaRepository.findByReunion_IdReunion(idReunion);
    }
}