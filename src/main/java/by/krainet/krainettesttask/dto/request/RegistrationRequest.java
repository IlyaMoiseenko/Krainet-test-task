package by.krainet.krainettesttask.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Username is mandatory")
    @NotBlank(message = "username is mandatory")
    private String username;

    @NotEmpty(message = "First name is mandatory")
    @NotBlank(message = "First name is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;
}