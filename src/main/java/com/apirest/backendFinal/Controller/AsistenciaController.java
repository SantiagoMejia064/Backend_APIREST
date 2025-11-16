package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.AsistenciaModel;
import com.apirest.backendFinal.Service.IAsistenciaService;
import com.apirest.backendFinal.Service.IUsuarioService;
import com.apirest.backendFinal.Service.IReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private IAsistenciaService asistenciaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IReunionService reunionService;

    @PostMapping
    public ResponseEntity<String> registrarAsistencia(@RequestBody AsistenciaModel asistencia) {

        Integer idUsuario = asistencia.getUsuario().getIdUsuario();
        Integer idReunion = asistencia.getReunion().getIdReunion();

        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
        if (!reunionService.existsById(idReunion)) {
            return ResponseEntity.badRequest().body("La reunión no existe");
        }

        asistenciaService.guardar(asistencia);
        return ResponseEntity.ok("Asistencia registrada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<AsistenciaModel>> listar() {
        return ResponseEntity.ok(asistenciaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaModel> obtener(@PathVariable Integer id) {
        Optional<AsistenciaModel> a = asistenciaService.obtenerPorId(id);
        return a.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<AsistenciaModel>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(asistenciaService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/reunion/{idReunion}")
    public ResponseEntity<List<AsistenciaModel>> listarPorReunion(@PathVariable Integer idReunion) {
        return ResponseEntity.ok(asistenciaService.listarPorReunion(idReunion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!asistenciaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        asistenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}