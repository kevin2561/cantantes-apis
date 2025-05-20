package com.apirestsql.apirest.util;

import java.time.LocalDate;

public class FechaUtil {

    public static int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        int edad = fechaActual.getYear() - fechaNacimiento.getYear();
        // Con Period.between obtenemos la edad extacta comparando meses
        // int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        return edad;
    }

}
