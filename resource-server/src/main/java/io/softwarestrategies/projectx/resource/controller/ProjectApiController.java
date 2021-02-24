package io.softwarestrategies.projectx.resource.controller;

import io.softwarestrategies.projectx.common.data.dto.ProjectDTO;
import io.softwarestrategies.projectx.resource.data.entity.Project;
import io.softwarestrategies.projectx.resource.data.utils.ProjectConverters;
import io.softwarestrategies.projectx.resource.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/projects")
public class ProjectApiController {

    private final ProjectService projectService;

    public ProjectApiController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Flux<ProjectDTO> handleFindAll() {
        return projectService.findAll().flatMap(ProjectConverters::toProjectDTO);
    }

    @GetMapping("/{id}")
    public Mono<ProjectDTO> handleFindById(@PathVariable Integer id) {
        return projectService.findById(id).flatMap(ProjectConverters::toProjectDTO);
    }

    @PostMapping
    public Mono<Project> handlePost(@RequestBody ProjectDTO projectDTO) {
        return projectService.create(ProjectConverters.toProject(projectDTO));
    }

    @PutMapping("/{id}")
    public Mono<ProjectDTO> handlePut(@PathVariable Integer id, @RequestBody Project project) {
        return projectService.update(id, project).flatMap(ProjectConverters::toProjectDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> handleDelete(@PathVariable Integer id) {
        return projectService.delete(id);
    }
}
