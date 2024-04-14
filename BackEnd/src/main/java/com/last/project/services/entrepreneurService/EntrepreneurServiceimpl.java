package com.last.project.services.entrepreneurService;

import com.last.project.dto.ProjectDetailsEntrepreneurDto;
import com.last.project.dto.ProjectDto;
import com.last.project.dto.PropositionDto;
import com.last.project.dto.ReviewDto;
import com.last.project.entities.Project;
import com.last.project.entities.Proposition;
import com.last.project.entities.Review;
import com.last.project.entities.User;
import com.last.project.enums.ReviewStatus;
import com.last.project.repositories.ProjectRepository;
import com.last.project.repositories.PropositionRepository;
import com.last.project.repositories.ReviewRepository;
import com.last.project.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Service
public class EntrepreneurServiceimpl implements EntrepreneurService {
    private static final Logger logger = LoggerFactory.getLogger(EntrepreneurServiceimpl.class);

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PropositionRepository propositionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public List<ProjectDto> getAllProject(){
        return projectRepository.findAll().stream().map(Project::getProjectDto).collect(Collectors.toList());
    }
    public List<ProjectDto> searshProjectByName(String name){
        return projectRepository.findAllByProjectNameContaining(name).stream().map(Project::getProjectDto).collect(Collectors.toList());
    }
    public List<ProjectDto> searshProjectDomaineName(String name){
        return projectRepository.findAllByDomaineNameContainingIgnoreCase(name).stream().map(Project::getProjectDto).collect(Collectors.toList());
    }
//    public boolean saveProposition(PropositionDto propositionDto){
//        Optional<Project> optionalProject = projectRepository.findById(propositionDto.getProjectId());
//        Optional<User> optionalUser = userRepository.findById(propositionDto.getUserId());
//        if(optionalUser.isPresent() && optionalProject.isPresent()){
//            Proposition proposition = new Proposition();
//            proposition.setPropositionDate(propositionDto.getPropositionDate());
//            proposition.setPropositionStatus(propositionDto.getPropositionStatus());
//            proposition.setProject(optionalProject.get());
//            proposition.setCreateur(optionalProject.get().getUser());
//            proposition.setReviewStatus(ReviewStatus.FALSE);
//            propositionRepository.save(proposition);
//            return true;
//        }
//        return false;
//    }
public boolean saveProposition(PropositionDto propositionDto){
    Optional<Project> optionalProject = projectRepository.findById(propositionDto.getProjectId());
    Optional<User> optionalUser = userRepository.findById(propositionDto.getUserId());
    if(optionalUser.isPresent() && optionalProject.isPresent()){
        Proposition proposition = new Proposition();
        proposition.setPropositionDate(propositionDto.getPropositionDate());
        proposition.setPropositionStatus(propositionDto.getPropositionStatus());
        proposition.setProject(optionalProject.get());
        proposition.setCreateur(optionalProject.get().getUser());
        proposition.setReviewStatus(ReviewStatus.FALSE);
        proposition.setUser(optionalUser.get()); // Assurez-vous que l'utilisateur est défini ici
        propositionRepository.save(proposition);
        return true;
    }
    return false;
}

    public ProjectDetailsEntrepreneurDto getPrjectDetailsById(Long projectId){
        Optional <Project> optionalProject=projectRepository.findById(projectId);
        ProjectDetailsEntrepreneurDto projectDetailsEntrepreneur= new ProjectDetailsEntrepreneurDto();
        if (optionalProject.isPresent()){
            projectDetailsEntrepreneur.setProjectDto(optionalProject.get().getProjectDto());
            List<Review> reviewList = reviewRepository.findAllByProjectId(projectId);
            projectDetailsEntrepreneur.setReviewDtoList(reviewList.stream().map(Review :: getDto).collect(Collectors.toList()));
        }
        return projectDetailsEntrepreneur;
    }
    public List<PropositionDto>getAllPrppositionByUserId(Long userId){
        return  propositionRepository.findAllByUserId(userId).stream().map(Proposition ::getPropositionDto).collect(Collectors.toList());
    }

    public Boolean giveReview(ReviewDto reviewDto){
        Optional<User> optionalUser=userRepository.findById(reviewDto.getUserId());
        Optional<Proposition> optionalProposition= propositionRepository.findById(reviewDto.getPropositionId());
        if (optionalUser.isPresent() && optionalProposition.isPresent()){
            Review review = new Review();
            review.setReviewDate(new Date());
            review.setReview(reviewDto.getReview());
            review.setRating(reviewDto.getRating());

            review.setUser(optionalUser.get());
            review.setProject(optionalProposition.get().getProject());
            reviewRepository.save(review);
             Proposition proposition= optionalProposition.get();
             proposition.setReviewStatus(ReviewStatus.TRUE);

             propositionRepository.save(proposition);
             return true;


        }
        return false;
    }
//public Boolean giveReview(ReviewDto reviewDto) {
//    // Vérifier si les identifiants sont non nuls
//    if (reviewDto.getUserId() == null || reviewDto.getPropositionId() == null) {
//        throw new IllegalArgumentException("Les identifiants ne doivent pas être nuls");
//    }
//
//    Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
//    Optional<Proposition> optionalProposition = propositionRepository.findById(reviewDto.getPropositionId());
//
//    // Vérifier si l'utilisateur et la proposition existent
//    if (optionalUser.isEmpty()) {
//        throw new EntityNotFoundException("L'utilisateur avec l'ID " + reviewDto.getUserId() + " n'a pas été trouvé");
//    }
//
//    if (optionalProposition.isEmpty()) {
//        throw new EntityNotFoundException("La proposition avec l'ID " + reviewDto.getPropositionId() + " n'a pas été trouvée");
//    }
//
//    // Créer une nouvelle revue
//    Review review = new Review();
//    review.setReviewDate(new Date());
//    review.setReview(reviewDto.getReview());
//    // Vérifier si le rating est null ou non
//    review.setRating(reviewDto.getRating() != null ? reviewDto.getRating() : 0); // Mettre 0 si le rating est null
//
//    // Associer l'utilisateur et la proposition à la revue
//    review.setUser(optionalUser.get());
//    review.setProject(optionalProposition.get().getProject());
//
//    // Enregistrer la revue dans la base de données
//    reviewRepository.save(review);
//
//    // Mettre à jour le statut de la proposition
//    Proposition proposition = optionalProposition.get();
//    proposition.setReviewStatus(ReviewStatus.TRUE);
//    propositionRepository.save(proposition);
//
//    return true;
//}

//
//    public Boolean giveReview(ReviewDto reviewDto) {
//        try {
//            Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
//            Optional<Proposition> optionalProposition = propositionRepository.findById(reviewDto.getPropositionId());
//
//            // Log des valeurs des identifiants
//            logger.info("User ID: {}", reviewDto.getUserId());
//            logger.info("Proposition ID: {}", reviewDto.getPropositionId());
//
//            if (optionalUser.isPresent() && optionalProposition.isPresent()) {
//                Review review = new Review();
//                review.setReviewDate(new Date());
//                review.setReview(reviewDto.getReview());
//                review.setRating(reviewDto.getRating());
//
//                review.setUser(optionalUser.get());
//                review.setProject(optionalProposition.get().getProject());
//                reviewRepository.save(review);
//
//                // Log pour indiquer que la revue a été enregistrée
//                logger.info("Review saved successfully");
//
//                Proposition proposition = optionalProposition.get();
//                proposition.setReviewStatus(ReviewStatus.TRUE);
//                propositionRepository.save(proposition);
//
//                // Log pour indiquer que le statut de proposition a été mis à jour
//                logger.info("Proposition review status updated successfully");
//
//                return true;
//            } else {
//                // Log pour indiquer que l'utilisateur ou la proposition est introuvable
//                logger.warn("User or proposition not found");
//                return false;
//            }
//        } catch (Exception e) {
//            // Log de toute exception survenue
//            logger.error("An error occurred while giving review: {}", e.getMessage());
//            return false;
//        }
//    }



    public class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }



}
