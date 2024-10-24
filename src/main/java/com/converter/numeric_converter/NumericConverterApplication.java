package com.converter.numeric_converter;

//Importa las clases necesarias del framework Spring Boot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Anotación que indica que esta clase es la principal de una aplicación Spring Boot
@SpringBootApplication
public class NumericConverterApplication {

	//Metodo principal (entry point) de la aplicación. Es el primero que se ejecuta cuando inicia el programa.
	public static void main(String[] args) {
		//Lanza la aplicación Spring Boot. El metodo run inicializa y configura el entorno completo de Spring Boot.
		SpringApplication.run(NumericConverterApplication.class, args);
	}
}
