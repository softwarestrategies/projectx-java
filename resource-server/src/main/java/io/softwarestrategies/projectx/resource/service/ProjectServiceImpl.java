package io.softwarestrategies.projectx.resource.service;

import io.softwarestrategies.projectx.common.exceptions.EntityNotFoundException;
import io.softwarestrategies.projectx.resource.data.entity.Project;
import io.softwarestrategies.projectx.resource.data.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = false)
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Flux<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Project> findById(Integer id) {
        return projectRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Project not found: " + id)));
    }

    @Override
    public Mono<Project> create(Project project) {
        return projectRepository.save(project)
                .onErrorResume(e -> Mono.error(new RuntimeException(e.getMessage())));
    }

    @Override
    public Mono<Project> patch(Integer id, Project project) {
        return findById(id)
                .flatMap(p -> {
                    p.setName(project.getName());
                    p.setDescription(project.getDescription());
                    p.setStatus(project.getStatus());
                    return projectRepository.save(p);
                })
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Project not found: " + project.getId())));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Project not found: " + id)))
                .flatMap(projectRepository::delete);    }
}
