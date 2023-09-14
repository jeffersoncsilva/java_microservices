package jefferson.livro.javabackend.controller;

import dtos.UserDTO;
import jakarta.validation.Valid;
import jefferson.livro.javabackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody @Valid UserDTO userDTO) {
        return service.save(userDTO);
    }

    @GetMapping("/{cpf}/cpf")
    public UserDTO findByCpf(@RequestParam(name="key", required = true) String key, @PathVariable String cpf) {
        return service.findByCpfAndKey(cpf, key);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public List<UserDTO> queryByName(@RequestParam(name = "nome", required = true) String nome) {
        return service.queryByName(nome);
    }
}
