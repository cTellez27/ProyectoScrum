package com.torneos.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(requests -> requests
	            // 1. Recursos públicos
	            .requestMatchers("/login", "/registro", "/css/**", "/js/**").permitAll()
	            
	            // 2. REGLAS DE ORGANIZADOR (Rutas que modifican datos)
	            // Solo el organizador puede entrar a nuevo, editar, guardar o eliminar
	            .requestMatchers("/torneos/nuevo", "/torneos/guardar", "/torneos/editar/**", "/torneos/eliminar/**")
	                .hasRole("ORGANIZADOR")
	            
	            // 3. REGLAS GENERALES (Para Espectadores y Organizadores)
	            // Cualquiera logueado puede ver la lista (/torneos) y el detalle
	            .requestMatchers("/reportes/**", "/torneos", "/torneos/**").authenticated()
	            
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/torneos", true)
	            .permitAll()
	        )
	        // ... logout ...
	        ;

	    return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}