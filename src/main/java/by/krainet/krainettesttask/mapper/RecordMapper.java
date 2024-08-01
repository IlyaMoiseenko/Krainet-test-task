package by.krainet.krainettesttask.mapper;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.domain.Record;
import by.krainet.krainettesttask.dto.request.RecordRequest;
import by.krainet.krainettesttask.dto.response.RecordResponse;
import by.krainet.krainettesttask.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordMapper {

    private final ProjectService projectService;

    public Record toDomain(RecordRequest from) {
        Project project = projectService.findByName(from.getProjectName());

        return Record
                .builder()
                .startTime(from.getStartTime())
                .endTime(from.getEndTime())
                .project(project)
                .user(from.getUser())
                .build();
    }

    public RecordResponse toResponse(Record from) {
        return RecordResponse
                .builder()
                .startTime(from.getStartTime())
                .endTime(from.getEndTime())
                .username(from.getUser().getUsername())
                .projectName(from.getProject().getName())
                .build();
    }
}
