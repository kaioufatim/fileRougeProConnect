package com.last.project.dto;

import com.last.project.entities.Project;
import com.last.project.entities.User;
import com.last.project.enums.PropositionStatus;
import com.last.project.enums.ReviewStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropositionDto {

    private Long id;
    private PropositionStatus propositionStatus;
    private ReviewStatus reviewStatus;
    private String projectName;
    private Date propositionDate;
    private String userName;
    private Long userId;
    private Long createurId;
    private Long projectId;


}
