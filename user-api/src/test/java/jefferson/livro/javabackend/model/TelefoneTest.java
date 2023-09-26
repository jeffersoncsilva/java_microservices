package jefferson.livro.javabackend.model;

import jefferson.livro.javabackend.exceptions.models.TelefoneInvalidoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class TelefoneTest {

    @ParameterizedTest
    @ValueSource(strings = {"(11) 1111-1111", "(11) 11111-1111", "(12) 23442-1235"})
    public void quando_formatoDoTelefoneEValido_naoDeveLancarExcecao(String argument){
        assertDoesNotThrow(() -> {
            Telefone telefone = new Telefone(argument);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"(11) 1111-111", "(11) 1111-11111", "(12) 23442123", "31 4567-2345", "55 44556-11234"})
    public void quando_formatoDoTelefoneEInvalido_deveLancarExcecao(String argument){
        assertThrows(TelefoneInvalidoException.class, () -> {
            Telefone telefone = new Telefone(argument);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"(11) 1111-1111", "(11) 11111-1111", "(12) 23442-1235"})
    public void quando_recuperar_um_telefone_deve_estar_no_formato_correto(String argument){
        Telefone telefone = new Telefone(argument);
        String rgx = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}";
        String telefoneRecuperado = telefone.getTelefone();
        assertTrue(telefoneRecuperado.matches(rgx));
        assertEquals(argument, telefoneRecuperado);
    }
}