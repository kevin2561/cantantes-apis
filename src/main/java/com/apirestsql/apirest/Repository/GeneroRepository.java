package com.apirestsql.apirest.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestsql.apirest.Entites.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

}
