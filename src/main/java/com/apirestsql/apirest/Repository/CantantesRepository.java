package com.apirestsql.apirest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestsql.apirest.Entites.Cantante;

@Repository
public interface CantantesRepository extends JpaRepository<Cantante, Long> {
    List<Cantante> findByGenero_IdGenero(Long idGenero);

}
