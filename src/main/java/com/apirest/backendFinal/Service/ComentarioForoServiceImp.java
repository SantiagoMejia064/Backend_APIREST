package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ComentarioForoModel;
import com.apirest.backendFinal.Repository.IComentarioForoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioForoServiceImp implements IComentarioForoService {

    @Autowired
    private IComentarioForoRepository comentarioForoRepository;

    @Override
    public ComentarioForoModel guardar(ComentarioForoModel comentario) {
        return comentarioForoRepository.save(comentario);
    }

    @Override
    public List<ComentarioForoModel> listar() {
        return comentarioForoRepository.findAll();
    }

    @Override
    public Optional<ComentarioForoModel> obtenerPorId(Integer id) {
        return comentarioForoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        comentarioForoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return comentarioForoRepository.existsById(id);
    }

    @Override
    public List<ComentarioForoModel> listarPorForo(Integer idForo) {
        return comentarioForoRepository.findByForo_IdForo(idForo);
    }

    @Override
    public List<ComentarioForoModel> listarPorComentarioPadre(Integer idPadre) {
        return comentarioForoRepository.findByComentarioPadre_IdComentario(idPadre);
    }
}