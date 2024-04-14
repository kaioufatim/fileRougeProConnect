package com.last.project.repositories;

import com.last.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //User FindFirstByEmail(String email);
   // Optional<User> findByUsername(String username);

    User findFirstByEmail(String email);
    Optional<User> findByEmail(String email);


//    Optional<User> findByName(String username);
//
    Boolean existsByUsername(String username);
//
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
}
