package jefferson.livro.javabackend.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jefferson.livro.javabackend.model.Telefone;

@Converter(autoApply = true)
public class TelefoneConverter implements AttributeConverter<Telefone, String> {
    @Override
    public String convertToDatabaseColumn(Telefone telefone) {
        return telefone.toString();
    }

    @Override
    public Telefone convertToEntityAttribute(String s) {
        return new Telefone(s);
    }
}
