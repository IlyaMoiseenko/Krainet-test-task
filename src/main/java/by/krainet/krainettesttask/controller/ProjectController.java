package by.krainet.krainettesttask.controller;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.dto.request.ProjectRequest;
import by.krainet.krainettesttask.mapper.ProjectMapper;
import by.krainet.krainettesttask.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ResponseEntity<Long> createNewProject(ProjectRequest request) {
        Project project = projectMapper.toProject(request);

        return new ResponseEntity<>(
                projectService.save(project),
                HttpStatus.CREATED
        );
    }
}
