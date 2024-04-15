package com.last.project.ControllersTest;

import com.last.project.controllers.CreateurController;
import com.last.project.controllers.EntrepreneurController;
import com.last.project.dto.ProjectDetailsEntrepreneurDto;
import com.last.project.dto.PropositionDto;
import com.last.project.dto.ReviewDto;
import com.last.project.security.jwt.AuthEntryPointJwt;
import com.last.project.security.jwt.JwtUtils;
import com.last.project.security.services.UserDetailsServiceImpl;
import com.last.project.services.createur.CreateurService;
import com.last.project.services.entrepreneurService.EntrepreneurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EntrepreneurController.class)
@AutoConfigureMockMvc
public class EntrepreneurControllerTesting {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntrepreneurService entrepreneurService;

    @InjectMocks
    private EntrepreneurController entrepreneurController;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;
    @MockBean
    private JwtUtils jwtUtils;

    @Test
    void getAllProject() throws Exception {
        doReturn(Collections.emptyList()).when(entrepreneurService).getAllProject();
        mockMvc.perform(get("/api/entrepreneur/projects"))
                .andExpect(status().isOk());
    }

    @Test
    void searchByProjectName() throws Exception {
        doReturn(Collections.emptyList()).when(entrepreneurService).searshProjectByName(any());
        mockMvc.perform(get("/api/entrepreneur/search/{name}", "test"))
                .andExpect(status().isOk());
    }

    @Test
    void searchByDomaineName() throws Exception {
        doReturn(Collections.emptyList()).when(entrepreneurService).searshProjectDomaineName(any());
        mockMvc.perform(get("/api/entrepreneur/searchDomaine/{domainename}", "test"))
                .andExpect(status().isOk());
    }

    @Test
    void createProposition() throws Exception {
        PropositionDto propositionDto = new PropositionDto();
        // Set any necessary properties on propositionDto here

        doReturn(true).when(entrepreneurService).saveProposition(any());

        mockMvc.perform(post("/api/entrepreneur/proposition-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Replace "{}" with a JSON representation of your PropositionDto
                .andExpect(status().isOk());
    }

    @Test
    void getDetailsByProjectId() throws Exception {
        // Create an instance of ProjectDetailsEntrepreneurDto
        ProjectDetailsEntrepreneurDto projectDetails = new ProjectDetailsEntrepreneurDto();

        doReturn(projectDetails).when(entrepreneurService).getPrjectDetailsById(any());

        mockMvc.perform(get("/api/entrepreneur/project/{projectId}", 1))
                .andExpect(status().isOk());
    }


    @Test
    void getAllPropositionsByUserId() throws Exception {
        doReturn(Collections.emptyList()).when(entrepreneurService).getAllPrppositionByUserId(any());
        mockMvc.perform(get("/api/entrepreneur/my-propositions/{userId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void giveReview() throws Exception {
        ReviewDto reviewDto = new ReviewDto();
        // Set any necessary properties on reviewDto here

        doReturn(true).when(entrepreneurService).giveReview(any());

        mockMvc.perform(post("/api/entrepreneur/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")) // Replace "{}" with a JSON representation of your ReviewDto
                .andExpect(status().isOk());
    }
}
