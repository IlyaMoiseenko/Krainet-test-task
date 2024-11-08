package by.krainet.krainettesttask.controller;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.dto.request.ProjectRequest;
import by.krainet.krainettesttask.dto.request.UpdateProjectRequest;
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

    @GetMapping("/{project-name}")
    public ResponseEntity<ProjectResponse> getByName(@PathVariable(name = "project-name") String name) {
        Project project = projectService.findByName(name);

        return new ResponseEntity<>(
                projectMapper.toProjectResponse(project),
                HttpStatus.FOUND
        );
    }

    @DeleteMapping("/{project-name}")
    public ResponseEntity<?> deleteByName(@PathVariable(name = "project-name") String name) {
        projectService.deleteByName(name);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{project-name}")
    public ResponseEntity<ProjectResponse> update(@PathVariable(name = "project-name") String name,
                                                  @RequestBody @Valid UpdateProjectRequest request) {
        Project projectToUpdate = projectService.findByName(name);

        Project updatedProject = projectService.update(request, projectToUpdate);

        return new ResponseEntity<>(
                projectMapper.toProjectResponse(updatedProject),
                HttpStatus.OK
        );
    }
}
