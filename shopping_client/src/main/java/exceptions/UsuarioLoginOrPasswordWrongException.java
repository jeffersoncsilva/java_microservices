package exceptions;

public class UsuarioLoginOrPasswordWrongException extends RuntimeException{

        public UsuarioLoginOrPasswordWrongException(String message) {
            super(message);
        }
}
