package by.krainet.krainettesttask.repository;

import by.krainet.krainettesttask.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByName(String name);
    void deleteByName(String name);
}
