package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Exception.RecursoNoEncontradoException;
import com.apirest.backendFinal.Model.InscripcionModel;
import com.apirest.backendFinal.Model.ProgresoRetoModel;
import com.apirest.backendFinal.Service.IProgresoRetoService;
import com.apirest.backendFinal.Service.IInscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/progreso")
@CrossOrigin(origins = "*")
public class ProgresoRetoController {

    @Autowired
    private IProgresoRetoService progresoService;

    @Autowired
    private IInscripcionService inscripcionService;

    // Registrar progreso (con validaciones de negocio)
    // ✅ Registrar progreso (versión corregida)
    @PostMapping
    public ResponseEntity<String> registrarProgreso(@RequestBody ProgresoRetoModel progreso) {

        // Validar que vengan los IDs en el JSON
        if (progreso.getInscripcion() == null || progreso.getInscripcion().getIdInscripcion() == null) {
            return ResponseEntity.badRequest().body("Debe enviar 'inscripcion.idInscripcion'.");
        }

        if (progreso.getLibro() == null || progreso.getLibro().getIdLibro() == null) {
            return ResponseEntity.badRequest().body("Debe enviar 'libro.idLibro'.");
        }

        Integer idInscripcion = progreso.getInscripcion().getIdInscripcion();
        Integer idLibro = progreso.getLibro().getIdLibro();

        // 1️⃣ Cargar la inscripción completa desde la BD
        InscripcionModel inscripcion = inscripcionService.obtenerPorId(idInscripcion)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("No existe la inscripción con id: " + idInscripcion));

        // 2️⃣ Sacar el reto desde la inscripción
        // Cambia getReto() por getRetoLectura() si tu entidad se llama así
        Integer idReto = inscripcion.getRetoLectura().getIdReto();

        // 3️⃣ Validar que el libro esté vinculado a ese reto
        if (!progresoService.libroPerteneceAReto(idLibro, idReto)) {
            return ResponseEntity.badRequest()
                    .body("El libro no pertenece al reto asociado a la inscripción.");
        }

        // 4️⃣ Reasociar la inscripción "completa" al progreso (no solo el id)
        progreso.setInscripcion(inscripcion);

        // 5️⃣ Guardar el progreso
        progresoService.guardar(progreso);
        return ResponseEntity.ok("Progreso registrado correctamente");
    }

    // Listar todos los progresos
    @GetMapping
    public ResponseEntity<List<ProgresoRetoModel>> listar() {
        return ResponseEntity.ok(progresoService.listar());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProgresoRetoModel> obtener(@PathVariable Integer id) {
        Optional<ProgresoRetoModel> progreso = progresoService.obtenerPorId(id);
        return progreso.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar por inscripción
    @GetMapping("/inscripcion/{id}")
    public ResponseEntity<List<ProgresoRetoModel>> listarPorInscripcion(@PathVariable Integer id) {
        return ResponseEntity.ok(progresoService.listarPorInscripcion(id));
    }

    // Actualizar progreso
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id,
                                             @RequestBody ProgresoRetoModel datos) {

        if (!progresoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        datos.setIdProgreso(id);
        progresoService.guardar(datos);
        return ResponseEntity.ok("Progreso actualizado correctamente");
    }

    // Eliminar progreso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!progresoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        progresoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // CRUD "crudo" para progreso (sin validaciones extra)
    @PostMapping("/crud")
    public ResponseEntity<ProgresoRetoModel> guardar(@RequestBody ProgresoRetoModel progresoRequest) {
        ProgresoRetoModel guardado = progresoService.guardar(progresoRequest);
        return ResponseEntity.ok(guardado);
    }
}