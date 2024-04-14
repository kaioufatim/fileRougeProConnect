package com.last.project.entities;

import com.last.project.dto.ReviewDto;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date reviewDate;
    private  String review;
    private Long rating;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "project_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    public ReviewDto getDto(){
        ReviewDto reviewDto=new ReviewDto();

        reviewDto.setId(id);
        reviewDto.setReview(review);
        reviewDto.setRating(rating);
        reviewDto.setReviewDate(reviewDate);
        reviewDto.setUserId(user.getId());
        reviewDto.setEntrepreneurName(user.getUsername());
        reviewDto.setProjectId(project.getId());
        reviewDto.setProjectName(project.getProjectName());
        return reviewDto;

    }



}
