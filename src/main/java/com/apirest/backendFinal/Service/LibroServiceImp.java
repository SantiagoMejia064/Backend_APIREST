package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.LibroModel;
import com.apirest.backendFinal.Repository.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImp implements ILibroService {

    @Autowired
    private ILibroRepository libroRepository;

    @Override
    public LibroModel guardar(LibroModel libro) {
        return libroRepository.save(libro);
    }

    @Override
    public List<LibroModel> listar() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<LibroModel> obtenerPorId(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        libroRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return libroRepository.existsById(id);
    }

    @Override
    public List<LibroModel> listarPorGenero(String genero) {
        return libroRepository.findByGenero(genero);
    }
}