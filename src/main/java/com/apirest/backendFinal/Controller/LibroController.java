package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.LibroModel;
import com.apirest.backendFinal.Service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    //Crear libro
    @PostMapping
    public ResponseEntity<LibroModel> crearLibro(@RequestBody LibroModel libro) {
        LibroModel creado = libroService.guardar(libro);
        return ResponseEntity.ok(creado);
    }

    //Listar todos los libros
    @GetMapping
    public ResponseEntity<List<LibroModel>> listar() {
        return ResponseEntity.ok(libroService.listar());
    }

    //Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<LibroModel> obtener(@PathVariable Integer id) {
        Optional<LibroModel> libro = libroService.obtenerPorId(id);
        return libro.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Buscar por género
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<LibroModel>> buscarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(libroService.listarPorGenero(genero));
    }

    //Actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<LibroModel> actualizar(@PathVariable Integer id,
                                                 @RequestBody LibroModel datos) {
        if (!libroService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        datos.setIdLibro(id);
        return ResponseEntity.ok(libroService.guardar(datos));
    }

    //Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!libroService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}