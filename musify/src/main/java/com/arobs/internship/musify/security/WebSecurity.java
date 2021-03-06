package com.arobs.internship.musify.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final InMemoryTokenBlacklist tokenBlacklist;

    @Autowired
    public WebSecurity(InMemoryTokenBlacklist tokenBlacklist) {
        super();
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/**/swagger-resources", "/**/swagger-resources/**", "/**/swagger-ui",
                        "/**/swagger-ui/**", "/**/swagger-ui.html", "/**/swagger-ui.html/**", "/**/v3/api-docs/**").permitAll() // TODO remove /** after testing phase
                .antMatchers(HttpMethod.POST, "/user/register", "/user/login").permitAll()
                .antMatchers(HttpMethod.GET, "/user/403").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/user/403")
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), tokenBlacklist))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }
}
