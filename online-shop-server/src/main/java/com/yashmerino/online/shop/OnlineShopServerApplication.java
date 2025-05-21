package com.yashmerino.online.shop;

  
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

@SpringBootApplication
public class OnlineShopServerApplication {

    public static void main(String[] args) {SpringApplication.run(OnlineShopServerApplication.class, args);}

}
