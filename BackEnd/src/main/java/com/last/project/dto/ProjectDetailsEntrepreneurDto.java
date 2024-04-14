package com.last.project.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDetailsEntrepreneurDto {
    private ProjectDto projectDto;
    private List<ReviewDto> reviewDtoList;
}
