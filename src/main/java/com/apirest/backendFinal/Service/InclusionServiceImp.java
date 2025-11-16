package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.InclusionModel;
import com.apirest.backendFinal.Model.InclusionId;
import com.apirest.backendFinal.Repository.IInclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InclusionServiceImp implements IInclusionService {

    @Autowired
    private IInclusionRepository inclusionRepository;

    @Override
    public InclusionModel guardar(InclusionModel inclusion) {
        return inclusionRepository.save(inclusion);
    }

    @Override
    public List<InclusionModel> listar() {
        return inclusionRepository.findAll();
    }

    @Override
    public Optional<InclusionModel> obtenerPorId(InclusionId id) {
        return inclusionRepository.findById(id);
    }

    @Override
    public void eliminar(InclusionId id) {
        inclusionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(InclusionId id) {
        return inclusionRepository.existsById(id);
    }

    @Override
    public List<InclusionModel> listarPorReto(Integer idReto) {
        return inclusionRepository.findByReto_IdReto(idReto);
    }

    @Override
    public List<InclusionModel> listarPorLibro(Integer idLibro) {
        return inclusionRepository.findByLibro_IdLibro(idLibro);
    }
}