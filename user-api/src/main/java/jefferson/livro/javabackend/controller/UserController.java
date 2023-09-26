package jefferson.livro.javabackend.controller;

import dtos.user.UserDTO;
import jakarta.validation.Valid;
import jefferson.livro.javabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> pegaTodosUsuarios() {
        var user =  service.pegaTodosUsuarios();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> buscaUsuarioPorId(@PathVariable Long id) {
        var userDto = service.buscaUsuarioPorId(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO cadatraNovoUsuario(@RequestBody @Valid UserDTO userDTO) {
        return service.cadastraNovoUsuario(userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apagaUmUsuario(@PathVariable Long id) {
        service.delete(id);
    }
}
