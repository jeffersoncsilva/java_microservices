package jefferson.livro.javabackend.controller;

import api.response.ApiResponse;
import dtos.login.ErroLoginDTO;
import dtos.login.UserLoginDTO;
import dtos.login.UserTokenDTO;
import exceptions.UsuarioLoginOrPasswordWrongException;
import jakarta.validation.Valid;
import jefferson.livro.javabackend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> realizaLogin(@RequestBody @Valid UserLoginDTO login){
        try{
            var token =  loginService.realizaLogin(login);
            return ResponseEntity.ok(new ApiResponse<>(token, "Login feito com sucesso", HttpStatus.OK.value(), LocalDateTime.now()));
        }catch (UsuarioLoginOrPasswordWrongException ex){
            var erro = new ErroLoginDTO();
            erro.setMessage(ex.getMessage());
            erro.setStatus(HttpStatus.UNAUTHORIZED.value());
            erro.setTimestamp(LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>(erro, "Erro na autenticacao", HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now()));
        }
    }

}
