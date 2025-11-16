package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ComentarioReseniaModel;
import com.apirest.backendFinal.Service.IComentarioReseniaService;
import com.apirest.backendFinal.Service.IReseniaService;
import com.apirest.backendFinal.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios-resenia")
@CrossOrigin(origins = "*")
public class ComentarioReseniaController {

    @Autowired
    private IComentarioReseniaService comentarioResenaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IReseniaService reseniaService;

    @PostMapping
    public ResponseEntity<String> crearComentario(@RequestBody ComentarioReseniaModel comentario) {

        Integer idUsuario = comentario.getUsuario().getIdUsuario();
        Integer idResenia = comentario.getResenia().getIdResenia();

        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
        if (!reseniaService.existsById(idResenia)) {
            return ResponseEntity.badRequest().body("La reseña no existe");
        }

        comentarioResenaService.guardar(comentario);
        return ResponseEntity.ok("Comentario registrado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ComentarioReseniaModel>> listar() {
        return ResponseEntity.ok(comentarioResenaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioReseniaModel> obtener(@PathVariable Integer id) {
        Optional<ComentarioReseniaModel> c = comentarioResenaService.obtenerPorId(id);
        return c.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/resenia/{idResenia}")
    public ResponseEntity<List<ComentarioReseniaModel>> listarPorResenia(@PathVariable Integer idResenia) {
        return ResponseEntity.ok(comentarioResenaService.listarPorResenia(idResenia));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ComentarioReseniaModel>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(comentarioResenaService.listarPorUsuario(idUsuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!comentarioResenaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        comentarioResenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}