package com.csse.mycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MycareApplication {

    public static void main(String[] args) {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // or HS384, HS512
//        String encodedKey = java.util.Base64.getEncoder().encodeToString(key.getEncoded());
//        System.out.println("Generated Secure Key: " + encodedKey);
        SpringApplication.run(MycareApplication.class, args);
    }

}
