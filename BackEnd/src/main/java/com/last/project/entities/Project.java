package com.last.project.entities;

import com.last.project.dto.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String domaineName;
    private String description;
    private String projectName;
//    @Lob
//    @Column(columnDefinition = "longblob")
//    private  byte[] img;
    @Column(columnDefinition = "bytea")
    private byte[] img;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    //    public ProjectDto getProjectDto(){
//        ProjectDto projectDto =new ProjectDto();
//        projectDto.setDomaineName(domaineName);
//        projectDto.setDescription(description);
//        projectDto.setCreatorName(user.getName());
//        projectDto.setProjectName(projectName);
//
//        projectDto.setReturnedImg(img);
//
//        return projectDto;
//    }
public ProjectDto getProjectDto() {
    ProjectDto projectDto = new ProjectDto();
    projectDto.setId(this.getId()); // Map project ID
    projectDto.setDomaineName(this.getDomaineName());
    projectDto.setDescription(this.getDescription());
    projectDto.setProjectName(this.getProjectName());

    // Handle potential null value in User
    if (this.getUser() != null) {
        projectDto.setCreatorName(this.getUser().getUsername());
        projectDto.setUserId(this.getUser().getId());
    } else {
        projectDto.setCreatorName(null); // Set to null if user is not available
        projectDto.setUserId(null);
    }

    // Consider if img should be mapped differently (assuming byte[])
    projectDto.setReturnedImg(this.getImg());

    return projectDto;
}

}
