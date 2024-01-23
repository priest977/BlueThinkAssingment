package com.example.CrowdFunding.Services;

import com.example.CrowdFunding.JavaPojo.Project;
import com.example.CrowdFunding.Repository.ContributionRepository;
import com.example.CrowdFunding.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        // Implement logic to create a project
        return projectRepository.save(project);
    }

    public List<Project> getInnovatorProjects(Long innovatorId) {
        // Implement logic to get projects for an innovator
        return projectRepository.findByInnovatorIdAndIsArchivedFalse(innovatorId);
    }

    public List<Project> getAllProjects() {
        // Implement logic to get all active projects
        return projectRepository.findByIsArchivedFalse();
    }
}

@Service
public class ContributionService {
    private final ContributionRepository contributionRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ContributionService(ContributionRepository contributionRepository, ProjectRepository projectRepository) {
        this.contributionRepository = contributionRepository;
        this.projectRepository = projectRepository;
    }

    public void contributeToProject(Contribution contribution) {
        // Implement logic to contribute to a project
        Project project = projectRepository.findById(contribution.getProject().getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setCurrentAmount(project.getCurrentAmount() + contribution.getAmount());
        contributionRepository.save(contribution);

        if (project.getCurrentAmount() >= project.getGoalAmount()) {
            project.setArchived(true);
        }
    }
}
