package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ComentarioForoModel;
import com.apirest.backendFinal.Service.IComentarioForoService;
import com.apirest.backendFinal.Service.IUsuarioService;
import com.apirest.backendFinal.Service.IForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comentarios-foro")
@CrossOrigin(origins = "*")
public class ComentarioForoController {

    @Autowired
    private IComentarioForoService comentarioService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IForoService foroService;

    @PostMapping
    public ResponseEntity<String> crearComentario(@RequestBody ComentarioForoModel comentario) {

        Integer idUsuario = comentario.getUsuario().getIdUsuario();
        Integer idForo    = comentario.getForo().getIdForo();

        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
        if (!foroService.existsById(idForo)) {
            return ResponseEntity.badRequest().body("El foro no existe");
        }

        if (comentario.getComentarioPadre() != null) {
            Integer idPadre = comentario.getComentarioPadre().getIdComentario();
            if (!comentarioService.existsById(idPadre)) {
                return ResponseEntity.badRequest().body("El comentario padre no existe");
            }
        }

        comentarioService.guardar(comentario);
        return ResponseEntity.ok("Comentario creado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ComentarioForoModel>> listar() {
        return ResponseEntity.ok(comentarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioForoModel> obtener(@PathVariable Integer id) {
        Optional<ComentarioForoModel> c = comentarioService.obtenerPorId(id);
        return c.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/foro/{idForo}")
    public ResponseEntity<List<ComentarioForoModel>> listarPorForo(@PathVariable Integer idForo) {
        return ResponseEntity.ok(comentarioService.listarPorForo(idForo));
    }

    @GetMapping("/padre/{idPadre}")
    public ResponseEntity<List<ComentarioForoModel>> listarPorPadre(@PathVariable Integer idPadre) {
        return ResponseEntity.ok(comentarioService.listarPorComentarioPadre(idPadre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!comentarioService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        comentarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}