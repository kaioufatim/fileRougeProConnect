//package com.last.project.ControllersTest;
//
//import com.last.project.controllers.CreateurController;
//import com.last.project.dto.ProjectDto;
//import com.last.project.dto.PropositionDto;
//import com.last.project.enums.PropositionStatus;
//import com.last.project.enums.ReviewStatus;
//import com.last.project.services.createur.CreateurService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
//import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.*;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest(CreateurController.class)
//@AutoConfigureMockMvc
//public class CreateurControllerTest {
//        @MockBean
//        private CreateurService createurService;
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        private ProjectDto projectDto;
//        private List<ProjectDto> projectList;
//        private List<PropositionDto> propositionList;
//
//    @BeforeEach
//    void setUp() {
//        // Initialize ProjectDto
//        projectDto = new ProjectDto();
//        projectDto.setId(1L);
//        projectDto.setDomaineName("Software Development");
//        projectDto.setDescription("A project for developing a software application.");
//        projectDto.setProjectName("My Software Project");
//        // For MultipartFile, you might need to mock it or use a real file for testing
//        // For simplicity, we'll leave it as null
//        projectDto.setImg(null);
//        projectDto.setReturnedImg(new byte[]{1, 2, 3}); // Example byte array
//        projectDto.setUserId(1L);
//        projectDto.setCreatorName("John Doe");
//
//        // Initialize List of ProjectDto for getAllProjectByUserId test
//        projectList = Arrays.asList(projectDto);
//
//        // Initialize PropositionDto
//        PropositionDto propositionDto = new PropositionDto();
//        propositionDto.setId(1L);
//        propositionDto.setPropositionStatus(PropositionStatus.PENDING);
//        propositionDto.setReviewStatus(ReviewStatus.TRUE);
//        propositionDto.setProjectName("My Software Project");
//        propositionDto.setPropositionDate(new Date());
//        propositionDto.setUserName("John Doe");
//        propositionDto.setUserId(1L);
//        propositionDto.setCreateurId(1L);
//        propositionDto.setProjectId(1L);
//
//        // Initialize List of PropositionDto for getAllPropositionBycreateur test
//        propositionList = Arrays.asList(propositionDto);
//    }
//
//        @Test
//        void postProject() throws Exception {
//            when(createurService.postProject(any(Long.class), any(ProjectDto.class))).thenReturn(true);
//
//            mockMvc.perform(post("/api/createur/project/{userId}", 1L)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content("{...}")) // JSON representation of ProjectDto
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//
//        @Test
//        void getAllProjectByUserId() throws Exception {
//            when(createurService.getAllProject(any(Long.class))).thenReturn(projectList);
//
//            mockMvc.perform(get("/api/createur/projects/{userId}", 1L))
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//
//        @Test
//        void getProjectById() throws Exception {
//            when(createurService.getProjById(any(Long.class))).thenReturn(projectDto);
//
//            mockMvc.perform(get("/api/createur/project/{projectId}", 1L))
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//
//        @Test
//        void updateProject() throws Exception {
//            when(createurService.updateProject(any(Long.class), any(ProjectDto.class))).thenReturn(true);
//
//            mockMvc.perform(put("/api/createur/project/{projectId}", 1L)
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content("{...}")) // JSON representation of ProjectDto
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//
//        @Test
//        void deleteProject() throws Exception {
//            when(createurService.deleteProject(any(Long.class))).thenReturn(true);
//
//            mockMvc.perform(delete("/api/createur/project/{projectId}", 1L))
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//
//        @Test
//        void getAllPropositionBycreateur() throws Exception {
//            when(createurService.getAllPropositions(any(Long.class))).thenReturn(propositionList);
//
//            mockMvc.perform(get("/api/createur/propositions/{createurId}", 1L))
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//
//        @Test
//        void changePropositionStatus() throws Exception {
//            when(createurService.changePropositionStatus(any(Long.class), any(String.class))).thenReturn(true);
//
//            mockMvc.perform(get("/api/createur/propositions/{propositionId}/{status}", 1L, "status"))
//                    .andExpect(status().isOk())
//                    .andDo(print());
//        }
//    }
//
//}
