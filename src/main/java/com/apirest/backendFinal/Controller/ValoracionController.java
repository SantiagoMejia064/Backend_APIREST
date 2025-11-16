package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.ValoracionModel;
import com.apirest.backendFinal.Model.ValoracionId;
import com.apirest.backendFinal.Service.IValoracionService;
import com.apirest.backendFinal.Service.IReseniaService;
import com.apirest.backendFinal.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/valoraciones")
@CrossOrigin(origins = "*")
public class ValoracionController {

    @Autowired
    private IValoracionService valoracionService;

    @Autowired
    private IReseniaService reseniaService;

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> crearValoracion(@RequestBody ValoracionModel valoracion) {

        Integer idResenia = valoracion.getResenia().getIdResenia();
        Integer idUsuario = valoracion.getUsuario().getIdUsuario();

        if (!reseniaService.existsById(idResenia)) {
            return ResponseEntity.badRequest().body("La reseña no existe");
        }
        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        if (valoracionService.usuarioYaValoro(idUsuario, idResenia)) {
            return ResponseEntity.badRequest().body("El usuario ya valoró esta reseña");
        }

        String utilidad = valoracion.getUtilidad();
        if (!"0".equals(utilidad) && !"1".equals(utilidad)) {
            return ResponseEntity.badRequest().body("La utilidad solo puede ser '0' o '1'");
        }

        valoracionService.guardar(valoracion);
        return ResponseEntity.ok("Valoración registrada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<ValoracionModel>> listar() {
        return ResponseEntity.ok(valoracionService.listar());
    }

    @GetMapping("/resenia/{idResenia}")
    public ResponseEntity<List<ValoracionModel>> listarPorResenia(@PathVariable Integer idResenia) {
        return ResponseEntity.ok(valoracionService.listarPorResenia(idResenia));
    }

    @GetMapping("/{idResenia}/{idUsuario}")
    public ResponseEntity<ValoracionModel> obtener(@PathVariable Integer idResenia,
                                                   @PathVariable Integer idUsuario) {

        ValoracionId id = new ValoracionId(idResenia, idUsuario);
        Optional<ValoracionModel> v = valoracionService.obtenerPorId(id);
        return v.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idResenia}/{idUsuario}")
    public ResponseEntity<String> eliminar(@PathVariable Integer idResenia,
                                           @PathVariable Integer idUsuario) {

        ValoracionId id = new ValoracionId(idResenia, idUsuario);
        if (!valoracionService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        valoracionService.eliminar(id);
        return ResponseEntity.ok("Valoración eliminada");
    }
}