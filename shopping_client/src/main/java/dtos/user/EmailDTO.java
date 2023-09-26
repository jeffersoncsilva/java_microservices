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
public class EmailDTO {
    @NotBlank(message = "E-mail e obrigatório.")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO emailDTO = (EmailDTO) o;
        return Objects.equals(email, emailDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
