package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.RetoLecturaModel;
import com.apirest.backendFinal.Service.IRetoLecturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/retos")
@CrossOrigin(origins = "*")
public class RetoLecturaController {

    @Autowired
    private IRetoLecturaService retoLecturaService;

    //Crear reto
    @PostMapping
    public ResponseEntity<RetoLecturaModel> crearReto(@RequestBody RetoLecturaModel reto) {
        RetoLecturaModel creado = retoLecturaService.guardar(reto);
        return ResponseEntity.ok(creado);
    }

    //Listar todos los retos
    @GetMapping
    public ResponseEntity<List<RetoLecturaModel>> listarRetos() {
        return ResponseEntity.ok(retoLecturaService.listar());
    }

    //Obtener reto por id
    @GetMapping("/{id}")
    public ResponseEntity<RetoLecturaModel> obtenerPorId(@PathVariable Integer id) {
        Optional<RetoLecturaModel> reto = retoLecturaService.obtenerPorId(id);
        return reto.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Actualizar reto
    @PutMapping("/{id}")
    public ResponseEntity<RetoLecturaModel> actualizarReto(@PathVariable Integer id,
                                                           @RequestBody RetoLecturaModel datos) {

        if (!retoLecturaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        datos.setIdReto(id);
        RetoLecturaModel actualizado = retoLecturaService.guardar(datos);
        return ResponseEntity.ok(actualizado);
    }

    //Eliminar reto (solo si no tiene inscritos)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReto(@PathVariable Integer id) {

        if (!retoLecturaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (retoLecturaService.retoTieneInscritos(id)) {
            return ResponseEntity.badRequest()
                    .body("No se puede eliminar el reto porque tiene usuarios inscritos");
        }

        retoLecturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}