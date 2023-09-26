package jefferson.livro.javabackend;

import jefferson.livro.javabackend.model.*;

import java.time.LocalDateTime;

public class Utils {
    public static User getUser(String cpf){
        var user = new User();
        user.setNome("Jefferson");
        user.setCpf(new Cpf(cpf));
        user.setEmail(new Email("email."+cpf+"com@email.com"));
        user.setSenha("senha");
        user.setDataCadastro(LocalDateTime.now());
        Endereco endereco = new Endereco();
        endereco.setCep("35170000");
        endereco.setRua("Rua 1");
        endereco.setNumero("123");
        endereco.setBairro("Bairro 1");
        endereco.setCidade("Cidade 1");
        endereco.setEstado("MG");
        endereco.setComplemento("complemento");
        user.setEndereco(endereco);
        user.setTelefone(new Telefone("(33) 9999-9999"));
        return user;
    }
}
