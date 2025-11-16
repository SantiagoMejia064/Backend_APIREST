package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.PropuestaLibroModel;
import com.apirest.backendFinal.Repository.IPropuestaLibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropuestaLibroServiceImp implements IPropuestaLibroService {

    @Autowired
    private IPropuestaLibroRepository propuestaLibroRepository;

    @Override
    public PropuestaLibroModel guardar(PropuestaLibroModel propuesta) {
        return propuestaLibroRepository.save(propuesta);
    }

    @Override
    public List<PropuestaLibroModel> listar() {
        return propuestaLibroRepository.findAll();
    }

    @Override
    public Optional<PropuestaLibroModel> obtenerPorId(Integer id) {
        return propuestaLibroRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        propuestaLibroRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return propuestaLibroRepository.existsById(id);
    }

    @Override
    public List<PropuestaLibroModel> listarPorUsuario(Integer idUsuario) {
        return propuestaLibroRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<PropuestaLibroModel> listarPorLibro(Integer idLibro) {
        return propuestaLibroRepository.findByLibro_IdLibro(idLibro);
    }
}