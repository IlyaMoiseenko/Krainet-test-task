package by.krainet.krainettesttask.service;

import by.krainet.krainettesttask.domain.Project;
import by.krainet.krainettesttask.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Long save(Project project) {
        return projectRepository.save(project).getId();
    }
}
