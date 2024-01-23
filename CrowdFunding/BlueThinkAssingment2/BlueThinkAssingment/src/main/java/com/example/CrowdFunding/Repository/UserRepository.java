package com.example.CrowdFunding.Repository;

import com.example.CrowdFunding.JavaPojo.Project;
import com.example.CrowdFunding.JavaPojo.User;

import java.awt.*;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByIsArchivedFalse();
}

public interface ContributionRepository extends JpaRepository<Contribution, Long> {
    List<Contribution> findByProjectId(Long projectId);
}
