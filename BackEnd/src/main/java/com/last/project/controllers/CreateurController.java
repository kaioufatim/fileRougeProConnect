package com.last.project.controllers;

import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.services.createur.CreateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/createur")
public class CreateurController {
    @Autowired
    private CreateurService createurService;
    @PostMapping("/project/{userId}")
    public ResponseEntity<?> postProject(@PathVariable Long userId, @ModelAttribute ProjectDto projectDto) throws IOException {
        boolean success = createurService.postProject(userId,projectDto);
        if (success){
            return  ResponseEntity.status(HttpStatus.OK).build();
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/projects/{userId}")
    public ResponseEntity<?> getAllProjectByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(createurService.getAllProject(userId));

    }

    @GetMapping("/project/{projectId}")

    public ResponseEntity<?> getProjectById(@PathVariable Long projectId){
        ProjectDto projectDto = createurService.getProjById(projectId);
        if (projectDto !=null){
            return ResponseEntity.ok(projectDto);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/project/{projectId}")
    public ResponseEntity<?> updateProject(@PathVariable Long projectId ,@ModelAttribute ProjectDto projectDto) throws IOException {
        boolean success = createurService.updateProject(projectId,projectDto);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId){
        boolean success = createurService.deleteProject(projectId);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/propositions/{createurId}")
    public ResponseEntity<List<PropositionDto>> getAllPropositionBycreateur(@PathVariable Long createurId){
        return ResponseEntity.ok(createurService.getAllPropositions(createurId));
    }

    @GetMapping("/propositions/{propositionId}/{status}")
    public ResponseEntity<?> changePropositionStatus(@PathVariable Long propositionId , @PathVariable String status){
        boolean success = createurService.changePropositionStatus(propositionId, status);
        if (success) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }




}
