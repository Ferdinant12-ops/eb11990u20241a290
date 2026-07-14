package com.whirlpool.care.platform.u20241a290;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main entry point for the Whirlpool Certified Care platform.
 *
 * <p>Enables JPA auditing so that createdAt and updatedAt audit attributes are
 * populated automatically on persistence entities.</p>
 *
 * @author Jose Fernando Flores Pinchi
 */
@EnableJpaAuditing
@SpringBootApplication
public class Eb11990u20241a290Application {
    public static void main(String[] args) {
        SpringApplication.run(Eb11990u20241a290Application.class, args);
    }
}
