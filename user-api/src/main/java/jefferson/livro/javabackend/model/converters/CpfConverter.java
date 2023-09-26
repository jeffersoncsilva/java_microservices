package jefferson.livro.javabackend.model.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jefferson.livro.javabackend.model.Cpf;

@Converter(autoApply = true)
public class CpfConverter implements AttributeConverter<Cpf, String> {
    @Override
    public String convertToDatabaseColumn(Cpf cpf){
        return cpf.toString();
    }

    @Override
    public Cpf convertToEntityAttribute(String cpf){
        return new Cpf(cpf);
    }
}
