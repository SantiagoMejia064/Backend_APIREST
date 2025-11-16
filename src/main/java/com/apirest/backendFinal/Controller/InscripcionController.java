package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.InscripcionModel;
import com.apirest.backendFinal.Model.UsuarioModel;
import com.apirest.backendFinal.Service.IInscripcionService;
import com.apirest.backendFinal.Service.IUsuarioService;
import com.apirest.backendFinal.Service.IRetoLecturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    @Autowired
    private IInscripcionService inscripcionService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRetoLecturaService retoLecturaService;

    // Crear inscripción (lector → reto)
    @PostMapping
    public ResponseEntity<String> crearInscripcion(@RequestBody InscripcionModel inscripcion) {

        // Validar existencia de usuario y reto
        Optional<UsuarioModel> usuarioOpt = usuarioService.obtenerPorId(inscripcion.getUsuario().getIdUsuario());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        if (!retoLecturaService.existsById(inscripcion.getRetoLectura().getIdReto())) {
            return ResponseEntity.badRequest().body("El reto no existe");
        }

        UsuarioModel usuario = usuarioOpt.get();

        // Validar rol lector
        if (!"lector".equalsIgnoreCase(usuario.getRol())) {
            return ResponseEntity.badRequest().body("Solo los usuarios con rol 'lector' pueden inscribirse");
        }

        // Validar inscripción no duplicada
        if (inscripcionService.usuarioYaInscrito(usuario.getIdUsuario(),
                inscripcion.getRetoLectura().getIdReto())) {
            return ResponseEntity.badRequest().body("El usuario ya está inscrito en este reto");
        }

        // Estado por defecto: activa (si viene nulo podrías setearlo aquí)
        if (inscripcion.getEstadoInscripcion() == null) {
            inscripcion.setEstadoInscripcion("activa");
        }

        inscripcionService.guardar(inscripcion);
        return ResponseEntity.ok("Inscripción creada correctamente");
    }

    // Listar todas las inscripciones
    @GetMapping
    public ResponseEntity<List<InscripcionModel>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.listar());
    }

    // Obtener inscripción por id
    @GetMapping("/{id}")
    public ResponseEntity<InscripcionModel> obtenerPorId(@PathVariable Integer id) {
        Optional<InscripcionModel> inscripcion = inscripcionService.obtenerPorId(id);
        return inscripcion.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar inscripciones por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<InscripcionModel>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(inscripcionService.listarPorUsuario(idUsuario));
    }

    // Listar inscripciones por reto
    @GetMapping("/reto/{idReto}")
    public ResponseEntity<List<InscripcionModel>> listarPorReto(@PathVariable Integer idReto) {
        return ResponseEntity.ok(inscripcionService.listarPorReto(idReto));
    }

    // "Eliminar" inscripción → cambiar a estado cancelada
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelarInscripcion(@PathVariable Integer id) {

        Optional<InscripcionModel> inscripcionOpt = inscripcionService.obtenerPorId(id);
        if (inscripcionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        InscripcionModel inscripcion = inscripcionOpt.get();
        inscripcion.setEstadoInscripcion("cancelada");
        inscripcionService.guardar(inscripcion);

        return ResponseEntity.ok("Inscripción cambiada a estado 'cancelada' (no se elimina físicamente)");
    }
}