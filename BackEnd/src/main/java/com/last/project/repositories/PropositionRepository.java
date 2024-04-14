package com.last.project.repositories;

import com.last.project.dto.PropositionDto;
import com.last.project.entities.Proposition;
import com.last.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition,Long> {
    List<Proposition> findAllByCreateurId(Long createurId);
    List<Proposition> findAllByUserId(Long UserId);
//    @Query("SELECT new com.last.project.dto.PropositionDto(p.id, p.propositionStatus, p.reviewStatus, p.propositionDate, p.user.name, p.user.id, p.createur.id, p.project.id, p.project.projectName) FROM Proposition p WHERE p.createur.id = :createurId")
//    List<PropositionDto> findAllByCreateurIdWithProjection(@Param("createurId") Long createurId);


}
