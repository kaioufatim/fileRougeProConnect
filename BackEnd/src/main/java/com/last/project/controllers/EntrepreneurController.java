package com.last.project.controllers;

import com.last.project.dto.PropositionDto;
import com.last.project.dto.ReviewDto;
import com.last.project.services.entrepreneurService.EntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/entrepreneur")
public class EntrepreneurController {
    @Autowired
    private EntrepreneurService entrepreneurService;
    @GetMapping("/projects")
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.ok(entrepreneurService.getAllProject());
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchByProjectName(@PathVariable String name){
        return ResponseEntity.ok(entrepreneurService.searshProjectByName(name));
    }
    @GetMapping("/searchDomaine/{domainename}")
    public ResponseEntity<?> searchByDomaineName(@PathVariable String domainename){
        return ResponseEntity.ok(entrepreneurService.searshProjectDomaineName(domainename));
    }
    @PostMapping("/proposition-service")
    public ResponseEntity<?> createProposition(@RequestBody PropositionDto propositionDto){
        boolean success = entrepreneurService.saveProposition(propositionDto);
        if (success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<?> getDetailsByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(entrepreneurService.getPrjectDetailsById(projectId));
    }

    @GetMapping("/my-propositions/{userId}")
    public ResponseEntity<?> getAllPropositionsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(entrepreneurService.getAllPrppositionByUserId(userId));
    }
//    @PostMapping("/review")
//    public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto){
//        Boolean success = entrepreneurService.giveReview(reviewDto);
//        if(success){
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//    }
@PostMapping("/review")
public ResponseEntity<?> giveReview(@RequestBody ReviewDto reviewDto){
    try {
        Boolean success = entrepreneurService.giveReview(reviewDto);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    } catch (EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}


}
