package jefferson.livro.javabackend.service;

import dtos.login.UserLoginDTO;
import dtos.login.UserTokenDTO;
import exceptions.UsuarioLoginOrPasswordWrongException;
import jefferson.livro.javabackend.model.Cpf;
import jefferson.livro.javabackend.model.User;
import jefferson.livro.javabackend.repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserLoginRepository loginRepository;

    public UserTokenDTO realizaLogin(UserLoginDTO login){
        User usuario;
        if(login.getEmail() != null){
            usuario = loginRepository.findByEmailAndSenha(login.getEmail(), login.getPassword());
        }else{
            usuario =  loginRepository.findByCpfAndSenha(new Cpf(login.getCpf()), login.getPassword());
        }

        if(usuario == null){
            throw new UsuarioLoginOrPasswordWrongException("Usuário ou senha inválidos");
        }
        UserTokenDTO user = new UserTokenDTO();
        user.setToken(gerarToken(usuario));
        return user;
    }



    private String gerarToken(User usuario){
        return usuario.getCpf() + ":" + usuario.getEmail();
    }
}
