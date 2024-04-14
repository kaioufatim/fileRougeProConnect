package com.last.project.services.createur;

import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.entities.Project;
import com.last.project.entities.Proposition;
import com.last.project.entities.User;
import com.last.project.enums.PropositionStatus;
import com.last.project.repositories.ProjectRepository;
import com.last.project.repositories.PropositionRepository;
import com.last.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CreateurServiceImp implements CreateurService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PropositionRepository propositionRepository;
    public boolean postProject(Long userId, ProjectDto projectDto) throws IOException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            Project project= new Project();
            project.setDomaineName(projectDto.getDomaineName());
            project.setDescription(projectDto.getDescription());
            project.setProjectName(projectDto.getProjectName());
            project.setImg(projectDto.getImg().getBytes());
            project.setUser(optionalUser.get());
            projectRepository.save(project);
            return true;
        }
        return false;
    }


    public List<ProjectDto> getAllProject(Long userId){
        return projectRepository.findAllByUserId(userId).stream().map(Project :: getProjectDto).collect(Collectors.toList());
    }
    public ProjectDto getProjById(Long projectId){
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()){
            return optionalProject.get().getProjectDto();
        }
            return null;
    }

    public boolean updateProject(Long projectId,ProjectDto projectDto) throws IOException {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()){
            Project project = optionalProject.get();
            project.setProjectName(projectDto.getProjectName());
            project.setDescription(projectDto.getDescription());
            project.setDomaineName(projectDto.getDomaineName());
            if(projectDto.getImg() != null){
                project.setImg(projectDto.getImg().getBytes());
            }
            projectRepository.save(project);
            return  true;
        }else {
            return false;
        }
    }
    public boolean deleteProject(Long projectId){
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if(optionalProject.isPresent()){
            projectRepository.delete(optionalProject.get());
            return true;
        }else {
            return false;
        }
    }
    public List<PropositionDto> getAllPropositions(Long createurId){
        return propositionRepository.findAllByCreateurId(createurId)
                .stream().map(Proposition::getPropositionDto)
                .collect(Collectors.toList());
    }

    public boolean changePropositionStatus(Long propositionId, String status){
        Optional<Proposition> optionalProposition = propositionRepository.findById(propositionId);
        if (optionalProposition.isPresent()){
            Proposition existedProposition = optionalProposition.get();
            if(Objects.equals(status,"Approve")){
                existedProposition.setPropositionStatus(PropositionStatus.APPROVED);
            }else {
                existedProposition.setPropositionStatus(PropositionStatus.REJECTED);
            }
            propositionRepository.save(existedProposition);
            return true;
    }
        return false;

    }



}


