package io.softwarestrategies.projectx.resource.controller;

import io.softwarestrategies.projectx.resource.data.entity.Project;
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
    public Flux<Project> handleFindAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Project> handleFindById(Integer id) {
        return projectService.findById(id);
    }

    @PostMapping
    public Mono<Project> handlePost(@RequestBody Project project) {
        return projectService.create(project);
    }

    @PutMapping("/{id}")
    public Mono<Project> handlePut(@PathVariable Integer id, @RequestBody Project project) {
        return projectService.update(id, project);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> handleDelete(@PathVariable Integer id) {
        return projectService.delete(id);
    }
}
