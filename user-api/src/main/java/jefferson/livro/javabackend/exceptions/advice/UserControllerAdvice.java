package jefferson.livro.javabackend.exceptions.advice;

import dtos.ErroDTO;
import exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "jefferson.livro.javabackend.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErroDTO handleUserNotFound(UserNotFoundException ex){
        ErroDTO dto = new ErroDTO();
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setMessage("Usuário não encontrado.");
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }
}
