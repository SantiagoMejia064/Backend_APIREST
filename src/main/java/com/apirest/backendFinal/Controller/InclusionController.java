package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.InclusionModel;
import com.apirest.backendFinal.Model.InclusionId;
import com.apirest.backendFinal.Service.IInclusionService;
import com.apirest.backendFinal.Service.IRetoLecturaService;
import com.apirest.backendFinal.Service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inclusiones")
@CrossOrigin(origins = "*")
public class InclusionController {

    @Autowired
    private IInclusionService inclusionService;

    @Autowired
    private IRetoLecturaService retoService;

    @Autowired
    private ILibroService libroService;

    @PostMapping
    public ResponseEntity<String> crearInclusion(@RequestBody InclusionModel inclusion) {

        Integer idReto  = inclusion.getReto().getIdReto();
        Integer idLibro = inclusion.getLibro().getIdLibro();

        if (!retoService.existsById(idReto)) {
            return ResponseEntity.badRequest().body("El reto no existe");
        }
        if (!libroService.existsById(idLibro)) {
            return ResponseEntity.badRequest().body("El libro no existe");
        }

        InclusionId id = new InclusionId(idReto, idLibro);
        if (inclusionService.existsById(id)) {
            return ResponseEntity.badRequest().body("Ese libro ya está incluido en el reto");
        }

        inclusionService.guardar(inclusion);
        return ResponseEntity.ok("Inclusión creada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<InclusionModel>> listar() {
        return ResponseEntity.ok(inclusionService.listar());
    }

    @GetMapping("/reto/{idReto}")
    public ResponseEntity<List<InclusionModel>> listarPorReto(@PathVariable Integer idReto) {
        return ResponseEntity.ok(inclusionService.listarPorReto(idReto));
    }

    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<InclusionModel>> listarPorLibro(@PathVariable Integer idLibro) {
        return ResponseEntity.ok(inclusionService.listarPorLibro(idLibro));
    }

    @DeleteMapping("/{idReto}/{idLibro}")
    public ResponseEntity<String> eliminar(@PathVariable Integer idReto,
                                           @PathVariable Integer idLibro) {
        InclusionId id = new InclusionId(idReto, idLibro);
        if (!inclusionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        inclusionService.eliminar(id);
        return ResponseEntity.ok("Inclusión eliminada");
    }
}