package com.last.project.repositories;

import com.last.project.dto.ProjectDto;
import com.last.project.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findAllByUserId (Long userId);
    List<Project> findAllByProjectNameContaining(String name);
    List<Project> findAllByDomaineNameContainingIgnoreCase(String name);
}
