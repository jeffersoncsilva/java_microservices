package api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T>{
    private T data;
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
