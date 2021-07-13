package com.lopez.empleos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DataBaseWebSecurity  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, estatus from Usuarios where username=?")
		.authoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " +
		"inner join Usuarios u on u.id = up.idUsuario " +
		"inner join Perfiles p on p.id = up.idPerfil " +
		"where u.username = ? ");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//Los recursos estaticos no requieren autenticacion
		.antMatchers(
				"/bootstrap/**",
				"/images/**",
				"/tinymce/**",
				"/logos/**").permitAll()
		//Las vistas publicas no requieren autentificacion
				.antMatchers(
						"/",
						"/signup",
						"/search",
						"/vacantes/view/**").permitAll()
		// Asignar permisos a URLs por ROLES
		.antMatchers("/vacantes/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
		.antMatchers("/categorias/**").hasAnyAuthority("SUPERVISOR","ADMINISTRADOR")
		.antMatchers("/users**").hasAnyAuthority("ADMINISTRADOR")
		// Todas las demas URLs de la aplicacion requieren autentificacion
				.anyRequest().authenticated()
		// El formulario de Login no requiere autentificacion
				.and().formLogin().permitAll();
	}
	
	
}
