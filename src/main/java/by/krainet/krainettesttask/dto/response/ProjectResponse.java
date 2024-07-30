package by.krainet.krainettesttask.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectResponse {

    private String name;
    private String description;
}
