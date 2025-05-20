package com.apirestsql.apirest.Controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestsql.apirest.Entites.Cantante;
import com.apirestsql.apirest.Repository.CantantesRepository;
import com.apirestsql.apirest.security.jwtSecurity;
import com.apirestsql.apirest.util.FechaUtil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/cantantes")
@CrossOrigin(origins = "*")

public class CantantesController {
    @Autowired
    CantantesRepository cantantesRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCantantes(@RequestHeader("Authorization") String autorHeader) {

        if (autorHeader == null || !autorHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Token no enviado o formato incorrecto");
        }
        String token = autorHeader.replace("Bearer ", "").trim();

        if (!jwtSecurity.tokenValido(token)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        return ResponseEntity.ok(cantantesRepository.findAll());
    }

    @GetMapping("/get/{idCantante}")
    public ResponseEntity<?> cantanteIndivual(@PathVariable Long idCantante,
            @RequestHeader("Authorization") String autorHeader) {
        if (autorHeader == null || !autorHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(400).body("Token no enviado");

        }
        String token = autorHeader.replace("Bearer ", "").trim();

        if (!jwtSecurity.tokenValido(token)) {
            return ResponseEntity.status(401).body("Token invalido o expirado");

        }
        Optional<Cantante> cantanteOPT= cantantesRepository.findById(idCantante); 
        if (cantanteOPT.isPresent()) {
            return ResponseEntity.ok(cantanteOPT.get());

            
        }else{
            return ResponseEntity.status(404).body("Cantante no existe");

        }


    }

    @PostMapping("/post")
    public ResponseEntity<?> crearCantante(@RequestBody Cantante cantante) {

        if (cantante.getNombreArtistico() == null) {
            return ResponseEntity.badRequest().body("El Nombre Artistico es obligatorio.");

        }
        if (cantante.getNombreReal() == null) {
            return ResponseEntity.badRequest().body("El Nombre Real es obligatorio.");

        }
        if (cantante.getFechaNacimiento() == null) {
            return ResponseEntity.badRequest().body("La fecha nacimiento es obligatorio.");

        }
        if (cantante.getFechaNacimiento().isAfter(LocalDate.now())) {
            return ResponseEntity.badRequest().body("La de nacimiento no puede ser futura.");

        }
        if (cantante.getGeneroMusical() == null) {
            return ResponseEntity.badRequest().body("El genero musical es obligatorio.");

        }
        int edad = FechaUtil.calcularEdad(cantante.getFechaNacimiento());
        cantante.setEdad(edad);
        Cantante cantanteCreado = cantantesRepository.save(cantante);

        return ResponseEntity.status(200).body(cantanteCreado);
    }

    @PutMapping("/put/{idCantante}")
    public ResponseEntity<?> actualizarCantante(@PathVariable Long idCantante, @RequestBody Cantante cantante) {

        Cantante cantanteExiste = cantantesRepository.findById(idCantante)
                .orElseThrow(() -> new RuntimeException("El cantante no exite"));

        cantanteExiste.setNombreArtistico(cantante.getNombreArtistico());
        cantanteExiste.setNombreReal(cantante.getNombreReal());
        cantanteExiste.setFechaNacimiento(cantante.getFechaNacimiento());
        cantanteExiste.setPaisOrigen(cantante.getPaisOrigen());
        cantanteExiste.setGeneroMusical(cantante.getGeneroMusical());

        int edad = FechaUtil.calcularEdad(cantante.getFechaNacimiento());
        cantanteExiste.setEdad(edad);

        Cantante cantanteActualizado = cantantesRepository.save(cantanteExiste);
        return ResponseEntity.ok(cantanteActualizado);

    }

    @DeleteMapping("/del/{idCantante}")
    public ResponseEntity<?> eliminarCantante(@PathVariable Long idCantante) {
        Cantante cantanteExite = cantantesRepository.findById(idCantante)
                .orElseThrow(() -> new RuntimeException("El cantante no exite"));

        cantantesRepository.delete(cantanteExite);

        return ResponseEntity.ok("Cantante Eliminado");
    }

}
