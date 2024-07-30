package by.krainet.krainettesttask.mapper;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.dto.request.ProjectRequest;
import by.krainet.krainettesttask.dto.response.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public Project toProject(ProjectRequest from) {
        return Project
                .builder()
                .name(from.getName())
                .description(from.getDescription())
                .build();
    }

    public ProjectResponse toProjectResponse(Project from) {
        return ProjectResponse
                .builder()
                .name(from.getName())
                .description(from.getDescription())
                .build();
    }
}
