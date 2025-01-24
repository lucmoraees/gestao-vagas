package com.lucasmoraes.gestaovagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TesteUtils {
    public static String objectToJson(Object obj){
        try{
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID companyId, String secret){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String token = JWT.create().withIssuer("lucasmoraes")
                .withExpiresAt(expiresIn)
                .withSubject(companyId.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        return token;
    }
}
