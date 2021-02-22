package io.softwarestrategies.projectx.resource.service;

import io.softwarestrategies.projectx.resource.data.entity.Project;
import io.softwarestrategies.projectx.resource.data.repository.ProjectCustomRepository;
import io.softwarestrategies.projectx.resource.data.repository.ProjectRepository;
import io.softwarestrategies.projectx.resource.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = false)
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectCustomRepository projectCustomRepository;

    public ProjectServiceImpl(
            ProjectRepository projectRepository,ProjectCustomRepository projectCustomRepository) {
        this.projectRepository = projectRepository;
        this.projectCustomRepository = projectCustomRepository;
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
        return projectRepository.save(project);
    }

    @Override
    public Mono<Project> update(Integer id, Project project) {
        return findById(id)
                .flatMap(p -> {
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
