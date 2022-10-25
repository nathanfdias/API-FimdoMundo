package br.org.serratec.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.org.serratec.security.JwtAuthenticationFilter;
import br.org.serratec.security.JwtAuthorizationFilter;
import br.org.serratec.security.JwtUtil;
import br.org.serratec.security.UsuarioDetalheService;


@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	@Autowired
	UsuarioDetalheService usuarioDetalheService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(usuarioDetalheService)
			.passwordEncoder(bCryptEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/").permitAll()
		.antMatchers(HttpMethod.GET, "/produtos").permitAll()
		.antMatchers(HttpMethod.GET, "/usuario", "/perfil","/clientes", "/categorias", "/pedidos", "/itempedidos").hasAuthority("ADMIN")
	   	.antMatchers(HttpMethod.POST, "/usuario", "/perfil","/clientes", "/categorias", "/pedidos", "/itempedidos").hasAuthority("ADMIN")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.cors()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilter(new JwtAuthenticationFilter(this.authenticationManager(), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(this.authenticationManager(), jwtUtil, usuarioDetalheService));
		
	}
	

	  
	   @Bean
	   public CorsConfigurationSource corsConfigurationSource() {
	       CorsConfiguration corsConfiguration = new CorsConfiguration();
	       corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
	       corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
	       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	       source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
	       return source;
	   }
	   
	   @Bean
	   public BCryptPasswordEncoder bCryptEncoder() {
		   return new BCryptPasswordEncoder (); 
	   }
	
}

