package dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CpfDTO {
    @NotBlank(message = "CPF é obrigatório.")
    private String cpf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CpfDTO cpfDTO = (CpfDTO) o;
        return Objects.equals(cpf, cpfDTO.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
