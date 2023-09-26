package jefferson.livro.javabackend.exceptions.models;

public class EmailInvalidoException extends RuntimeException{
    public EmailInvalidoException(String message) {
        super(message);
    }
}
