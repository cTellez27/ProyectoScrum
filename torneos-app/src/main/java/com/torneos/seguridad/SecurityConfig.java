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
	            
	            // 2. REGLAS DE ORGANIZADOR (Rutas de administración, creación y mutación)
	            .requestMatchers(
	                "/torneos/nuevo", "/torneos/guardar", "/torneos/editar/**", "/torneos/eliminar/**",
	                "/canchas/nuevo", "/canchas/guardar", "/canchas/editar/**", "/canchas/eliminar/**",
	                "/equipos/nuevo", "/equipos/guardar", "/equipos/editar/**", "/equipos/eliminar/**",
	                "/partidos/torneo/*/guardar", "/partidos/torneo/*/registrar-resultado", "/partidos/torneo/*/eliminar/**",
	                "/equipos/*/jugadores/guardar", "/equipos/*/jugadores/eliminar/**",
	                "/partidos/*/tarjetas/registrar", "/partidos/tarjetas/eliminar/**",
	                "/usuarios/**"
	            ).hasAuthority("ORGANIZADOR")
	            
	            // 3. REGLAS GENERALES (Para Espectadores y Organizadores - Solo lectura y navegación básica)
	            .requestMatchers(
	                "/torneos", "/torneos/**",
	                "/canchas", "/canchas/**",
	                "/equipos", "/equipos/**",
	                "/partidos/**",
	                "/reportes/**"
	            ).authenticated()
	            
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
