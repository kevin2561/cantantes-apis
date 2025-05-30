package com.apirestsql.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestsql.apirest.Entites.Genero;
import com.apirestsql.apirest.Repository.GeneroRepository;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "*")
public class GeneroController {
    @Autowired
    GeneroRepository generoRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getGenero() {
        List<Genero> generos = generoRepository.findAll();

        return ResponseEntity.ok(generos);
    }

}
