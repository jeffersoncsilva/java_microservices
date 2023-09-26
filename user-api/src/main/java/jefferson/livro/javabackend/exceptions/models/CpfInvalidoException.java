package jefferson.livro.javabackend.exceptions.models;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(String cpfInválido) {
        super(cpfInválido);
    }
}
