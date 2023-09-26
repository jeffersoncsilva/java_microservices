package jefferson.livro.javabackend.model;

import jefferson.livro.javabackend.exceptions.models.CpfInvalidoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @ParameterizedTest
    @ValueSource(strings = {"12345678910", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666", "77777777777", "88888888888", "99999999999", "123.456.789-10", "123.456.789.10"})
    public void quando_inseridoCpfInvalido_deveLancarCpfInvalidoException(String argument){
        assertThrows(CpfInvalidoException.class, () -> new Cpf(argument));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123.456.789-09", "859.803.930-64", "685.289.220-49", "388.676.300-59"})
    public void quando_inseridoCpfNoFormatoCorretoEValido_deveCriarObjetoCpf(String argument){
        assertDoesNotThrow(() -> new Cpf(argument));
    }

    @ParameterizedTest
    @ValueSource(strings = {"023.853.540-12", "040.417.320-93", "116.080.090-17"})
    public void quando_recuperadoUmCpf_deveSerRetornadoNoFormatoCorreto(String argument){
        Cpf cpf = new Cpf(argument);
        assertEquals(argument, cpf.getCpf());
    }

}