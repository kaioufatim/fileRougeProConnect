package com.last.project.entities;

import com.last.project.dto.PropositionDto;
import com.last.project.enums.PropositionStatus;
import com.last.project.enums.ReviewStatus;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "propositions")
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PropositionStatus propositionStatus;
    private ReviewStatus reviewStatus;
    private Date propositionDate;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "createur_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User createur;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "project_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    public PropositionDto getPropositionDto(){
        PropositionDto propositionDto=new PropositionDto();

        propositionDto.setId(id);
        propositionDto.setProjectName(project.getProjectName());
        propositionDto.setPropositionDate(propositionDate);
        propositionDto.setPropositionStatus(propositionStatus);

        propositionDto.setReviewStatus(reviewStatus);

        propositionDto.setProjectId(project.getId());

        propositionDto.setCreateurId(createur.getId());
        propositionDto.setUserName(user.getUsername());
        return propositionDto;

    }



}
