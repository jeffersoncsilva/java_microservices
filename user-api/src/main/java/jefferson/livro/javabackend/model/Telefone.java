package jefferson.livro.javabackend.model;

import jefferson.livro.javabackend.exceptions.models.TelefoneInvalidoException;

import java.util.Objects;


public class Telefone {
    private String telefone;

    public Telefone(String telefone){
        setTelefone(telefone);
    }

    public void setTelefone(String telefone){
        if(telefoneEstaValido(telefone))
            this.telefone = telefone;
        else
            throw new TelefoneInvalidoException("Telefone inválido");
    }

    public String getTelefone(){
        return this.telefone;
    }

    private boolean telefoneEstaValido(String telefone){
        String rgx = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}";
        return telefone.matches(rgx);
    }

    @Override
    public String toString() {
        return telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone1 = (Telefone) o;
        return Objects.equals(telefone, telefone1.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefone);
    }
}
