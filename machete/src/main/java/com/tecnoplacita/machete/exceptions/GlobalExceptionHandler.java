package com.tecnoplacita.machete.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleException(Exception ex) {
		ex.printStackTrace();
		ProblemDetail errorDetail;

		if (ex instanceof JsonParseException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Formato JSON inválido");
			errorDetail.setProperty("description", "Error en el análisis del JSON: " + ex.getMessage());
			return errorDetail;

		} else if (ex instanceof DisabledException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
			errorDetail.setDetail("Credenciales Inhabilitadas");
			errorDetail.setProperty("description", ex.getMessage().toString());
			return errorDetail;
		} else if (ex instanceof BadCredentialsException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
			errorDetail.setDetail("Credenciales Invalidas");
			errorDetail.setProperty("description",ex.getMessage().toString());
			return errorDetail;
		} else if (ex instanceof CorreoNoEncontrado) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
			errorDetail.setDetail("Correo no encontrado");
			errorDetail.setProperty("description", ex.getMessage().toString());
			return errorDetail;

		}

		else if (ex instanceof InvalidFormatException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Formato inválido");
			errorDetail.setProperty("description", "Error en el formato de datos: " + ex.getMessage());
			return errorDetail;

		} else if (ex instanceof UnrecognizedPropertyException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Propiedad no reconocida");
			errorDetail.setProperty("description",
					"Se encontró una propiedad no válida en el JSON: " + ex.getMessage());
			return errorDetail;

		} else if (ex instanceof IllegalArgumentException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Argumento inválido");
			errorDetail.setProperty("description", ex.getMessage());
			return errorDetail;

		} else if (ex instanceof TokenInvalidException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
			errorDetail.setDetail("El token proporcionado es inválido");
			errorDetail.setProperty("description", ex.getMessage().toString());
			return errorDetail;

		} else if (ex instanceof TokenInvalidResetException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("El token proporcionado es inválido");
			errorDetail.setProperty("description", ex.getMessage().toString());
			return errorDetail;

		}

		else if (ex instanceof HttpRequestMethodNotSupportedException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
			errorDetail.setDetail("Método no permitido");
			errorDetail.setProperty("description",
					"El método HTTP no es compatible con esta solicitud: " + ex.getMessage());
			return errorDetail;

		} else if (ex instanceof IllegalStateException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Estado ilegal");
			errorDetail.setProperty("description", "El estado de la aplicación es ilegal: " + ex.getMessage());
			return errorDetail;

		} else if (ex instanceof HttpMessageNotReadableException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Mensaje HTTP no legible");
			errorDetail.setProperty("description", "El cuerpo de la solicitud no se puede leer");
			return errorDetail;

		} else if (ex instanceof MethodArgumentNotValidException) {
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
			errorDetail.setDetail("Argumento no válido");
			errorDetail.setProperty("description", "Errores de validación en los argumentos: " + ex.getMessage());
			return errorDetail;

		} else if (ex instanceof DataIntegrityViolationException) {
			String message = ex.getMessage();
			String campoAfectado = "campo desconocido"; // Valor predeterminado

			if (message != null && message.contains(":")) {
				String[] parts = message.split(":");
				String campoCompleto = parts[parts.length - 1].trim(); // "com.tecnoplacita.machete.model.User.email"

				if (campoCompleto.contains(".")) {
					String[] subParts = campoCompleto.split("\\.");
					campoAfectado = subParts[subParts.length - 1]; // "email"
				} else {
					campoAfectado = campoCompleto; // Si no tiene puntos, lo tomamos tal cual
				}
			}

			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Faltan Campos");
			errorDetail.setTitle("Bad Request");
			errorDetail.setDetail("Faltan Campos");
			errorDetail.setProperty("description", "Los campos son requeridos: " + campoAfectado);
			return errorDetail;
		}

		// Manejo de otros errores inesperados
		errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		errorDetail.setProperty("description", "Ocurrió un error inesperado");

		return errorDetail;
	}
}
