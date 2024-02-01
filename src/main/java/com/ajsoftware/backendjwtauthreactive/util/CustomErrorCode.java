package com.ajsoftware.backendjwtauthreactive.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {
    // Éxito
    SUCCESS(21,HttpStatus.OK, "Operación exitosa."),
    CREATED(22,HttpStatus.CREATED, "Operación exitosa."),
    NO_CONTENT(23,HttpStatus.NO_CONTENT, "No había datos para devolver."),
    // Errores del cliente (4xx)
    BAD_REQUEST(40,HttpStatus.BAD_REQUEST, "La solicitud no fue válida."),
    USER_NOTFOUND(410,HttpStatus.UNAUTHORIZED, "El usuario no se encuentra registrado."),
    PASS_INCORRECT(411,HttpStatus.UNAUTHORIZED, "Contraseña incorrecta."),
    USER_BLOCKED(412,HttpStatus.UNAUTHORIZED, "Usuario bloqueado, comuniquese con el servicio de soporte tecnico."),
    FORBIDDEN(43,HttpStatus.FORBIDDEN, "Ha intentado acceder a un recurso al que no tiene acceso."),
    NOT_FOUND(44,HttpStatus.NOT_FOUND, "El recurso de destino no existe."),
    METHOD_NOT_ALLOWED(45,HttpStatus.METHOD_NOT_ALLOWED, "El recurso de destino no admite el método HTTP solicitado."),
    NOT_ACCEPTABLE(46,HttpStatus.NOT_ACCEPTABLE, "El recurso de destino no admite el formato de datos solicitado."),
    CONFLICT(49,HttpStatus.NOT_ACCEPTABLE, "se ha detectado un cambio conflictivo durante un intento de modificación de un recurso."),
    UNSUPPORTED_MEDIA_TYPE(415,HttpStatus.UNSUPPORTED_MEDIA_TYPE, "El recurso de destino no admite el formato de datos."),
    // Errores del servidor (5xx)
    INTERNAL_SERVER_ERROR(50,HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado: comuniquese con el servicio de soporte tecnico."),
    JSON_PARSE(51,HttpStatus.INTERNAL_SERVER_ERROR, "Error inesperado al manejar la respuesta: comuniquese con el servicio de soporte tecnico."),


    ;



    private final int code;
    private final String message;
    private final HttpStatus httpCode;

    CustomErrorCode(int code, HttpStatus httpCode, String message) {
        this.code = code;
        this.httpCode = httpCode;
        this.message = message;
    }


}
