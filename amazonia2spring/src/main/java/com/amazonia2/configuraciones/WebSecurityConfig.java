package com.amazonia2.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	// AUTENTICACIÓN
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails admin = User.withDefaultPasswordEncoder()
			.username("javier").password("contra").roles("ADMIN")
			.build();
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("pepe").password("perez").roles("USER")
			.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

	// AUTORIZACIÓN
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
	        .authorizeHttpRequests(requests -> requests
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/detalle").authenticated()
                .anyRequest().permitAll()
//				.requestMatchers("/", "/home").permitAll()
//				.anyRequest().authenticated()
	        )
	        .exceptionHandling(handling -> handling.accessDeniedPage("/login?noautorizado"))
	        .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
	        )
	        .logout(logout -> logout.permitAll());

		return http.build();
	}
}