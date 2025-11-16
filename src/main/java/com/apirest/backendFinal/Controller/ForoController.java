package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ForoModel;
import com.apirest.backendFinal.Model.UsuarioModel;
import com.apirest.backendFinal.Service.IForoService;
import com.apirest.backendFinal.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/foros")
@CrossOrigin(origins = "*")
public class ForoController {

    @Autowired
    private IForoService foroService;

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> crearForo(@RequestBody ForoModel foro) {

        Integer idUsuario = foro.getUsuario().getIdUsuario();
        Optional<UsuarioModel> usuarioOpt = usuarioService.obtenerPorId(idUsuario);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        UsuarioModel usuario = usuarioOpt.get();
        if (!"moderador".equalsIgnoreCase(usuario.getRol())) {
            return ResponseEntity.badRequest().body("Solo los usuarios con rol 'moderador' pueden crear foros");
        }

        foroService.guardar(foro);
        return ResponseEntity.ok("Foro creado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ForoModel>> listar() {
        return ResponseEntity.ok(foroService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ForoModel> obtener(@PathVariable Integer id) {
        Optional<ForoModel> foro = foroService.obtenerPorId(id);
        return foro.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ForoModel>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(foroService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ForoModel>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(foroService.listarPorCategoria(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ForoModel> actualizar(@PathVariable Integer id,
                                                @RequestBody ForoModel datos) {
        if (!foroService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setIdForo(id);
        return ResponseEntity.ok(foroService.guardar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!foroService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        foroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}