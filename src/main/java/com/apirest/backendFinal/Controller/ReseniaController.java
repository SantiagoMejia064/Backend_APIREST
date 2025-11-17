package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ReseniaModel;
import com.apirest.backendFinal.Service.IReseniaService;
import com.apirest.backendFinal.Service.IUsuarioService;
import com.apirest.backendFinal.Service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resenias")
@CrossOrigin(origins = "*")
public class ReseniaController {

    @Autowired
    private IReseniaService reseniaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILibroService libroService;

    @PostMapping
    public ResponseEntity<String> crearResenia(@RequestBody ReseniaModel resenia) {

        Integer idUsuario = resenia.getUsuario().getIdUsuario();
        Integer idLibro   = resenia.getLibro().getIdLibro();

        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
        if (!libroService.existsById(idLibro)) {
            return ResponseEntity.badRequest().body("El libro no existe");
        }

        //Validar calificación 1–5
        if (resenia.getCalificacion() < 1 || resenia.getCalificacion() > 5) {
            return ResponseEntity.badRequest().body("La calificación debe estar entre 1 y 5");
        }

        reseniaService.guardar(resenia);
        return ResponseEntity.ok("Reseña creada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ReseniaModel>> listar() {
        return ResponseEntity.ok(reseniaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReseniaModel> obtener(@PathVariable Integer id) {
        Optional<ReseniaModel> r = reseniaService.obtenerPorId(id);
        return r.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<ReseniaModel>> listarPorLibro(@PathVariable Integer idLibro) {
        return ResponseEntity.ok(reseniaService.listarPorLibro(idLibro));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReseniaModel>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(reseniaService.listarPorUsuario(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id,
                                             @RequestBody ReseniaModel datos) {

        if (!reseniaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        //Validar nuevamente calificación
        if (datos.getCalificacion() < 1 || datos.getCalificacion() > 5) {
            return ResponseEntity.badRequest().body("La calificación debe estar entre 1 y 5");
        }

        datos.setIdResenia(id);
        reseniaService.guardar(datos);
        return ResponseEntity.ok("Reseña actualizada correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!reseniaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reseniaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}