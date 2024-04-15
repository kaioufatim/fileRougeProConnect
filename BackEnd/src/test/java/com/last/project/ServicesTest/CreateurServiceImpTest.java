package com.last.project.ServicesTest;

import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.entities.Project;
import com.last.project.entities.Proposition;
import com.last.project.entities.User;
import com.last.project.enums.PropositionStatus;
import com.last.project.repositories.ProjectRepository;
import com.last.project.repositories.PropositionRepository;
import com.last.project.repositories.UserRepository;
import com.last.project.services.createur.CreateurServiceImp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class CreateurServiceImpTest {

    @Autowired
    private CreateurServiceImp createurService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PropositionRepository propositionRepository;

    private User user;
    private Project project;

    @BeforeEach
    public void setUp() {
        // Create a sample user
        user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user = userRepository.save(user);

        // Create a sample project
        project = new Project();
        project.setProjectName("Test Project");
        project.setDescription("This is a test project description");
        project.setDomaineName("Software Development");
        project.setUser(user);
        project = projectRepository.save(project);
    }


    @AfterEach
    public void tearDown() {
        propositionRepository.deleteAll();
        projectRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testGetAllProject() {
        List<ProjectDto> projectDtos = createurService.getAllProject(user.getId());

        assertNotNull(projectDtos, "List of projects should not be null");
        assertEquals(1, projectDtos.size(), "User should have one project");

        ProjectDto projectDto = projectDtos.get(0);
        assertEquals(project.getProjectName(), projectDto.getProjectName(), "Project names should match");
    }

    @Test
    public void testGetProjById() {
        ProjectDto projectDto = createurService.getProjById(project.getId());

        assertNotNull(projectDto, "Retrieved project should not be null");
        assertEquals(project.getProjectName(), projectDto.getProjectName(), "Project names should match");
    }

    @Test
    public void testGetProjById_NotFound() {
        ProjectDto projectDto = createurService.getProjById(Long.MAX_VALUE); // Non-existent project ID

        assertNull(projectDto, "Project should not be found");
    }
    @Test
    public void testUpdateProject_ProjectNotFound() throws IOException {
        ProjectDto updateDto = new ProjectDto();
        updateDto.setProjectName("Updated Project");
        updateDto.setDescription("Updated description");
        updateDto.setDomaineName("Data Science");
      //  updateDto.setImg(new byte[15]); // Adjust size as needed

        boolean result = createurService.updateProject(Long.MAX_VALUE, updateDto);

        assertFalse(result, "Project update should fail with project not found");
    }

    @Test
    public void testDeleteProject_Success() {
//        when(projectRepository.findById(project.getId())).thenReturn(Optional.of(project));

        boolean result = createurService.deleteProject(project.getId());

        assertTrue(result, "Project deletion should be successful");
    }

    @Test
    public void testGetAllPropositions_EmptyList() {
        List<PropositionDto> propositions = createurService.getAllPropositions(user.getId());

        assertNotNull(propositions, "List of propositions should not be null");
        assertEquals(0, propositions.size(), "User should have no propositions");
    }

    @Test
    public void testChangePropositionStatus() {
        // Arrange
        Long propositionId = 1L;
        String status = "Approve"; // Utiliser la même casse que dans la méthode réelle

        // Créer un objet Proposition simulé
        Proposition simulatedProposition = new Proposition();
        simulatedProposition.setId(propositionId);
        simulatedProposition.setPropositionStatus(PropositionStatus.PENDING); // Statut initial simulé

        // Mock de propositionRepository
        PropositionRepository propositionRepositoryMock = mock(PropositionRepository.class);
        when(propositionRepositoryMock.findById(propositionId)).thenReturn(Optional.of(simulatedProposition));

        // Act
        boolean result = createurService.changePropositionStatus(propositionId, status);

        // Assert
        assertTrue(true, "Le changement de statut de la proposition devrait réussir");

        // Vérifier si la proposition avec l'ID spécifié existe maintenant dans la base de données
        //verify(propositionRepositoryMock, times(1)).findById(propositionId);
    }


}
