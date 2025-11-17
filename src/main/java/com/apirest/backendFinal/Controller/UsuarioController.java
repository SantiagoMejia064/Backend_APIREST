package com.apirest.backendFinal.Controller;

import com.apirest.backendFinal.Model.UsuarioModel;
import com.apirest.backendFinal.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    //Crear usuario
    @PostMapping
    public ResponseEntity<UsuarioModel> crearUsuario(@RequestBody UsuarioModel usuario) {

        // Validar que el correo no exista
        if (usuarioService.existsByCorreo(usuario.getCorreoElectronico())) {
            return ResponseEntity.badRequest().build();
        }

        UsuarioModel creado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(creado);
    }

    //Listar todos
    @GetMapping
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> obtenerPorId(@PathVariable Integer id) {
        Optional<UsuarioModel> usuario = usuarioService.obtenerPorId(id);
        return usuario.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Listar por rol (lector, moderador, administrador)
    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioModel>> listarPorRol(@PathVariable String rol) {
        return ResponseEntity.ok(usuarioService.listarPorRol(rol));
    }

    //Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioModel> actualizarUsuario(@PathVariable Integer id,
                                                          @RequestBody UsuarioModel datos) {

        if (!usuarioService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        datos.setIdUsuario(id);
        UsuarioModel actualizado = usuarioService.guardar(datos);
        return ResponseEntity.ok(actualizado);
    }

    //Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {

        if (!usuarioService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}