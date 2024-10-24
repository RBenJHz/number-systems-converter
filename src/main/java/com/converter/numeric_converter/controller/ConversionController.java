package com.converter.numeric_converter.controller;

// Importa el servicio de conversión para poder utilizarlo
import com.converter.numeric_converter.service.ConversionService;
// Importa las anotaciones necesarias de Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Anotación que indica que esta clase es un controlador REST de Spring
@RestController
// Define la ruta base para las solicitudes a este controlador
@RequestMapping("/api/converter")
public class ConversionController {

    // Inyección de dependencias del servicio de conversión
    @Autowired
    private ConversionService conversionService;

    // Mapea las solicitudes GET a la ruta "/convert"
    @GetMapping("/convert") // Cambiar de @PostMapping a @GetMapping
    public ResponseEntity<String> convert(
            @RequestParam String number, // Parámetro que representa el número a convertir
            @RequestParam String sourceSystem, // Parámetro que indica el sistema numérico de origen
            @RequestParam String targetSystem) { // Parámetro que indica el sistema numérico de destino

        try {
            // Llama al servicio de conversión para obtener el número convertido
            String convertedNumber = conversionService.convertBetweenSystems(number, sourceSystem, targetSystem);
            // Retorna una respuesta HTTP 200 OK con el número convertido
            return ResponseEntity.ok(convertedNumber);
        } catch (IllegalArgumentException e) {
            // Si se produce una excepción de argumento ilegal, retorna una respuesta HTTP 400 Bad Request con el mensaje de error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
