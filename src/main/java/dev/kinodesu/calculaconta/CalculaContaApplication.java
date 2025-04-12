package dev.kinodesu.calculaconta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CalculaContaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculaContaApplication.class, args);
    }

}
