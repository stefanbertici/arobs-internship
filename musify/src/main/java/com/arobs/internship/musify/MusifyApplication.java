package com.arobs.internship.musify;

import com.arobs.internship.musify.hibernate.HibernateArtistRepository;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.service.ArtistMapperImpl;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class MusifyApplication {
    public static void main(String[] args) {
        //SpringApplication.run(MusifyApplication.class, args);
        //HibernateTest.test();
        HibernateArtistRepository hibernateArtistRepository = new HibernateArtistRepository();
        //HibernateBandRepository hibernateBandRepository = new HibernateBandRepository();

        Artist artist = hibernateArtistRepository.getArtistById(8);
        artist.setFirstName("Updated2");
        artist.setLastName("Updated2");
        artist.setStageName("Updated2");
        hibernateArtistRepository.updateArtist(artist);

        /*Band band = hibernateBandRepository.getBandById(1);
        band.setBandName("D12");
        band.setLocation("Detroit");
        band.setActivityStartDate("1998");
        band.setActivityEndDate("2018");
        hibernateBandRepository.updateBand(band);*/

        //hibernateArtistRepository.deleteArtist(7);
        //hibernateArtistRepository.getArtistById(1);
        //hibernateArtistRepository.addArtist();
        //hibernateArtistRepository.updateArtist();

        //List<Artist> artists = hibernateArtistRepository.getAllArtists();

        /*System.out.println("BAND NAMES OF ARTISTS = ");
        for (Artist artist : artists) {
            for (Band band : artist.getBands()) {
                System.out.println(band.getId() + ", " + band.getBandName());
            }
        }*/
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
