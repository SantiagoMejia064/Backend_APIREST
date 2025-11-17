package com.apirest.backendFinal.Service;

import com.apirest.backendFinal.Exception.RecursoNoEncontradoException;
import com.apirest.backendFinal.Exception.ReglaNegocioException;
import com.apirest.backendFinal.Model.InscripcionModel;
import com.apirest.backendFinal.Model.LibroModel;
import com.apirest.backendFinal.Model.ProgresoRetoModel;
import com.apirest.backendFinal.Repository.IProgresoRetoRepository;
import com.apirest.backendFinal.Repository.IInclusionRepository;
import com.apirest.backendFinal.Repository.IInscripcionRepository;
import com.apirest.backendFinal.Repository.ILibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgresoRetoServiceImp implements IProgresoRetoService {

    @Autowired
    private IProgresoRetoRepository progresoRetoRepository;

    @Autowired
    private IInclusionRepository inclusionRepository;

    @Autowired
    private IInscripcionRepository inscripcionRepo;

    @Autowired
    private ILibroRepository libroRepo;
    
    @Override
    public List<ProgresoRetoModel> listar() {
        return progresoRetoRepository.findAll();
    }

    @Override
    public Optional<ProgresoRetoModel> obtenerPorId(Integer id) {
        return progresoRetoRepository.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        progresoRetoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return progresoRetoRepository.existsById(id);
    }

    @Override
    public boolean libroPerteneceAReto(Integer idLibro, Integer idReto) {
        //Versión más directa usando el exists del repositorio de inclusión
        return inclusionRepository.existsByReto_IdRetoAndLibro_IdLibro(idReto, idLibro);
    }

    @Override
    public List<ProgresoRetoModel> listarPorInscripcion(Integer idInscripcion) {
        return progresoRetoRepository.findByInscripcion_IdInscripcion(idInscripcion);
    }

    @Override
    @Transactional
    public ProgresoRetoModel guardar(ProgresoRetoModel progresoRequest) {

        Integer idInscripcion = progresoRequest.getInscripcion().getIdInscripcion();
        Integer idLibro = progresoRequest.getLibro().getIdLibro();

        //1- Validar que la inscripción exista
        InscripcionModel inscripcion = inscripcionRepo.findById(idInscripcion)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Inscripción no encontrada con id: " + idInscripcion
                ));

        //2- Validar que el libro exista
        LibroModel libro = libroRepo.findById(idLibro)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Libro no encontrado con id: " + idLibro
                ));

        //3- Validamos que el libro esté asociado al reto de la inscripción
        Integer idReto = inscripcion.getRetoLectura().getIdReto();

        boolean libroPerteneceAlReto = inclusionRepository
                .existsByReto_IdRetoAndLibro_IdLibro(idReto, idLibro);

        if (!libroPerteneceAlReto) {
            throw new ReglaNegocioException(
                    "El libro con id " + idLibro +
                    " no está asociado al reto de la inscripción " + idInscripcion
            );
        }

        //4- Buscamos si ya existe un progreso para (inscripción + libro)
        //en IProgresoRetoRepository este método debe devolver List<ProgresoRetoModel>
        //List<ProgresoRetoModel> findByInscripcion_IdInscripcionAndLibro_IdLibro(Integer idInscripcion, Integer idLibro);
        List<ProgresoRetoModel> existentes =
                progresoRetoRepository.findByInscripcion_IdInscripcionAndLibro_IdLibro(idInscripcion, idLibro);

        ProgresoRetoModel progreso;

        if (existentes.isEmpty()) {
            //Como no existía entonces creamos uno nuevo
            progreso = new ProgresoRetoModel();
            progreso.setInscripcion(inscripcion);
            progreso.setLibro(libro);
        } else {
            //Ya existía al menos uno entonces nos quedamos con el más reciente
            //por simplicidad, el último de la lista (pa que no se nos olvide)
            progreso = existentes.get(existentes.size() - 1);

            //Si hay más de uno, opcionalmente podemos limpiar los demás
            if (existentes.size() > 1) {
                for (int i = 0; i < existentes.size() - 1; i++) {
                    progresoRetoRepository.delete(existentes.get(i));
                }
            }
        }

        //Actualizamos el porcentaje, sea nuevo o existente
        progreso.setPorcentajeAvance(progresoRequest.getPorcentajeAvance());

        //5- Siempre actualizamos la fecha
        progreso.setFechaActualizacion(LocalDate.now());

        //El estado lo controla el trigger 'trg_progreso_estado_automatico' en la BD

        return progresoRetoRepository.save(progreso);
    }
}