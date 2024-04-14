package com.last.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    private Date reviewDate;
    private  String review;
    private Long rating;
    private Long userId;
    private Long projectId;
    private String entrepreneurName;
    private String projectName;
    private Long PropositionId;

}
