package com.last.project.ServicesTest;

import com.last.project.dto.ProjectDetailsEntrepreneurDto;
import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.dto.ReviewDto;
import com.last.project.entities.Project;
import com.last.project.entities.User;
import com.last.project.enums.ReviewStatus;
import com.last.project.repositories.ProjectRepository;
import com.last.project.repositories.PropositionRepository;
import com.last.project.repositories.UserRepository;
import com.last.project.services.entrepreneurService.EntrepreneurServiceimpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
public class EntrepreneurServiceImpTest {

    @Autowired
    private EntrepreneurServiceimpl entrepreneurService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private PropositionRepository propositionRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Project project;

    @BeforeEach
    public void setUp() {
        // Créer un utilisateur et un projet pour les tests
        user = new User();
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user = userRepository.save(user);

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
        List<ProjectDto> projectDtos = entrepreneurService.getAllProject();

        assertNotNull(projectDtos, "La liste des projets ne doit pas être nulle");
        assertEquals(1, projectDtos.size(), "L'utilisateur devrait avoir un projet");

        ProjectDto projectDto = projectDtos.get(0);
        assertEquals(project.getProjectName(), projectDto.getProjectName(), "Les noms de projet doivent correspondre");
    }

    @Test
    public void testSearshProjectByName() {
        List<ProjectDto> projectDtos = entrepreneurService.searshProjectByName("Test");

        assertNotNull(projectDtos, "La liste des projets ne doit pas être nulle");
        assertEquals(1, projectDtos.size(), "Il devrait y avoir un projet correspondant à la recherche");

        ProjectDto projectDto = projectDtos.get(0);
        assertEquals(project.getProjectName(), projectDto.getProjectName(), "Les noms de projet doivent correspondre");
    }

    @Test
    public void testSearshProjectDomaineName() {
        List<ProjectDto> projectDtos = entrepreneurService.searshProjectDomaineName("Development");

        assertNotNull(projectDtos, "La liste des projets ne doit pas être nulle");
        assertEquals(1, projectDtos.size(), "Il devrait y avoir un projet correspondant au domaine de recherche");

        ProjectDto projectDto = projectDtos.get(0);
        assertEquals(project.getProjectName(), projectDto.getProjectName(), "Les noms de projet doivent correspondre");
    }

    @Test
    public void testSaveProposition_Success() {
        PropositionDto propositionDto = new PropositionDto();
        propositionDto.setProjectId(project.getId());
        propositionDto.setUserId(user.getId());
       // propositionDto.setPropositionStatus(ReviewStatus.FALSE);

        boolean result = entrepreneurService.saveProposition(propositionDto);

        assertTrue(result, "La sauvegarde de proposition devrait réussir");
    }

    @Test
    public void testGetPrjectDetailsById() {
        // Arrange
        Long projectId = project.getId();

        // Act
        ProjectDetailsEntrepreneurDto projectDetailsEntrepreneurDto = entrepreneurService.getPrjectDetailsById(projectId);

        // Assert
        assertNotNull(projectDetailsEntrepreneurDto, "Les détails du projet ne doivent pas être nuls");
        assertEquals(project.getProjectName(), projectDetailsEntrepreneurDto.getProjectDto().getProjectName(), "Les noms de projet doivent correspondre");
        // Ajoutez d'autres assertions pour vérifier d'autres détails du projet si nécessaire
    }

    @Test
    public void testGetAllPrppositionByUserId() {
        // Arrange
        Long userId = user.getId();

        // Act
        List<PropositionDto> propositionDtos = entrepreneurService.getAllPrppositionByUserId(userId);

        // Assert
        assertNotNull(propositionDtos, "La liste des propositions ne doit pas être nulle");
        assertEquals(0, propositionDtos.size(), "L'utilisateur ne devrait avoir aucune proposition");
        // Vous pouvez ajouter d'autres assertions si vous vous attendez à un certain nombre de propositions pour cet utilisateur
    }

//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testGiveReview_Success() {
//        // Arrange
//        PropositionDto propositionDto = new PropositionDto();
//        propositionDto.setProjectId(project.getId());
//        propositionDto.setUserId(user.getId());
//        propositionDto.setReviewStatus(ReviewStatus.FALSE);
//        entrepreneurService.saveProposition(propositionDto); // Sauvegarde d'une proposition pour le test
//
//        ReviewDto reviewDto = new ReviewDto();
//        reviewDto.setUserId(user.getId());
//        reviewDto.setPropositionId(propositionDto.getId());
//        reviewDto.setReview("C'est un excellent projet !");
//        reviewDto.setRating(5L); // Note parfaite
//
//        // Act
//        boolean result = entrepreneurService.giveReview(reviewDto);
//
//        // Assert
//        assertTrue(result, "La revue devrait être réussie");
//        // Ajoutez d'autres assertions si nécessaire pour vérifier le statut de revue ou d'autres détails
//    }

}
