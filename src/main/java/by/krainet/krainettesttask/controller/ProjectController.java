package by.krainet.krainettesttask.controller;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.dto.request.ProjectRequest;
import by.krainet.krainettesttask.dto.response.PageResponse;
import by.krainet.krainettesttask.dto.response.ProjectResponse;
import by.krainet.krainettesttask.mapper.ProjectMapper;
import by.krainet.krainettesttask.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping
    public ResponseEntity<Long> createNewProject(@RequestBody @Valid ProjectRequest request) {
        Project project = projectMapper.toProject(request);

        return new ResponseEntity<>(
                projectService.save(project),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<PageResponse<ProjectResponse>> getAll(
            @RequestParam(name = "size", required = false, defaultValue = "5") int size,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page
    ) {
        List<Project> projects = projectService.findAll(size, page);
        List<ProjectResponse> responses = projects.stream()
                .map(projectMapper::toProjectResponse)
                .toList();

        return ResponseEntity.ok(
                new PageResponse<>(
                        responses,
                        page,
                        size,
                        responses.size()
                )
        );
    }
}
