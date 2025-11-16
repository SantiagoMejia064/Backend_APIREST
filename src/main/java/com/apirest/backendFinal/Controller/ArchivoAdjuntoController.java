package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ArchivoAdjuntoModel;
import com.apirest.backendFinal.Service.IArchivoAdjuntoService;
import com.apirest.backendFinal.Service.IReseniaService;
import com.apirest.backendFinal.Service.IReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin(origins = "*")
public class ArchivoAdjuntoController {

    @Autowired
    private IArchivoAdjuntoService archivoService;

    @Autowired
    private IReseniaService reseniaService;

    @Autowired
    private IReunionService reunionService;

    @PostMapping
    public ResponseEntity<String> crearArchivo(@RequestBody ArchivoAdjuntoModel archivo) {

        Integer idResenia = archivo.getResenia().getIdResenia();
        Integer idReunion = archivo.getReunion().getIdReunion();

        if (!reseniaService.existsById(idResenia)) {
            return ResponseEntity.badRequest().body("La reseña asociada no existe");
        }
        if (!reunionService.existsById(idReunion)) {
            return ResponseEntity.badRequest().body("La reunión asociada no existe");
        }

        archivoService.guardar(archivo);
        return ResponseEntity.ok("Archivo adjunto registrado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ArchivoAdjuntoModel>> listar() {
        return ResponseEntity.ok(archivoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoAdjuntoModel> obtener(@PathVariable Integer id) {
        Optional<ArchivoAdjuntoModel> a = archivoService.obtenerPorId(id);
        return a.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/resenia/{idResenia}")
    public ResponseEntity<List<ArchivoAdjuntoModel>> listarPorResenia(@PathVariable Integer idResenia) {
        return ResponseEntity.ok(archivoService.listarPorResenia(idResenia));
    }

    @GetMapping("/reunion/{idReunion}")
    public ResponseEntity<List<ArchivoAdjuntoModel>> listarPorReunion(@PathVariable Integer idReunion) {
        return ResponseEntity.ok(archivoService.listarPorReunion(idReunion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!archivoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        archivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}