package com.last.project.services.entrepreneurService;

import com.last.project.dto.ProjectDetailsEntrepreneurDto;
import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.dto.ReviewDto;

import java.util.List;

public interface EntrepreneurService {
    List<ProjectDto> getAllProject();

    List<ProjectDto> searshProjectByName(String name);

    List<ProjectDto> searshProjectDomaineName(String name);

    boolean saveProposition(PropositionDto propositionDto);

    ProjectDetailsEntrepreneurDto getPrjectDetailsById(Long projectId);

    List<PropositionDto> getAllPrppositionByUserId(Long userId);
    Boolean giveReview(ReviewDto reviewDto);

}
