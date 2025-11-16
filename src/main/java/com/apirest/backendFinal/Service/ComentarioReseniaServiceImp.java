package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ComentarioReseniaModel;
import com.apirest.backendFinal.Repository.IComentarioReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioReseniaServiceImp implements IComentarioReseniaService {

    @Autowired
    private IComentarioReseniaRepository comentarioResenaRepository;

    @Override
    public ComentarioReseniaModel guardar(ComentarioReseniaModel comentario) {
        return comentarioResenaRepository.save(comentario);
    }

    @Override
    public List<ComentarioReseniaModel> listar() {
        return comentarioResenaRepository.findAll();
    }

    @Override
    public Optional<ComentarioReseniaModel> obtenerPorId(Integer id) {
        return comentarioResenaRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        comentarioResenaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return comentarioResenaRepository.existsById(id);
    }

    @Override
    public List<ComentarioReseniaModel> listarPorResenia(Integer idResenia) {
        return comentarioResenaRepository.findByResenia_IdResenia(idResenia);
    }

    @Override
    public List<ComentarioReseniaModel> listarPorUsuario(Integer idUsuario) {
        return comentarioResenaRepository.findByUsuario_IdUsuario(idUsuario);
    }
}