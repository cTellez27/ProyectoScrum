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
	            .requestMatchers("/", "/home", "/login", "/registro", "/css/**", "/js/**", "/espectador/**",
					"/torneos", "/torneos/{idTorneo}", "/torneos/{idTorneo}/equipos",
	                "/canchas",
	                "/equipos",
	                "/partidos", "/partidos/torneo/1", "/partidos/pendientes"
				).permitAll()
	            
	            // 2. REGLAS DE ORGANIZADOR (Rutas de administración, creación y mutación)
	            .requestMatchers(
	                "/torneos/nuevo", "/torneos/guardar", "/torneos/editar/**", "/torneos/eliminar/**",
	                "/canchas/nuevo", "/canchas/guardar", "/canchas/editar/**", "/canchas/eliminar/**",
	                "/equipos/nuevo", "/equipos/guardar", "/equipos/editar/**", "/equipos/eliminar/**",
	                "/partidos/torneo/*/guardar", "/partidos/torneo/*/registrar-resultado", "/partidos/torneo/*/eliminar/**",
	                "/equipos/*/jugadores/guardar", "/equipos/*/jugadores/eliminar/**",
	                "/partidos/*/tarjetas/registrar", "/partidos/tarjetas/eliminar/**",
	                "/usuarios/**",
					"/reportes/**"
	            ).hasAuthority("ORGANIZADOR")
	            
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login")
	            .defaultSuccessUrl("/index", true)
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutSuccessUrl("/home")
	            .permitAll()
	        );

	    return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}