package com.apirestsql.apirest.Controllers;

import org.springframework.web.bind.annotation.RestController;

import com.apirestsql.apirest.Entites.Usuario;
import com.apirestsql.apirest.Repository.UsuarioRepository;
import com.apirestsql.apirest.security.jwtSecurity;
import com.apirestsql.apirest.util.FechaUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")

public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/post")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {

        int age = FechaUtil.calcularEdad(usuario.getFechaNacimiento());
        usuario.setEdad(age);

        usuarioRepository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = usuarioRepository.findByUsuarioAndPassword(usuario.getUsuario(),
                usuario.getPassword());

        if (usuarioEncontrado == null) {
            return ResponseEntity.status(400).body("0");

        } else {
            String token = jwtSecurity.generarToken(usuario.getUsuario());
            return ResponseEntity.ok(token);

        }

    }

}
