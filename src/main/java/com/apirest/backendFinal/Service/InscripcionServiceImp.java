package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Exception.RecursoNoEncontradoException;
import com.apirest.backendFinal.Model.InscripcionModel;
import com.apirest.backendFinal.Model.RetoLecturaModel;
import com.apirest.backendFinal.Model.UsuarioModel;
import com.apirest.backendFinal.Repository.IInscripcionRepository;
import com.apirest.backendFinal.Repository.IRetoLecturaRepository;
import com.apirest.backendFinal.Repository.IUsuarioRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceImp implements IInscripcionService {

    @Autowired
    private IInscripcionRepository inscripcionRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IRetoLecturaRepository retoLecturaRepository;

    @Autowired
    private IInscripcionRepository inscripcionRepo;

    @Override
    public InscripcionModel guardar(InscripcionModel inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<InscripcionModel> listar() {
        return inscripcionRepository.findAll();
    }

    @Override
    public Optional<InscripcionModel> obtenerPorId(Integer id) {
        return inscripcionRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        inscripcionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return inscripcionRepository.existsById(id);
    }

    @Override
    public boolean usuarioYaInscrito(Integer idUsuario, Integer idReto) {
        return inscripcionRepository.existsByUsuario_IdUsuarioAndRetoLectura_IdReto(idUsuario, idReto);
    }

    @Override
    public List<InscripcionModel> listarPorUsuario(Integer idUsuario) {
        return inscripcionRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public List<InscripcionModel> listarPorReto(Integer idReto) {
        return inscripcionRepository.findByRetoLectura_IdReto(idReto);
    }

    @Override
    @Transactional
    public InscripcionModel inscribir(InscripcionModel inscripcion) {

        Integer idUsuario = inscripcion.getUsuario().getIdUsuario();
        Integer idReto    = inscripcion.getRetoLectura().getIdReto(); // OJO: retoLectura

        UsuarioModel usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idUsuario));

        //validación de rol
        if (!"lector".equalsIgnoreCase(usuario.getRol())) {
            throw new RuntimeException("Solo usuarios con rol LECTOR pueden inscribirse.");
            
        }

        retoLecturaRepository.findById(idReto)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado con id: " + idReto));

        //Evitar inscripción duplicada
        if (inscripcionRepository.existsByUsuario_IdUsuarioAndRetoLectura_IdReto(idUsuario, idReto)) {
            throw new RuntimeException("El usuario ya está inscrito en este reto.");
        }

        inscripcion.setFecha(LocalDate.now());
        inscripcion.setEstadoInscripcion("activa");

        return inscripcionRepository.save(inscripcion);
    }

    @Override
    @Transactional
    public InscripcionModel cancelarInscripcion(Integer id) {
        InscripcionModel ins = inscripcionRepo.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Inscripción no encontrada: " + id));

        ins.setEstadoInscripcion("cancelada");

        return inscripcionRepo.save(ins);
    }

}