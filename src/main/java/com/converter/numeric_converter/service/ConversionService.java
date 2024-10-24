package com.converter.numeric_converter.service;

// Importa la anotación Service del framework Spring
import org.springframework.stereotype.Service;

// Importa la clase BigInteger para manejar números enteros grandes
import java.math.BigInteger;

// Anotación que indica que esta clase es un servicio de Spring, lo que permite la inyección de dependencias
@Service
public class ConversionService {
    // Metodo que convierte un número entre sistemas numéricos especificados
    public String convertBetweenSystems(String number, String sourceSystem, String targetSystem) {
        // Verifica si el número es válido para el sistema numérico de entrada
        if (!isValidNumber(number, sourceSystem)) {
            // Lanza una excepción si el número no es válido
            throw new IllegalArgumentException("El número no pertenece al sistema numérico seleccionado");
        }

        // Convierte el número de su sistema numérico de origen a decimal
        BigInteger decimalValue = convertToDecimal(number, sourceSystem);
        // Convierte el valor decimal al sistema numérico de destino y devuelve el resultado
        return convertFromDecimal(decimalValue, targetSystem);
    }

    // Metodo privado que valida si un número es válido para el sistema numérico especificado
    private boolean isValidNumber(String number, String system) {
        switch (system.toLowerCase()) { // Compara el sistema numérico en minúsculas
            case "binary":
                return number.matches("[01]+"); // Solo acepta dígitos 0 y 1
            case "octal":
                return number.matches("[0-7]+"); // Solo acepta dígitos del 0 al 7
            case "decimal":
                return number.matches("[0-9]+"); // Solo acepta dígitos del 0 al 9
            case "hexadecimal":
                return number.matches("[0-9A-fa-f]+"); // Acepta dígitos del 0 al 9 y letras A-F (mayúsculas y minúsculas)
            default:
                return false; // Si el sistema no es válido, retorna falso
        }
    }

    // Metodo privado que convierte un número al sistema decimal desde el sistema numérico de origen
    private BigInteger convertToDecimal(String number, String sourceSystem) {
        switch (sourceSystem.toLowerCase()) {
            case "binary":
                return new BigInteger(number, 2); // Convierte de binario a decimal
            case "octal":
                return new BigInteger(number, 8); // Convierte de octal a decimal
            case "decimal":
                return new BigInteger(number); // Ya está en decimal, solo devuelve el valor
            case "hexadecimal":
                return new BigInteger(number, 16); // Convierte de hexadecimal a decimal
            default:
                // Lanza una excepción si el sistema de origen no es válido
                throw new IllegalArgumentException("Sistema numérico de origen no válido");
        }
    }

    // Metodo privado que convierte un valor decimal a un sistema numérico de destino
    private String convertFromDecimal(BigInteger decimalValue, String targetSystem) {
        switch (targetSystem.toLowerCase()) {
            case "binary":
                return decimalValue.toString(2); // Convierte de decimal a binario
            case "octal":
                return decimalValue.toString(8); // Convierte de decimal a octal
            case "decimal":
                return decimalValue.toString(); // Ya está en decimal, solo devuelve el valor
            case "hexadecimal":
                return decimalValue.toString(16).toUpperCase(); // Convierte de decimal a hexadecimal y lo devuelve en mayúsculas
            default:
                // Lanza una excepción si el sistema de destino no es válido
                throw new IllegalArgumentException("Sistema numérico de origen no válido");
        }
    }
}

