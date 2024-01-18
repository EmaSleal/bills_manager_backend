package com.ventas.facturas;

import com.ventas.facturas.model.User;
import com.ventas.facturas.service.AuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))
public class FacturasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturasApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource basedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
        //AllowedOrigins http://192.168.100.111
        configuration.setAllowedOrigins(Arrays.asList("http://192.168.100.111"));
		configuration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", "Accept",
				"Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With", "Access-Control-Request-Method",
				"Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
				"Filename", "AUTHORIZATION"));
		configuration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials",
				"Filename", "AUTHORIZATION"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		basedCorsConfigurationSource.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(basedCorsConfigurationSource);
	}

	//creo un usuario por defecto
	/*@Bean
	CommandLineRunner run(AuthenticationService service) {
		return args -> {
			service.register(new User(null,"sotoleal","sotoleal123","sotoleal1@gmail.com", User.Rol.ADMIN,true,true,true,true));
		};
	}*/

}
