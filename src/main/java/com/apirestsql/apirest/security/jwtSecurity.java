package com.apirestsql.apirest.security;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class jwtSecurity {
    public static final String claveSecreta = "seguro123";
    public static final long claveExpirada = 86400000;

    public static String generarToken(String usuario) {

        return JWT.create()
                .withSubject(usuario)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + claveExpirada))
                .sign(Algorithm.HMAC256(claveSecreta));
    }

    public static boolean tokenValido(String token) {
        try {
            Algorithm algortimo = Algorithm.HMAC256(claveSecreta);
            JWTVerifier verificador = JWT.require(algortimo).build();
            verificador.verify(token);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;

        }

    }

    public static String obtenerUsuario(String token) {
        Algorithm algoritmo = Algorithm.HMAC256(claveSecreta);
        JWTVerifier verificar = JWT.require(algoritmo).build();
        DecodedJWT decoded = verificar.verify(token);
        return decoded.getSubject();

    }

}
