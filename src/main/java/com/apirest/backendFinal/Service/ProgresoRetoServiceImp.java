package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ProgresoRetoModel;
import com.apirest.backendFinal.Repository.IProgresoRetoRepository;
import com.apirest.backendFinal.Repository.IInclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgresoRetoServiceImp implements IProgresoRetoService {

    @Autowired
    private IProgresoRetoRepository progresoRetoRepository;

    @Autowired
    private IInclusionRepository inclusionRepository;

    @Override
    public ProgresoRetoModel guardar(ProgresoRetoModel progreso) {
        return progresoRetoRepository.save(progreso);
    }

    @Override
    public List<ProgresoRetoModel> listar() {
        return progresoRetoRepository.findAll();
    }

    @Override
    public Optional<ProgresoRetoModel> obtenerPorId(Integer id) {
        return progresoRetoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        progresoRetoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return progresoRetoRepository.existsById(id);
    }

    @Override
    public boolean libroPerteneceAReto(Integer idLibro, Integer idReto) {
        return !inclusionRepository.findByReto_IdReto(idReto)
                .stream()
                .filter(inc -> inc.getLibro().getIdLibro().equals(idLibro))
                .toList()
                .isEmpty();
    }

    @Override
    public List<ProgresoRetoModel> listarPorInscripcion(Integer idInscripcion) {
        return progresoRetoRepository.findByInscripcion_IdInscripcion(idInscripcion);
    }
}