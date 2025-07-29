package br.com.marllonbruno.fitnesstracker.api.dto.response;

import java.time.LocalDateTime;


// DTO responsável por padronizar as respostas de erro da API
public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
