package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
