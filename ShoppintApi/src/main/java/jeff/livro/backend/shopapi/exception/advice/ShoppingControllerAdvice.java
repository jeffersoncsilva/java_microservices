package jeff.livro.backend.shopapi.exception.advice;

import dtos.ErroDTO;
import exceptions.ProductNotFoundException;
import exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "jeff.livro.backend.shopapi.controller")
public class ShoppingControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErroDTO handleProductNotFound(ProductNotFoundException ex){
        ErroDTO dto = new ErroDTO();
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setMessage("Produto não encontrado.");
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErroDTO handleUserNotFound(UserNotFoundException ex){
        ErroDTO dto = new ErroDTO();
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setTimestamp(LocalDateTime.now());
        dto.setMessage(ex.getMessage());
        return dto;
    }

}
