package jefferson.livro.javabackend.model;

import jefferson.livro.javabackend.exceptions.models.EmailInvalidoException;

import java.util.Objects;

public class Email {
    private static final int TAMANHO_MAXIMO_DO_EMAIL = 100;
    private String email;

    public Email(String email){
        setEmail(email);
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        if(emailEstaValido(email))
            this.email = email;
        else
            throw new EmailInvalidoException("Email inválido");
    }

    public boolean emailEstaValido(String email){
        String rgx = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.([a-zA-Z0-9-]+)+";
        return email.matches(rgx) && email.length() <= TAMANHO_MAXIMO_DO_EMAIL;
    }

    @Override
    public String toString(){
        return this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
