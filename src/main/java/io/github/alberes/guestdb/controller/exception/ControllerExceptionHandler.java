package io.github.alberes.guestdb.controller.exception;

import io.github.alberes.guestdb.controller.dto.FieldErroDto;
import io.github.alberes.guestdb.services.exception.DuplicateRecordException;
import io.github.alberes.guestdb.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StardardError> objectNotFoundException(
            ObjectNotFoundException objectNotFoundException, HttpServletRequest httpServletRequest){
        StardardError stardardError = new StardardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Not found",
                objectNotFoundException.getMessage(),
                httpServletRequest.getRequestURI(),
                List.of()
        );
        return ResponseEntity.status(stardardError.getStatus()).body(stardardError);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<StardardError> duplicateRecordException(
            DuplicateRecordException duplicateRecordException, HttpServletRequest httpServletRequest){
        StardardError stardardError = new StardardError(
                System.currentTimeMillis(),
                HttpStatus.CONFLICT.value(),
                "Conflit",
                duplicateRecordException.getMessage(),
                httpServletRequest.getRequestURI(),
                List.of()
        );
        return ResponseEntity.status(stardardError.getStatus()).body(stardardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StardardError> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                         HttpServletRequest httpServletRequest){
        List<FieldError> fieldErrors = methodArgumentNotValidException.getFieldErrors();
        List<FieldErroDto> fieldErros = fieldErrors.stream().map(
                fe -> new FieldErroDto(fe.getField(), fe.getDefaultMessage())
        ).toList();

        StardardError stardardError = new StardardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                methodArgumentNotValidException.getMessage(),
                httpServletRequest.getRequestURI(),
                fieldErros
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stardardError);
    }
}
