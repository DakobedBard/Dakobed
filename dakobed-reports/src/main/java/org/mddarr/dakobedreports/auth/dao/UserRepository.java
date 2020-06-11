package org.mddarr.dakobedreports.auth.dao;


import org.mddarr.dakobedreports.auth.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username );
}