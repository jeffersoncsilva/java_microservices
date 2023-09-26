package jefferson.livro.javabackend.exceptions.models;

public class TelefoneInvalidoException extends RuntimeException{
    public TelefoneInvalidoException() {
    }

    public TelefoneInvalidoException(String message) {
        super(message);
    }
}
