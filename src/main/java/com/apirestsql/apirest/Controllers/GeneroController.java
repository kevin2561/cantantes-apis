package com.apirestsql.apirest.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestsql.apirest.Repository.GeneroRepository;

@RestController
@RequestMapping("/genero")
@CrossOrigin(origins = "*")
public class GeneroController {
    @Autowired
    GeneroRepository generoRepository;

}
