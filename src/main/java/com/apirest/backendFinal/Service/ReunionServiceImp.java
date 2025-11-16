package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ReunionModel;
import com.apirest.backendFinal.Repository.IReunionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReunionServiceImp implements IReunionService {

    @Autowired
    private IReunionRepository reunionRepository;

    @Override
    public ReunionModel guardar(ReunionModel reunion) {
        return reunionRepository.save(reunion);
    }

    @Override
    public List<ReunionModel> listar() {
        return reunionRepository.findAll();
    }

    @Override
    public Optional<ReunionModel> obtenerPorId(Integer id) {
        return reunionRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        reunionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return reunionRepository.existsById(id);
    }

    @Override
    public List<ReunionModel> listarPorLibro(Integer idLibro) {
        return reunionRepository.findByLibro_IdLibro(idLibro);
    }
}