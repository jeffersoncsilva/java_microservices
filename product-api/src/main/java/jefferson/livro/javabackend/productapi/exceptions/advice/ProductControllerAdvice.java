package jefferson.livro.javabackend.productapi.exceptions.advice;

import dtos.ErroDTO;
import exceptions.CategoryNotFoundException;
import exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice(basePackages = "jefferson.livro.javabackend.productapi.controlers")
public class ProductControllerAdvice {

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
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErroDTO handleCategoryNotFound(CategoryNotFoundException ex){
        ErroDTO dto = new ErroDTO();
        dto.setStatus(HttpStatus.NOT_FOUND.value());
        dto.setMessage("Categoria não encontrada.");
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroDTO processValidationError(MethodArgumentNotValidException ex){
        ErroDTO dto = new ErroDTO();
        dto.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = ex.getBindingResult();
        List<FieldError> filedErros = result.getFieldErrors();
        StringBuilder sb = new StringBuilder("Valor inválido para o(s) campo(s):");
        for(FieldError error : filedErros){
            sb.append(" ");
            sb.append(error.getField());
        }
        dto.setMessage(sb.toString());
        dto.setTimestamp(LocalDateTime.now());
        return dto;
    }

}