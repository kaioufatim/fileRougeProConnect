package com.last.project.ControllersTest;

import com.last.project.controllers.CreateurController;
import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.security.jwt.AuthEntryPointJwt;
import com.last.project.security.jwt.JwtUtils;
import com.last.project.security.services.UserDetailsServiceImpl;
import com.last.project.services.createur.CreateurService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CreateurController.class)
@AutoConfigureMockMvc
public class CreateurControllerTesting {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateurService createurService;

    @InjectMocks
    private CreateurController createurController;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @MockBean
    private AuthEntryPointJwt authEntryPointJwt;
    @MockBean
    private JwtUtils jwtUtils;
    @Test
    void postProject() throws Exception {
        // Assuming you have a way to create a valid ProjectDto object
        ProjectDto projectDto = new ProjectDto();
        // Set any necessary properties on projectDto here

        // Set up the mock to return true when postProject is called
        doReturn(true).when(createurService).postProject(any(), any());

        mockMvc.perform(post("/api/createur/project/{userId}", 1)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .flashAttr("projectDto", projectDto)) // Assuming this is how you're passing the ProjectDto in your real application
                .andExpect(status().isOk());
    }


    @Test //worked
    void getAllProjectByUserId() throws Exception {
        doReturn(Collections.emptyList()).when(createurService).getAllProject(any());
        mockMvc.perform(get("/api/createur/projects/{userId}", 1))
                .andExpect(status().isOk());
    }

    @Test //worked
    void getProjectById() throws Exception {
        doReturn(new ProjectDto()).when(createurService).getProjById(any());
        mockMvc.perform(get("/api/createur/project/{projectId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void updateProject() throws Exception {
        ProjectDto projectDto = new ProjectDto();
        doReturn(true).when(createurService).updateProject(any(), any());

        mockMvc.perform(put("/api/createur/project/{projectId}", 1)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .flashAttr("projectDto", projectDto)) // Assuming this is how you're passing the ProjectDto in your real application
                .andExpect(status().isOk());
    }


    @Test //worked
    void deleteProject() throws Exception {
        // Set up the mock to return true when deleteProject is called
        doReturn(true).when(createurService).deleteProject(any());

        mockMvc.perform(delete("/api/createur/project/{projectId}", 1))
                .andExpect(status().isOk());
    }


    @Test //worked
    void getAllPropositionBycreateur() throws Exception {
        doReturn(Collections.emptyList()).when(createurService).getAllPropositions(any());
        mockMvc.perform(get("/api/createur/propositions/{createurId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void changePropositionStatus() throws Exception {
        // Assuming you have a way to create a valid PropositionDto object or similar
        // Set up the mock to return true when changePropositionStatus is called
        doReturn(true).when(createurService).changePropositionStatus(any(), any());

        mockMvc.perform(get("/api/createur/propositions/{propositionId}/{status}", 1, "status"))
                .andExpect(status().isOk());
    }

}

