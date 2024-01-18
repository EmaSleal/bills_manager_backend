package com.ventas.facturas.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final FiltroAutorizacionJWT jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final FiltroAutenticacionJWT jwtAuthenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( (authorizeRequests) -> authorizeRequests
                        .requestMatchers("api/Login/**", "/forgot-password/**","/api/facturas/**","/api/productos/**").permitAll()
                ).authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "TRAINER")
                )
                .exceptionHandling( (exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(jwtAuthenFilter)
                ).sessionManagement( (sessionManagement) -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .cors((cors) -> {

                })
                .csrf(AbstractHttpConfigurer::disable);
                /*.authorizeHttpRequests()
                .requestMatchers("/Login/**", "/forgot-password/**").permitAll()
                .requestMatchers(httpSecurity -> httpSecurity.getMethod().equals("GET")).authenticated()
                .requestMatchers("/forgot-password/reset-password").hasAuthority("RESET_PASSWORD")
                .requestMatchers("/Almacenes/**", "/Marcas/**", "/Equipos/**", "/Dashboard").hasAnyAuthority("ADMIN", "USER", "INGENIER")
                .requestMatchers("/Certificaciones/**").hasAnyAuthority("ADMIN", "INGENIER")
                .requestMatchers("/Ciudades/**", "/Paises/**", "/Usuarios/**", "/Auditoria/**").hasAuthority("ADMIN")
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenFilter)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);*/

        return http.build();
    }

}