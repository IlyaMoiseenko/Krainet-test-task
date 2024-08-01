package by.krainet.krainettesttask.dto.request;

import by.krainet.krainettesttask.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class RecordRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    private User user;

    @NotEmpty(message = "Project name is mandatory")
    @NotBlank(message = "Project name is mandatory")
    private String projectName;
}
