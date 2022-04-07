package com.arobs.internship.musify;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.hibernate.HibernateArtistRepository;
import com.arobs.internship.musify.hibernate.HibernateBandRepository;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashSet;

@SpringBootApplication
public class MusifyApplication {
    public static void main(String[] args) {
        //SpringApplication.run(MusifyApplication.class, args);

        HibernateArtistRepository hibernateArtistRepository = new HibernateArtistRepository();
        HibernateBandRepository hibernateBandRepository = new HibernateBandRepository();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Authentication";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
