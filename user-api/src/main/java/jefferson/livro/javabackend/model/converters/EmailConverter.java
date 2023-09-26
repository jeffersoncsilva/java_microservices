package jefferson.livro.javabackend.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jefferson.livro.javabackend.model.Email;

@Converter(autoApply = true)
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email.toString();
    }

    @Override
    public Email convertToEntityAttribute(String s) {
        return new Email(s);
    }
}
