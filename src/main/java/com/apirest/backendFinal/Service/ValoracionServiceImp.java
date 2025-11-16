package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.ValoracionModel;
import com.apirest.backendFinal.Model.ValoracionId;
import com.apirest.backendFinal.Repository.IValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionServiceImp implements IValoracionService {

    @Autowired
    private IValoracionRepository valoracionRepository;

    @Override
    public ValoracionModel guardar(ValoracionModel valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    public List<ValoracionModel> listar() {
        return valoracionRepository.findAll();
    }

    @Override
    public Optional<ValoracionModel> obtenerPorId(ValoracionId id) {
        return valoracionRepository.findById(id);
    }

    @Override
    public void eliminar(ValoracionId id) {
        valoracionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(ValoracionId id) {
        return valoracionRepository.existsById(id);
    }

    @Override
    public boolean usuarioYaValoro(Integer idUsuario, Integer idResenia) {
        return valoracionRepository.existsByIdReseniaAndIdUsuario(idResenia, idUsuario);
    }

    @Override
    public List<ValoracionModel> listarPorResenia(Integer idResenia) {
        return valoracionRepository.findByIdResenia(idResenia);
    }
}