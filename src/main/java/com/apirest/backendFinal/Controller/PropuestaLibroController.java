package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.PropuestaLibroModel;
import com.apirest.backendFinal.Service.IPropuestaLibroService;
import com.apirest.backendFinal.Service.IUsuarioService;
import com.apirest.backendFinal.Service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propuestas")
@CrossOrigin(origins = "*")
public class PropuestaLibroController {

    @Autowired
    private IPropuestaLibroService propuestaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILibroService libroService;

    @PostMapping
    public ResponseEntity<String> crearPropuesta(@RequestBody PropuestaLibroModel propuesta) {

        Integer idUsuario = propuesta.getUsuario().getIdUsuario();
        Integer idLibro   = propuesta.getLibro().getIdLibro();

        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }

        if (!libroService.existsById(idLibro)) {
            return ResponseEntity.badRequest().body("El libro no existe");
        }

        if (propuesta.getEstado() == null) {
            propuesta.setEstado("propuesto");
        }

        propuestaService.guardar(propuesta);
        return ResponseEntity.ok("Propuesta creada correctamente");
    }

    @GetMapping
    public ResponseEntity<List<PropuestaLibroModel>> listar() {
        return ResponseEntity.ok(propuestaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropuestaLibroModel> obtener(@PathVariable Integer id) {
        Optional<PropuestaLibroModel> prop = propuestaService.obtenerPorId(id);
        return prop.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PropuestaLibroModel>> listarPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(propuestaService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<PropuestaLibroModel>> listarPorLibro(@PathVariable Integer idLibro) {
        return ResponseEntity.ok(propuestaService.listarPorLibro(idLibro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropuestaLibroModel> actualizar(@PathVariable Integer id,
                                                          @RequestBody PropuestaLibroModel datos) {
        if (!propuestaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        datos.setIdPropuesta(id);
        return ResponseEntity.ok(propuestaService.guardar(datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!propuestaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        propuestaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}