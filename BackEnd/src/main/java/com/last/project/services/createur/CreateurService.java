package com.last.project.services.createur;

import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;

import java.io.IOException;
import java.util.List;

public interface CreateurService {
     boolean postProject(Long userId, ProjectDto projectDto) throws IOException;

     List<ProjectDto> getAllProject(Long userId);
     ProjectDto getProjById(Long projectId);
     boolean updateProject(Long projectId,ProjectDto projectDto) throws IOException ;
     boolean deleteProject(Long projectId);
     List<PropositionDto> getAllPropositions(Long createurId);
     boolean changePropositionStatus(Long propositionId, String status);



     }
