package com.lopez.empleos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfing  implements WebMvcConfigurer{
	
	@Value("${empleos.ruta.imagenes}")
	private String rutaImages;
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/");
		registry.addResourceHandler("/logos/**").addResourceLocations("file:"+rutaImages);
	}
	
}
