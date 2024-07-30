package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.exception.EntityNotFoundException;
import by.krainet.krainettesttask.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Long save(Project project) {
        return projectRepository.save(project).getId();
    }

    public List<Project> findAll(int size, int page) {
        Page<Project> allProjects = projectRepository.findAll(PageRequest.of(page, size));

        return allProjects.stream().toList();
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(Project.class, Map.of("Project name", name)));
    }

    public void deleteByName(String name) {
        Project project = projectRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(Project.class, Map.of("Name", name)));

        projectRepository.deleteById(project.getId());
    }
}
