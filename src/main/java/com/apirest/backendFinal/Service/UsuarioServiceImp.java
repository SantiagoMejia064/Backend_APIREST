package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Model.UsuarioModel;
import com.apirest.backendFinal.Repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UsuarioModel guardar(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<UsuarioModel> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<UsuarioModel> obtenerPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioRepository.existsById(id);
    }

    @Override
    public boolean existsByCorreo(String correo) {
        return usuarioRepository.findByCorreoElectronico(correo) != null;
    }

    @Override
    public List<UsuarioModel> listarPorRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }
}