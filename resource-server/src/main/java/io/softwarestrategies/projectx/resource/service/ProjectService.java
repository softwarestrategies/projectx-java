package io.softwarestrategies.projectx.resource.service;

import io.softwarestrategies.projectx.resource.data.entity.Project;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectService {

    Flux<Project> findAll();
    Mono<Project> findById(Integer id);
    Mono<Project> create(Project project);
    Mono<Project> update(Integer id, Project project);
    Mono<Void> delete(Integer id);
}
