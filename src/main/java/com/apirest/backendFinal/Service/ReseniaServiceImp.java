package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ReseniaModel;
import com.apirest.backendFinal.Repository.IReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReseniaServiceImp implements IReseniaService {

    @Autowired
    private IReseniaRepository reseniaRepository;

    @Override
    public ReseniaModel guardar(ReseniaModel resenia) {
        return reseniaRepository.save(resenia);
    }

    @Override
    public List<ReseniaModel> listar() {
        return reseniaRepository.findAll();
    }

    @Override
    public Optional<ReseniaModel> obtenerPorId(Integer id) {
        return reseniaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        reseniaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return reseniaRepository.existsById(id);
    }

    @Override
    public List<ReseniaModel> listarPorLibro(Integer idLibro) {
        return reseniaRepository.findByLibro_IdLibro(idLibro);
    }

    @Override
    public List<ReseniaModel> listarPorUsuario(Integer idUsuario) {
        return reseniaRepository.findByUsuario_IdUsuario(idUsuario);
    }
}