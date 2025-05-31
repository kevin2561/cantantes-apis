package com.apirestsql.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestsql.apirest.Entites.Cantante;
import com.apirestsql.apirest.Entites.Genero;
import com.apirestsql.apirest.Repository.CantantesRepository;
import com.apirestsql.apirest.Repository.GeneroRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "*")
public class GeneroController {
    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    CantantesRepository cantantesRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getGenero() {
        List<Genero> generos = generoRepository.findAll();

        return ResponseEntity.ok(generos);
    }

    @GetMapping("/genero-categoria")
    public ResponseEntity<?> getMethodName(@RequestParam Long idGnero) {
        List<Cantante> cantantes = cantantesRepository.findByGenero_IdGenero(idGnero);
        if(cantantes.isEmpty()){
            return ResponseEntity.status(404).body("Error");

        }
        return ResponseEntity.ok(cantantes);
    }

}
