package com.apirestsql.apirest.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.apirestsql.apirest.Entites.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {
//     @Query("select * from usuarios")

//     SELECT 
//     g.idGenero as idGenero, 
// 	g.nombre AS nombreGenero,
// 	g.descripcion as descripcion ,
//     COUNT(c.idCantante) AS cantidadCantantes
// FROM 
//     genero g
// LEFT JOIN 
//     cantantes c ON g.idGenero = c.idGenero
// GROUP BY 
//     g.idGenero, g.nombre, g.descripcion
// ORDER BY 
//     g.idGenero;

}
