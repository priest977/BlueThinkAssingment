package com.example.CrowdFunding.Controller;

import com.example.CrowdFunding.JavaPojo.Project;
import com.example.CrowdFunding.Services.ContributionService;
import com.example.CrowdFunding.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/innovators")
public class InnovatorController {
    private final ProjectService projectService;

    @Autowired
    public InnovatorController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // Implement logic to create a project
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getInnovatorProjects(@RequestParam Long innovatorId) {
        List projects = (List) projectService.getInnovatorProjects(innovatorId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}

@RestController
@RequestMapping("/api/donors")
public class DonorController {
    private final ProjectService projectService;
    private final ContributionService contributionService;

    @Autowired
    public DonorController(ProjectService projectService, ContributionService contributionService) {
        this.projectService = projectService;
        this.contributionService = contributionService;
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        // Implement logic to get all active projects
        List projects = (List) projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping("/contribute")
    public <Contribution> ResponseEntity<String> contributeToProject(@RequestBody Contribution contribution) {
        // Implement logic to contribute to a project
        contributionService.contributeToProject(contribution);
        return new ResponseEntity<>("Contribution successful", HttpStatus.OK);
    }
}

