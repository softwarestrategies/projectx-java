package io.softwarestrategies.projectx.resource.data.repository;

import io.softwarestrategies.projectx.resource.data.entity.Project;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<Project, Integer> {
}