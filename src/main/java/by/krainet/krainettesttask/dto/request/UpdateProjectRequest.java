package by.krainet.krainettesttask.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateProjectRequest {

    @NotEmpty(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotEmpty(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    private String description;
}
