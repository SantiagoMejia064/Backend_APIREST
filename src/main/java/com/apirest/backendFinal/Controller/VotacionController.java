package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.VotacionModel;
import com.apirest.backendFinal.Service.IVotacionService;
import com.apirest.backendFinal.Service.IUsuarioService;
import com.apirest.backendFinal.Service.IPropuestaLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/votaciones")
@CrossOrigin(origins = "*")
public class VotacionController {

    @Autowired
    private IVotacionService votacionService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IPropuestaLibroService propuestaService;

    @PostMapping
    public ResponseEntity<String> crearVotacion(@RequestBody VotacionModel votacion) {

        Integer idUsuario   = votacion.getUsuario().getIdUsuario();
        Integer idPropuesta = votacion.getPropuesta().getIdPropuesta();

        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        if (!propuestaService.existsById(idPropuesta)) {
            return ResponseEntity.badRequest().body("La propuesta no existe");
        }

        if (votacionService.existsByUsuarioAndPropuesta(idUsuario, idPropuesta)) {
            return ResponseEntity.badRequest().body("El usuario ya votó esta propuesta");
        }

        votacionService.guardar(votacion);
        return ResponseEntity.ok("Voto registrado correctamente");
    }

    @GetMapping
    public ResponseEntity<List<VotacionModel>> listar() {
        return ResponseEntity.ok(votacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotacionModel> obtener(@PathVariable Integer id) {
        Optional<VotacionModel> voto = votacionService.obtenerPorId(id);
        return voto.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/propuesta/{idPropuesta}")
    public ResponseEntity<List<VotacionModel>> listarPorPropuesta(@PathVariable Integer idPropuesta) {
        return ResponseEntity.ok(votacionService.listarPorPropuesta(idPropuesta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!votacionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        votacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}