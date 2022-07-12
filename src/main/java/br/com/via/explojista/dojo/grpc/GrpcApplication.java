package br.com.via.explojista.dojo.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(GrpcApplication.class, args);
    }
}


