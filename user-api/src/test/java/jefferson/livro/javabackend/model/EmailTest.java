package jefferson.livro.javabackend.model;

import jefferson.livro.javabackend.exceptions.models.EmailInvalidoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {"email@email.com", "em.ail@email.tu", "em-ail@gmail.com", "em_ail.com@gmail.com"})
    public void quando_email_valido_nao_deve_retornar_excecao(String argument){
        assertDoesNotThrow(() -> new Email(argument));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email", "email@", "email@.com", "email@.com.br", "email@com", "email@com."})
    public void quando_email_invalido_deve_retornar_excecao(String argument){
        assertThrows(EmailInvalidoException.class, () -> new Email(argument));
    }

    @ParameterizedTest
    @ValueSource(strings = {"email.com@email.com", "email@email.com", "e-m-a_i-l@email.c"})
    public void quando_emailEValido_deveRetornarEmailNoFormatoCorreto(String argument){
        Email email = new Email(argument);
        assertTrue(email.getEmail().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.([a-zA-Z0-9-]+)+"));
        assertEquals(argument, email.getEmail());
    }


    @ParameterizedTest
    @ValueSource(strings = {"emailemailemadfgsdfgsdfgsdfgsdfgsdfgsdfgdfgsdfgsdfgsdfgsdfgsdfghesrtyhsdffdghbfgdjsfhsrthysfghxrilemailemailemail@com."})
    public void quando_emailTemMaisDe100Caracteres_deveRetornarExcecao(String argument){
        assertThrows(EmailInvalidoException.class, () -> new Email(argument));
    }
}