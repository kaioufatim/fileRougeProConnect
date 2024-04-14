package com.last.project.repositories;

import com.last.project.entities.Role;
import com.last.project.enums.ERole;
import com.last.project.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    //Optional<Role> findByName(UserRole name);
    Optional<Role> findByName(ERole name);


}
