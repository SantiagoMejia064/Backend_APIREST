package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ForoModel;
import com.apirest.backendFinal.Repository.IForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForoServiceImp implements IForoService {

    @Autowired
    private IForoRepository foroRepository;

    @Override
    public ForoModel guardar(ForoModel foro) {
        return foroRepository.save(foro);
    }

    @Override
    public List<ForoModel> listar() {
        return foroRepository.findAll();
    }

    @Override
    public Optional<ForoModel> obtenerPorId(Integer id) {
        return foroRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        foroRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return foroRepository.existsById(id);
    }

    @Override
    public List<ForoModel> listarPorUsuario(Integer idUsuario) {
        return foroRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<ForoModel> listarPorCategoria(String categoria) {
        return foroRepository.findByCategoria(categoria);
    }
}