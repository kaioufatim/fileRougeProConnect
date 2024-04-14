package com.last.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    private String domaineName;
    private String description;
    private String projectName;
    private MultipartFile img;
    private  byte[] returnedImg;
    private Long userId;
    private String creatorName;

}
