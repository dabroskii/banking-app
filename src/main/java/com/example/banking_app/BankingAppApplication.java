package com.example.banking_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class BankingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingAppApplication.class, args);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable) // Disables CSRF protection
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/hello").permitAll() // Public access to /hello
						.anyRequest().authenticated() // Secure all other endpoints
				)
				.formLogin(form -> form.defaultSuccessUrl("/", true)); // Default login form with redirect after success
		return http.build();
	}
}
