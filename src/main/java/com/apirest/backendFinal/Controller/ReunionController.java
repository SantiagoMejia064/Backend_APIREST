package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ReunionModel;
import com.apirest.backendFinal.Service.IReunionService;
import com.apirest.backendFinal.Service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reuniones")
@CrossOrigin(origins = "*")
public class ReunionController {

    @Autowired
    private IReunionService reunionService;

    @Autowired
    private ILibroService libroService;

    @PostMapping
    public ResponseEntity<String> crearReunion(@RequestBody ReunionModel reunion) {

        Integer idLibro = reunion.getLibro().getIdLibro();

        if (!libroService.existsById(idLibro)) {
            return ResponseEntity.badRequest().body("El libro asociado a la reunión no existe");
        }

        reunionService.guardar(reunion);
        return ResponseEntity.ok("Reunión creada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ReunionModel>> listar() {
        return ResponseEntity.ok(reunionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReunionModel> obtener(@PathVariable Integer id) {
        Optional<ReunionModel> r = reunionService.obtenerPorId(id);
        return r.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<ReunionModel>> listarPorLibro(@PathVariable Integer idLibro) {
        return ResponseEntity.ok(reunionService.listarPorLibro(idLibro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReunionModel> actualizar(@PathVariable Integer id,
                                                   @RequestBody ReunionModel datos) {
        if (!reunionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        datos.setIdReunion(id);
        return ResponseEntity.ok(reunionService.guardar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!reunionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reunionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}