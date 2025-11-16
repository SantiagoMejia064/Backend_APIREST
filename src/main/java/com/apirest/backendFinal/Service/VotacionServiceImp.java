package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.VotacionModel;
import com.apirest.backendFinal.Repository.IVotacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotacionServiceImp implements IVotacionService {

    @Autowired
    private IVotacionRepository votacionRepository;

    @Override
    public VotacionModel guardar(VotacionModel votacion) {
        return votacionRepository.save(votacion);
    }

    @Override
    public List<VotacionModel> listar() {
        return votacionRepository.findAll();
    }

    @Override
    public Optional<VotacionModel> obtenerPorId(Integer id) {
        return votacionRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        votacionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return votacionRepository.existsById(id);
    }

    @Override
    public boolean existsByUsuarioAndPropuesta(Integer idUsuario, Integer idPropuesta) {
        return votacionRepository.existsByUsuario_IdUsuarioAndPropuesta_IdPropuesta(idUsuario, idPropuesta);
    }

    @Override
    public List<VotacionModel> listarPorPropuesta(Integer idPropuesta) {
        return votacionRepository.findByPropuesta_IdPropuesta(idPropuesta);
    }
}