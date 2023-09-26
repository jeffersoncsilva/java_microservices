package jefferson.livro.javabackend.model;

import jefferson.livro.javabackend.exceptions.models.CpfInvalidoException;

import java.util.Objects;

public class Cpf {

    private String cpf;

    public Cpf(String cpf) {
        setCpf(cpf);
    }

    public void setCpf(String cpf){
        if(cpfEvalido(cpf)){
            this.cpf = cpf;
        } else {
            throw new CpfInvalidoException("CPF inválido");
        }
    }

    private boolean cpfEvalido(String cpf) {
        boolean cpfValido = false;
        String rgx = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}";
        cpfValido = cpf.matches(rgx);
        if(cpfValido)
            cpfValido = validaCasosBases(cpf);
        if(cpfValido)
            cpfValido = validaDigitosVerificadores(cpf);

        return cpfValido;
    }

    private boolean validaDigitosVerificadores(String cpf){
        boolean cpfValido = true;
        int[] digitos = new int[11];
        int[] multiplicadoresPrimeiroDigito = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicadoresSegundoDigito = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        for(int i = 0; i < 11; i++){
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma = 0;
        for(int i = 0; i < 9; i++){
            soma += digitos[i] * multiplicadoresPrimeiroDigito[i];
        }

        int resto = soma % 11;
        int primeiroDigitoVerificador = 0;
        if(resto < 2)
            primeiroDigitoVerificador = 0;
        else
            primeiroDigitoVerificador = 11 - resto;

        if(primeiroDigitoVerificador != digitos[9])
            cpfValido = false;

        soma = 0;
        for(int i = 1; i < 10; i++){
            soma += digitos[i] * multiplicadoresSegundoDigito[i];
        }

        resto = soma % 11;
        int segundoDigitoVerificador = 0;
        if(resto < 2)
            segundoDigitoVerificador = 0;
        else
            segundoDigitoVerificador = 11 - resto;

        if(segundoDigitoVerificador != digitos[10])
            cpfValido = false;

        return cpfValido;
    }

    private boolean validaCasosBases(String cpf){
        boolean cpfValido = true;
        String[] casosBases = {"000.000.000-00", "111.111.111-11", "222.222.222-22", "333.333.333-33", "444.444.444-44", "555.555.555-55", "666.666.666-66", "777.777.777-77", "888.888.888-88", "999.999.999-99"};

        for(String casoBase : casosBases){
            if(cpf.equals(casoBase)){
                cpfValido = false;
                break;
            }
        }

        return cpfValido;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf1 = (Cpf) o;
        return Objects.equals(cpf, cpf1.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
